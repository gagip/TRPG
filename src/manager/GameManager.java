package manager;

import character.Player;

/**
 * 타이머 관리와 함께 게임 전반적인 규칙(클리어, 게임오버 등)을 관리하는 관리자 클래스 
 * @author gagip
 *
 */
public class GameManager {
	private static GameManager gm = new GameManager();
	
	// 초기값
	public static final int DEFAULT_HP = 100;
	public static final int DEFAULT_ATTACK = 10;
	public static final int DEFAULT_DEFENSE = 1;
	public static final float DEFAULT_BATTLE_TIME = 1f;
	public static final float DEFAULT_TIMER = 3f;
	
	public Player player;
	
	private GameManager() {
		player = new Player(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENSE, 1000);
	}
	
	public static GameManager getInstance() {
		return gm;
	}
	
	
}
