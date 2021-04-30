package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import application.Controller;
import character.Player;
import character.PlayerAction;
import character.PlayerState;
import enemy.Enemy;
import item.Inventory;
import item.Item;
import item.consumption.IConsumption;
import item.equipment.IEquipment;
import npc.InnNpc;
import npc.Itrade;
import npc.Npc;
import npc.SmithNpc;
import npc.StoreNpc;
import place.Dungeon;
import place.Inn;
import place.Place;
import place.Smith;
import place.Store;
import place.Village;

/**
 * 전반적인 게임 규칙(클리어, 게임오버, 패배 등)과 게임 UI를 관리하는 클래스
 * 플레이어 액션 제어
 * @author gagip
 *
 */
public class GameManager {
	private static GameManager gm = new GameManager();
	
	public static Controller controllor;
	private DungeonManager dm;
	private ScriptManager script;
	private BattleManager bm;
	private PlaceManager pm;
	public boolean running = false;
	private int timer;
	
	// 초기값
	public static final int DEFAULT_HP = 50;
	public static final int DEFAULT_ATTACK = 3;
	public static final int DEFAULT_DEFENSE = 1;
	public static final int DEFAULT_TIME = 500;
	
	public Player player;

	private List<PlayerAction> possibleActions = new ArrayList<PlayerAction>();
	private PlayerAction curAction = PlayerAction.IDLE; 
	private PlayerState curState = PlayerState.IDLE;
	public boolean canInput = false;
	
	private GameManager() {}
	
	public static GameManager getInstance() {
		return gm;
	}
	
	private void init() {
		// 관리자 호출
		script = ScriptManager.getInstance();
		dm = DungeonManager.getInstance();
		bm = BattleManager.getInstance();
		pm = PlaceManager.getInstance();
		
		pm.init();
		timer = DEFAULT_TIME;
		
		// 플레이어 생성
		player = new Player(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENSE, 100000);
		player.setWhere(pm.village);
		changePossibleActions(player.getWhere(), curState);
		
		updateUI();
	}
	
	
	public void gameStart() {
		// TODO 타이머 시작
		init();
		running = true;
		controllor.startTimer();
		script.startScene();
		beforeAction();
	}
	
	public void subtractTimer() {
		this.timer--;
	}
	
	public int getTimer() {
		return this.timer;
	}
	
	public void setTimer(int timer) {
		this.timer = timer;
	}
	
	public boolean getRunning() {
		return running;
	}
	
	public void gameClear() {
		running = false;
		int time = timer;
		printGameInfo(String.format("게임을 %d초 남기고 클리어 하셨습니다.\n", time));
	}
	
	public void gameOver() {
		running = false;
		printGameInfo("게임 오버\n 시작 버튼을 눌려 다시 실행해주세요\n");
	}
	
	public void win(Player player, Enemy enemy) {
		StringBuffer strbuf = new StringBuffer();
		
		int gold = player.getGold();
		gold += enemy.getGold();
		player.setGold(gold);
		strbuf.append(String.format("돈: +%d\n", enemy.getGold()));
		
		
		
		enemy.die((Dungeon) player.getWhere());
		
		printGameInfo(strbuf.toString());
	}
	
	public void defeat() {
		// 캐릭터 마을로
		player.setWhere(pm.village);
		player.setHp(player.getMaxHp());
		player.setGold(player.getGold() - 1000);
		// TODO 타이머 차감
		timer -= 100;
	}
	
	
	public List<Place> getAvailablePlace() {
		Place place = player.getWhere();
		return place.getAvailablePlace();
	}
	
	public List<PlayerAction> getPossibleActions(){
		return possibleActions;
	}
	
	/**
	 * 장소 및 상태에 따른 플레이어 액션 정의
	 */
	private void changePossibleActions(Place place, PlayerState state) {
		possibleActions.clear();
		// 일반적인 행동 정의
		possibleActions.add(PlayerAction.MOVE);
		possibleActions.add(PlayerAction.INVEN);
		
		// 장소, 상태에 따른 행동 정의
		if (place instanceof Smith ||
			place instanceof Store) {
			possibleActions.add(PlayerAction.BUY);
			possibleActions.add(PlayerAction.SELL);
		} else if (place instanceof Inn) {
			possibleActions.add(PlayerAction.REST);
		} else if (place instanceof Dungeon){
			switch (state) {
			case BATTLE:
				possibleActions.add(PlayerAction.RETEAT);
				break;
			case CONTECT_ENEMY:
				possibleActions.add(PlayerAction.BATTLE);
				break;
			default:
				break;
			}
		}
	}
	
	private void changeState() {
		// 상태를 변화시키는 변수들 추출
		Place place = player.getWhere();
		
		// 가진 정보들을 종합하여 상태 결정
		switch (curState) {
		case IDLE:
			if (curAction == PlayerAction.MOVE)			curState = PlayerState.MOVE;
			else if (curAction == PlayerAction.BATTLE)	curState = PlayerState.BATTLE;
			else if (curAction == PlayerAction.INVEN)	curState = PlayerState.SEARCH;
			else if (curAction == PlayerAction.BUY ||
					curAction == PlayerAction.SELL)		curState = PlayerState.TRADE;
			break;
			
		case MOVE:
			curState = PlayerState.IDLE;
			
			// 도착 장소에 사람이 몬스터가 있다면
			if (place instanceof Dungeon) {
				Dungeon dungeon = (Dungeon) place;
				if (dungeon.getMonster() != null) 		curState = PlayerState.CONTECT_ENEMY;
			} else if (place instanceof Inn ||
					place instanceof Smith ||
					place instanceof Store) 			curState = PlayerState.CONTECT_NPC;
			break;
			
		case CONTECT_ENEMY:
			if (curAction == PlayerAction.MOVE)			curState = PlayerState.MOVE;
			else if (curAction == PlayerAction.INVEN)	curState = PlayerState.SEARCH;
			else if (curAction == PlayerAction.BATTLE)	curState = PlayerState.BATTLE;
			break;
			
		case CONTECT_NPC:
			if (curAction == PlayerAction.MOVE)			curState = PlayerState.MOVE;
			else if (curAction == PlayerAction.INVEN)	curState = PlayerState.SEARCH;
			else if (curAction == PlayerAction.BUY ||
					curAction == PlayerAction.SELL)		curState = PlayerState.TRADE;
			break;
			
		case BATTLE:
			// 전투 종료 후 
			curState = PlayerState.IDLE;
			
			// 도착 장소에 사람이 몬스터가 있다면
			if (place instanceof Dungeon) {
				Dungeon dungeon = (Dungeon) place;
				if (dungeon.getEnemies().size() > 0) {
					curState = PlayerState.CONTECT_ENEMY;
				}
			} else if (place instanceof Inn ||
					place instanceof Smith ||
					place instanceof Store) {
				curState = PlayerState.CONTECT_NPC;
			}
			break;
		case SEARCH:
			curState = PlayerState.IDLE;
			break;
		case TRADE:
			curState = PlayerState.IDLE;
			break;
		default:
			break;
		
		}
	}
	
	private void battle(Player player, Enemy enemy) {
		bm.startBattle(player, enemy);
	}
	
	private <T> T choice(List<T> list, int num) {
		try {
			return list.get(num);
		} catch (Exception e) { return null; }
	}
	
	/**
	 * 캐릭터 정보를 UI에 업데이트
	 */
	public void updateUI() {
		printStat(player);
		printInven(player.getInven());
	}
	
	private void printStat(Player player) {
		List<IEquipment> equipments = player.getEquipment();
		controllor.statTxtAr.setText(String.format(
				"현재 장소: %s\n"
				+ "체력: %d/%d    머리: %s\n"
				+ "공격력: %d     옷: %s\n"
				+ "방어력: %d     무기: %s\n"
				+ "돈: %d\n"
				, player.getWhere().toString()
				, player.getHp(), player.getMaxHp(), equipments.get(0)
				, player.getAttack(), equipments.get(1)
				, player.getDefense(), equipments.get(2)
				, player.getGold()
		));
	}
	
	private void printInven(Inventory inven) {
		StringBuffer strbuf = new StringBuffer();
		List<Item> items = inven.getItems();
		for (int i=0; i<items.size(); i++) {
			strbuf.append(String.format("%d: %s", 
						i, Objects.isNull(items.get(i)) ? "(비어있음)" : items.get(i) ));
			strbuf.append( (i%2)!=0 ? "\n" : "\t" );
		}
		
		controllor.inventoryTxtAr.setText(strbuf.toString());
	}
	
	public void printGameInfo(String str) {
		controllor.gameInfoTxtAr.appendText(str);
	}
	
	/**
	 * 선택지 제공
	 */
	private void beforeAction() {
		StringBuffer strBuf = new StringBuffer();
		Place place = player.getWhere();
		Npc npc; 
		
		switch (curState) {
		case IDLE:
			strBuf.append("어떤 행동을 하시겠습니까?\n");
			strBuf.append(script.printChoice(getPossibleActions()));
			break;
		case MOVE:
			strBuf.append("어디로 이동 하시겠습니까?\n");
			// 장소마다 다르게 선택지 책정
			if (place instanceof Dungeon) { 
				strBuf.append(script.printChoice(dm.getDungeons()));
			} else {
				strBuf.append(script.printChoice(getAvailablePlace()));
			}
			break;
		case CONTECT_ENEMY:
			if (place instanceof Dungeon) {
				Dungeon dungeon = (Dungeon) place;
				Enemy enemy = dungeon.getMonster();
				
				strBuf.append(String.format(
						"%s (을)를 마주쳤다.\n"
						+ "-----------------\n"
						+ "체력: %d\n"
						+ "공격력: %d\n"
						+ "방어력: %d\n"
						+ "획득 골드: %d\n"
						+ "패시브: %s\n"
						+ "-----------------\n" 
						, enemy
						, enemy.getHp()
						, enemy.getAttack()
						, enemy.getDefense()
						, enemy.getGold()
						, enemy.getPassiveDescription()
				));
				strBuf.append("어떤 행동을 하시겠습니까?\n");
				strBuf.append(script.printChoice(getPossibleActions()));
			}
			break;
		case CONTECT_NPC:
			npc = place.getNpc();
			strBuf.append(npc.talk());
			strBuf.append(script.printChoice(getPossibleActions()));
			break;
		case BATTLE:
			//
			break;
		case SEARCH:
			strBuf.append("어떤 아이템을 사용하실껀가요?\n");
			strBuf.append(script.printChoice(player.getInven().getItems()));
			strBuf.append("사용하실 아이템이 없다면 아무 숫자를 입력하세요\n");
			break;
		case TRADE:
			if (curAction == PlayerAction.BUY) {
				npc = place.getNpc();

				strBuf.append("무엇을 사실껀가요?\n");
				strBuf.append(script.printItem(npc.getDisplayRack()));
			} else if (curAction == PlayerAction.SELL) {
				strBuf.append("무엇을 파실껀가요?\n");
				strBuf.append(script.printChoice(player.getInven().getItems()));
				strBuf.append("파실 아이템이 없다면 빈 아이템 공간 숫자를 입력하세요\n");
			}
			break;
		default:
			break;
		}

	
		// 입력 가능
		canInput = true;
		printGameInfo(strBuf.toString());
		
		// 이후 입력 대기
	}
	
	public void userInput(String str) {
		// 한 자리 숫자만 처리
		if (!canInput) return;  
		if (str.isEmpty() || str.length() > 1) return;
		if (!Character.isDigit(str.charAt(0))) return;
		
		canInput = false;							// 입력 잠금
		// 액션 선택
		int choice = Integer.parseInt(str);
		
		
		excuteAction(choice);
	}
	
	/**
	 * 액션 처리 후 결과
	 * @param choice
	 */
	private void excuteAction(int choice) {
		Place place = player.getWhere();
		Npc npc;
		
		// 액션 실행 이전에 예외처리 (다른 선택지 제공)
		if (curState == PlayerState.BATTLE)
			curAction = PlayerAction.IDLE;
		else if (curState == PlayerState.SEARCH)
			curAction = PlayerAction.INVEN;
		else if (curState == PlayerState.MOVE)
			curAction = PlayerAction.MOVE;
		else if (curState == PlayerState.TRADE)
			;
		else
			curAction = choice(getPossibleActions(), choice);
			
		
		switch (curAction) {
		case IDLE:
			break;
			
		case MOVE:
			if (curState == PlayerState.MOVE) {
				if (place instanceof Dungeon) {
					Place toPlace = choice(dm.getDungeons(), choice);
					if (toPlace != null) {
						player.move(toPlace);
						script.move(player.getWhere());
					}
				} else {
					Place toPlace = choice(getAvailablePlace(), choice);
					if (toPlace != null) {
						player.move(toPlace);
						script.move(player.getWhere());
					}
				}
			}
			break;
			
		case BATTLE:
			if (place instanceof Dungeon) {
				Enemy enemy = ((Dungeon) place).getMonster();
				battle(player, enemy);
			}
			break;
			
		case INVEN:
			if (curState == PlayerState.SEARCH) {
				Inventory inven = player.getInven();
				Item item = inven.getItem(choice);
				if (item instanceof IConsumption) {
					IConsumption consumption = (IConsumption) item;
					
					player.use(consumption);
					script.usedItem(item);
				} else if (item instanceof IEquipment) {
					IEquipment equipment = (IEquipment) item;
					
					player.equip(equipment);
					script.usedItem(item);
				}
			}
			break;
			
		case REST:
			InnNpc innNpc = (InnNpc) place.getNpc();
			innNpc.rest(player);
			break;
			
		case BUY:
			if (curState == PlayerState.TRADE) {
				npc = place.getNpc();
				if (npc instanceof SmithNpc) {
					SmithNpc smithNpc = (SmithNpc) npc;
					smithNpc.sell(choice, player);
				} else if (npc instanceof StoreNpc) {
					StoreNpc storeNpc = (StoreNpc) npc;
					storeNpc.sell(choice, player);
				}
			}
			
			break;
		case SELL:
			if (curState == PlayerState.TRADE) {
				npc = place.getNpc();
				if (npc instanceof SmithNpc) {
					SmithNpc smithNpc = (SmithNpc) npc;
					smithNpc.buy(choice, player);
				} else if (npc instanceof StoreNpc) {
					StoreNpc storeNpc = (StoreNpc) npc;
					storeNpc.buy(choice, player);
				}
			}
			break;
		default:
			break;
		}
		
		
		changeState();
		changePossibleActions(player.getWhere(), curState);
		updateUI();
		beforeAction();
	}
	

	public static void setController(Controller ctrl) {
		controllor = ctrl; 
	}
}
