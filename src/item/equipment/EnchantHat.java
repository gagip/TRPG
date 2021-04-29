package item.equipment;

import character.Player;
import item.Item;

public class EnchantHat extends Item implements IEquipment {
	
	public EnchantHat() {
		name = "마법 모자";
		description = "방어력 3을 증가";
		part = 0;
		defense = 3;
		gold = 600;
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
