package manager;

import character.Player;
import enemy.Enemy;

/**
 * Player와 Enemy의 전투를 관리하는 클래스
 * @author gagip
 *
 */
public class BattleManager {
	private static BattleManager bm = new BattleManager();
	
	private static final int BATTLE_TIME = 1000;
	
	private BattleManager() {
		
	}
	
	public static BattleManager getInstance() {
		return bm;
	}
	
	
	public void startBattle(Player player, Enemy enemy) {
		Thread battleThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					player.attack(enemy);
					enemy.attack(player);
					Thread.sleep(BATTLE_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		battleThread.setName("battle");
		battleThread.run();
	}
	
}
