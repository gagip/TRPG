package item.equipment;

import character.Player;
import item.Item;

public class EnchantArmor extends Item implements IEquipment {

	public EnchantArmor() {
		name = "강화 갑옷";
		description = "최대체력 70, 방어력 2 증가";
		part = 1;
		maxHp = 70;
		defense = 2;
		gold = 1100;
	}

	@Override
	public void equip(Player player) {
		player.setMaxHp(player.getMaxHp() + maxHp);
		player.setDefense(player.getDefense() + defense);
	}

	@Override
	public void unEquip(Player player) {
		player.setMaxHp(player.getMaxHp() - maxHp);
		player.setDefense(player.getDefense() - defense);
	}

}
