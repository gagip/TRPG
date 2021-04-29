package enemy;

import character.Player;

public class Thief extends Enemy {

	public Thief(int maxHp, int attack, int defense, int gold) {
		super(maxHp, attack, defense, gold);
		this.name = "도적";
		this.passiveDescription = "턴마다 소량의 골드를 훔칩니다";
	}

	@Override
	public void passive(Player player) {
		// 플레이어의 골드를 훔침
		int gold = player.getGold();
		gold -= 10;
		player.setGold(gold);
	}
}
