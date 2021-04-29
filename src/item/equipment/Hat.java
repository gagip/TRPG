package item.equipment;

import character.Player;
import item.Item;

public class Hat extends Item implements IEquipment {
	
	public Hat() {
		name = "모자";
		description = "방어력 1을 증가";
		part = 0;
		defense = 1;
		gold = 200;
	}

	@Override
	public void equip(Player player) {
		player.setDefense(player.getDefense() + defense);
	}

	@Override
	public void unEquip(Player player) {
		player.setDefense(player.getDefense() - defense);
	}
}
