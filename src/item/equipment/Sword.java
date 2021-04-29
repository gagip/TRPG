package item.equipment;

import character.Player;
import item.Item;

public class Sword extends Item implements IEquipment {
	
	public Sword() {
		name = "검";
		description = "공격력 3을 증가 시킵니다";
		part = 2;
		attack = 3;
		gold = 300;
	}

	@Override
	public void equip(Player player) {
		player.setAttack(player.getAttack() + attack);
	}

	@Override
	public void unEquip(Player player) {
		player.setAttack(player.getAttack() - attack);
	}
	
	
}
