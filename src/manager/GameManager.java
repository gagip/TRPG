package manager;

import character.Player;

/**
 * Ÿ�̸� ������ �Բ� ���� �������� ��Ģ(Ŭ����, ���ӿ��� ��)�� �����ϴ� ������ Ŭ���� 
 * @author gagip
 *
 */
public class GameManager {
	private static GameManager gm = new GameManager();
	
	// �ʱⰪ
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
