package npc;

import character.Player;
import manager.GameManager;

/**
 * 여관 주인 클래스
 * @author gagip
 *
 */
public class InnNpc extends Npc{

	private int charge = 100;
	
	public InnNpc() {
		name = "여관 주인";
		script = "숙박비는 50골드이고, 잃은 체력에 비례해 타이머가 차감됩니다. (hp 1당 1초 차감)";
	}
	
	public void rest(Player player) {
		// 휴식
		int gold = player.getGold();
		
		if (gold>=charge) {
			// 비용 지불
			player.setGold(gold-charge);
			// TODO 타이머 차감
			int timer = GameManager.getInstance().getTimer();
			int gapHp = player.getMaxHp() - player.getHp();
			
			GameManager.getInstance().setTimer(timer-gapHp);
			// 체력 풀 회복
			player.setHp(player.getMaxHp());
			
			GameManager.getInstance().printGameInfo("감사합니다. 또 이용해주세요\n");
		} else {
			GameManager.getInstance().printGameInfo("돈이 없네요... 골드를 모으시고 해주세요\n");
		}
	}

}
