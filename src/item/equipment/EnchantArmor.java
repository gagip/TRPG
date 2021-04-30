package item.equipment;

import java.util.List;

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
		List<IEquipment> equipment = player.getEquipment();
		
		IEquipment usedItem = equipment.get(part);
		
		// 플레이어가 뭘 장착하고 있다면
		if (usedItem != null)
			usedItem.unEquip(player);
		player.setEquipment(part, this);

		player.setMaxHp(player.getMaxHp() + maxHp);
		player.setDefense(player.getDefense() + defense);
	}

	@Override
	public void unEquip(Player player) {
		player.setMaxHp(player.getMaxHp() - maxHp);
		player.setDefense(player.getDefense() - defense);
		
		player.getInven().setItem(this);
	}

}
