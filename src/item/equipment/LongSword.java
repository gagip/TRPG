package item.equipment;

import java.util.List;

import character.Player;
import item.Item;

public class LongSword extends Item implements IEquipment {

	public LongSword() {
		name = "장검";
		description = "공격력 300을 증가 시킵니다";
		part = 2;
		attack = 300;
		gold = 10000;
	}
	
	@Override
	public void equip(Player player) {
		List<IEquipment> equipment = player.getEquipment();
		
		IEquipment usedItem = equipment.get(part);
		
		// 플레이어가 뭘 장착하고 있다면
		if (usedItem != null)
			usedItem.unEquip(player);
		player.setEquipment(part, this);
		
		player.setAttack(player.getAttack() + attack);
	}

	@Override
	public void unEquip(Player player) {
		player.setAttack(player.getAttack() - attack);
		
		player.getInven().setItem(this);
	}

}
