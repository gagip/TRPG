package item.equipment;

import java.util.List;

import character.Player;
import item.Item;

public class Armor extends Item implements IEquipment{

	public Armor() {
		name = "갑옷";
		description = "최대체력 20, 방어력 1 증가";
		part = 1;
		maxHp = 20;
		defense = 1;
		gold = 350;
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
