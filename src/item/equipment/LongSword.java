package item.equipment;

import character.Player;
import item.Item;

public class LongSword extends Item implements IEquipment {

	public LongSword() {
		name = "장검";
		description = "공격력 7을 증가 시킵니다";
		part = 2;
		attack = 7;
		gold = 800;
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
