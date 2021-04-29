package item.consumption;

import character.Player;
import item.Item;

public class RedPortion extends Item implements IConsumption {

	public RedPortion() {
		name = "빨간 포션";
		description = "체력 10 회복";
		hp = 10;
		gold = 100;
	}
	
	@Override
	public void use(Player player) {
		player.setHp(player.getHp() + hp);
	}
}
