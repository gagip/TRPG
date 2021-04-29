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
import place.Dungeon;
import place.Inn;
import place.Itrade;
import place.Place;
import place.Smith;
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
	
	
	// 초기값
	public static final int DEFAULT_HP = 100;
	public static final int DEFAULT_ATTACK = 10;
	public static final int DEFAULT_DEFENSE = 1;
	public static final float DEFAULT_BATTLE_TIME = 1f;
	public static final float DEFAULT_TIMER = 3f;
	
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
		
		// 플레이어 생성
		player = new Player(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENSE, 1000);
		player.setWhere(pm.village);
		changePossibleActions(player.getWhere(), curState);
		
		updateUI();
	}
	
	
	public void start() {
		// TODO 타이머 시작
		init();
		script.startScene();
		beforeAction(curAction, curState);
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
		possibleActions.add(PlayerAction.EQUIP);
		possibleActions.add(PlayerAction.USE);
		
		// 장소에 따른 행동 정의
		if (place instanceof Itrade) {
			possibleActions.add(PlayerAction.BUY);
			possibleActions.add(PlayerAction.SELL);
		} else if (place instanceof Inn) {
			possibleActions.add(PlayerAction.REST);
		} else if (place instanceof Dungeon){
			switch (state) {
			case BATTLE:
				possibleActions.add(PlayerAction.RETEAT);
				break;
			case CONTECT:
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
			if (place instanceof Dungeon) {
				// 몬스터를 만나면
				Dungeon dungeon = (Dungeon) place;
				Enemy enemy = dungeon.getMonster();
				if (enemy != null) 		curState = PlayerState.CONTECT;
			} 
			break;
		case BATTLE:
			curState = PlayerState.IDLE;
			break;
		case CONTECT:
			if (curAction == PlayerAction.BATTLE)	curState = PlayerState.BATTLE;
			else if (curAction == PlayerAction.BUY ||
					curAction == PlayerAction.SELL)	curState = PlayerState.TRADE;
			else 									curState = PlayerState.IDLE;
			break;
			
		case TRADE:
			curState = PlayerState.IDLE;
			break;
		default:
			break;
		}
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
		controllor.statTxtAr.setText(String.format(
				"현재 장소: %s\n"
				+ "체력: %d/%d\n"
				+ "공격력: %d\n"
				+ "방어력: %d\t돈: %d\n"
				+ "현재 행동: %s\t현재 상태: %s"
				, player.getWhere().toString()
				, player.getHp(), player.getMaxHp()
				, player.getAttack()
				, player.getDefense(), player.getMoney()
				, curAction.toString(), curState.toString()
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
	
	public void userInput(String str) {
		// 한 자리 숫자만 처리
		if (!canInput) return;  
		if (str.isEmpty() || str.length() > 1) return;
		if (!Character.isDigit(str.charAt(0))) return;
		
		canInput = false;							// 입력 잠금
		// 액션 선택
		int choice = Integer.parseInt(str);
		
		
		excuteAction(curAction, curState, choice);
	}
	
	private void beforeAction(PlayerAction action, PlayerState state) {
		StringBuffer strBuf = new StringBuffer();
		switch (action) {
		case IDLE:
			strBuf.append("어떤 행동을 하시겠습니까?\n");
			strBuf.append(script.printChoice(getPossibleActions()));
			break;
		case MOVE:
			strBuf.append("어디로 이동 하시겠습니까?\n");
			if (player.getWhere() instanceof Dungeon) {
				strBuf.append(script.printChoice(dm.getDungeons()));
			} else {
				strBuf.append(script.printChoice(getAvailablePlace()));
			}
			
		case BATTLE:
			break;
		default:
			break;
		}

		// 입력 가능
		canInput = true;
		printGameInfo(strBuf.toString());
		
		// 이후 입력 대기
	}
	
	/**
	 * 액션 처리 후 결과
	 * @param action
	 * @param state
	 * @param choice
	 */
	private void excuteAction(PlayerAction action, PlayerState state, int choice) {
		Place place = player.getWhere();
		switch (action) {
		case IDLE:
			curAction = choice(possibleActions, choice);
			script.idle(curAction);
			break;
		case MOVE:
			// 던전이라면 몇 층으로 갈지 선택지 제공
			if (place instanceof Dungeon) {
				
				if (place.toString().equals("던전 입구")) {
					
				}
				// 해당 장소로 이동
				Place toPlace = choice(dm.getDungeons(), choice);
				player.move(toPlace);
				script.move(player.getWhere());
				
				if (toPlace instanceof Dungeon) {
					// 적이 있으면 행동 재설정
					Dungeon dungeon = (Dungeon) toPlace;
					
					if (dungeon.getEnemies().size() > 0) {
						curAction = PlayerAction.IDLE;
					}
				} else {
					player.move(toPlace);
					script.move(player.getWhere());
				}
				
				
			} else {
				// 해당 장소로 이동
				Place toPlace = choice(getAvailablePlace(), choice);
				player.move(toPlace);
				script.move(player.getWhere());
				
				if (toPlace instanceof Dungeon) {
					
				} else {
					// IDLE 상태로 변환 후 선택지
					curAction = PlayerAction.IDLE;
					
				}
			}
			
			break;
		default:
			break;
		}
		
		
		changeState();
		changePossibleActions(player.getWhere(), state);
		updateUI();
		beforeAction(curAction, state);
	}
	

	public static void setController(Controller ctrl) {
		controllor = ctrl; 
	}
}
