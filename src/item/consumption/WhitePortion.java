package item.consumption;

import character.Player;
import item.Item;

public class WhitePortion extends Item implements IConsumption {

	public WhitePortion() {
		name = "하얀 포션";
		description = "체력 30 회복";
		hp = 10;
		gold = 350;
	}
	
	@Override
	public void use(Player player) {
		player.setHp(player.getHp() + hp);
	}
}