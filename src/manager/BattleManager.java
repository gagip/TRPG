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
	private static GameManager gm;
	private static ScriptManager script;
	
	private static final int BATTLE_TIME = 5000;
	private int cooltime = 0;
	
	private BattleManager() {
		gm = GameManager.getInstance();
		script = ScriptManager.getInstance();
	}
	
	public static BattleManager getInstance() {
		return bm;
	}
	
	
	public void startBattle(Player player, Enemy enemy) {
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				boolean isBattle = true;
				int turn = 0;
				
				while (isBattle) {
					// 쿨타임
					try {
						Thread.sleep(BATTLE_TIME - cooltime);
					} catch (InterruptedException e) {e.printStackTrace();}
					
					// 서로 공격
					player.attack(enemy);
					enemy.attack(player);
					enemy.passive(player);
					
					// 결과 출력
					turn += 1;
					script.printBattle(player, enemy, turn);
					
					// 체력 검사 -> 조건 만족 시 전투 종료
					if (player.getHp() <= 0 || enemy.getHp() <= 0) {
						isBattle = false;
						if (player.getHp() <= 0)		endBattle(enemy, player);
						else 							endBattle(player, enemy);
					}
			}
		}});
		
		thread.start();
	}
	
	
	public <W, L> void endBattle(W winner, L loser) {
		if (winner instanceof Player) {
			// 보상 획득
			if (loser instanceof Enemy) {
				Player player = (Player) winner;
				Enemy enemy = (Enemy) loser;
				gm.printGameInfo("전투에서 승리하셨습니다.\n");
				gm.win(player, enemy);
			}
			
		} else {
			// 플레이어 패배
			gm.printGameInfo("전투에서 패배하셨습니다. \n");
			gm.defeat();
			
		}
		script.endBattle();
	}
	
	
	public int getCooltime() {
		return cooltime;
	}
	
	public void setCooltime(int cooltime) {
		if (cooltime > BATTLE_TIME) 	this.cooltime = BATTLE_TIME;
		else if (cooltime < 0) 			this.cooltime = 0;
		else							this.cooltime = cooltime;
	}
}
