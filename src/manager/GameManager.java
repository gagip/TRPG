package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import application.Controller;
import character.Player;
import item.Inventory;
import item.Item;

/**
 * 전반적인 게임 규칙(클리어, 게임오버, 패배 등)과 게임 UI를 관리하는 클래스
 * @author gagip
 *
 */
public class GameManager {
	private static GameManager gm = new GameManager();
	
	public static Controller controllor;
	private ScriptManager script;
	private BattleManager bm;
	
	enum PlayerAction {
		MOVE,
		BATTLE, RETEAT,
		EQUIP, USE,
		BUY, SELL,
	}
	
	enum PlayerState {
		
	}
	
	private List<PlayerAction> action = new ArrayList<PlayerAction>();
	
	public void setAction() {
		switch (player.getWhere()) {
		case "":
			
			break;

		default:
			break;
		}
	}
	
	// 초기값
	public static final int DEFAULT_HP = 100;
	public static final int DEFAULT_ATTACK = 10;
	public static final int DEFAULT_DEFENSE = 1;
	public static final float DEFAULT_BATTLE_TIME = 1f;
	public static final float DEFAULT_TIMER = 3f;
	
	public Player player;
	
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

	}
	
	
	public void start() {
		// TODO 타이머 시작
		init();
		printGameInfo(script.startScene());
		printGameInfo(script.action());	
	}
	
	/**
	 * 캐릭터 정보를 UI에 업데이트
	 */
	public void updateUI() {
		printStat(player);
		printInven(player.getInven());
	}
	
	
	public void printStat(Player player) {
		controllor.statTxtAr.setText(String.format(
				"체력: %d/%d\n"
				+ "공격력: %d\n"
				+ "방어력: %d\n"
				+ "돈: %d"
				, player.getHp(), player.getMaxHp()
				, player.getAttack()
				, player.getDefense()
				, player.getMoney()
		));
	}
	
	
	public void printInven(Inventory inven) {
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
		// 한 줄씩 split
		controllor.gameInfoTxtAr.appendText(str);
	}
	
	
	public static void setController(Controller ctrl) {
		controllor = ctrl; 
	}
}
