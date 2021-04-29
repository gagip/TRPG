package manager;

import character.Player;
import character.PlayerState;
import enemy.Enemy;
import javafx.application.Platform;
import place.Smith;

/**
 * Player와 Enemy의 전투를 관리하는 클래스
 * @author gagip
 *
 */
public class BattleManager {
	private static BattleManager bm = new BattleManager();
	private static GameManager gm;
	private static ScriptManager script;
	
	private static final int BATTLE_TIME = 1000;
	
	public boolean isBattle = false;
	
	private BattleManager() {
		gm = GameManager.getInstance();
		script = ScriptManager.getInstance();
	}
	
	public static BattleManager getInstance() {
		return bm;
	}
	
	
	public void startBattle(Player player, Enemy enemy) {
		isBattle = true;
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while (isBattle) {
					// 서로 공격
					player.attack(enemy);
					enemy.attack(player);
					
					// 결과 출력
					script.printBattle(player, enemy);
					
					// 체력 검사 -> 조건 만족 시 전투 종료
					if (player.getHp() <= 0 || enemy.getHp() <= 0) {
						if (player.getHp() <= 0)		endBattle(enemy, player);
						else 							endBattle(player, enemy);
						
						isBattle = false;
					}
					
					// 쿨타임
					try {
						Thread.sleep(BATTLE_TIME);
					} catch (InterruptedException e) {e.printStackTrace();}
			}
				
		}
		});
		
		thread.start();
	}
	
	
	public <W, L> void endBattle(W winner, L loser) {
		if (winner instanceof Player) {
			// 보상 획득
			if (loser instanceof Enemy) {
				gm.printGameInfo("전투에서 승리하셨습니다.\n");
				Player player = (Player) winner;
				Enemy enemy = (Enemy) loser;
				gm.win(player, enemy);
				
			}
			
		} else {
			// 플레이어 패배
			gm.printGameInfo("전투에서 패배하셨습니다. \n");
			gm.defeat();
			
		}
		script.endBattle();
	}
	
	
}
