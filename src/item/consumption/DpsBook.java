package item.consumption;

import character.Player;
import item.Item;
import manager.BattleManager;

public class DpsBook extends Item implements IConsumption {
	
	public DpsBook() {
		name = "공격 속도 비법서";
		description = "공격 속도 1초 더 빨라집니다";
		gold = 2000;
	}
	
	@Override
	public void use(Player player) {
		int cooltime = BattleManager.getInstance().getCooltime();
		BattleManager.getInstance().setCooltime(cooltime + 1000);
	}
}
