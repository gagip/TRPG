package item.equipment;

import java.util.List;

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
		List<IEquipment> equipment = player.getEquipment();
		
		IEquipment usedItem = equipment.get(part);
		
		// 플레이어가 뭘 장착하고 있다면
		if (usedItem != null)
			usedItem.unEquip(player);
		player.setEquipment(part, this);

		player.setDefense(player.getDefense() + defense);
	}

	@Override
	public void unEquip(Player player) {
		player.setDefense(player.getDefense() - defense);
		
		player.getInven().setItem(this);
	}
}
