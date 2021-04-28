package manager;

import application.Controller;
import character.Player;

/**
 * 전반적인 게임 규칙(클리어, 게임오버, 패배 등)을 관리하는 클래스
 * @author gagip
 *
 */
public class GameManager {
	private static GameManager gm = new GameManager();
	
	public Controller controllor;
	private ScriptManager sm;
	private BattleManager bm;
	
	
	// 초기값
	public static final int DEFAULT_HP = 100;
	public static final int DEFAULT_ATTACK = 10;
	public static final int DEFAULT_DEFENSE = 1;
	public static final float DEFAULT_BATTLE_TIME = 1f;
	public static final float DEFAULT_TIMER = 3f;
	
	public Player player;
	
	private GameManager() {
		
	}
	
	public static GameManager getInstance() {
		return gm;
	}
	
	private void init() {
		// 관리자 호출
		sm = ScriptManager.getInstance();
		bm = BattleManager.getInstance();
		
		// 컨트롤
		player = new Player(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENSE, 1000);

		// UI 컨트롤
		controllor = Controller.getInstance();
		controllor.setPlayerProperty(player);
		controllor.setInvenProperty(player.getInven());
		
	}
	
	
	public void start() {
		// TODO 타이머 시작
		init();
		
		
		try {
			
			Thread.sleep(1000);
			player.setHp(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
