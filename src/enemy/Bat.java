package enemy;

import character.Player;

public class Bat extends Enemy {

	public Bat(int maxHp, int attack, int defense, int gold) {
		super(maxHp, attack, defense, gold);
		this.name = "박쥐";
		this.passiveDescription = "한 턴에 두 번 공격합니다";
	}

	
	@Override
	public void passive(Player player) {
		// 한 번 더 공격
		attack(player);
	}
}
