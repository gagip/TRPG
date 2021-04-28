package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import application.Controller;
import character.Player;
import character.PlayerAction;
import character.PlayerState;
import item.Inventory;
import item.Item;
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
	private ScriptManager script;
	private BattleManager bm;
	
	
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
		bm = BattleManager.getInstance();
		
		// 플레이어 생성
		player = new Player(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENSE, 1000);
		setPossibleActions(player.getWhere(), curState);
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
	private void setPossibleActions(Place place, PlayerState state) {
		possibleActions.clear();
		possibleActions.add(PlayerAction.MOVE);
		possibleActions.add(PlayerAction.EQUIP);
		possibleActions.add(PlayerAction.USE);
		
		if (place instanceof Itrade) {
			possibleActions.add(PlayerAction.BUY);
			possibleActions.add(PlayerAction.SELL);
		} else {
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
				+ "현재 행동: %s"
				, player.getWhere().toString()
				, player.getHp(), player.getMaxHp()
				, player.getAttack()
				, player.getDefense(), player.getMoney()
				, curAction.toString()
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
			strBuf.append(script.printChoice(getAvailablePlace()));
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
		switch (action) {
		case IDLE:
			curAction = choice(possibleActions, choice);
			
			script.idle(curAction);
			beforeAction(curAction, state);
			break;
		case MOVE:
			Place toPlace = choice(getAvailablePlace(), choice);
			player.move(toPlace);
			script.move(player.getWhere());
			curAction = PlayerAction.IDLE;
			setPossibleActions(toPlace, state);
			beforeAction(curAction, state);
			break;
		default:
			break;
		}
		
		updateUI();
	}
	

	public static void setController(Controller ctrl) {
		controllor = ctrl; 
	}
}
