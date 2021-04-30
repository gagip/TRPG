package enemy;

import character.Player;
import manager.GameManager;
import place.Dungeon;

public class Boss extends Enemy {

	public Boss(int maxHp, int attack, int defense, int gold) {
		super(maxHp, attack, defense, gold);
		this.name = "보스";
		this.passiveDescription = "한 턴에 3번 공격하고, 소량 회복합니다.";
	}

	
	@Override
	public void passive(Player player) {
		// 한 번 더 공격
		attack(player);
		attack(player);
		
		// hp 회복
		int hp = getHp();
		int heal = maxHp / 10;
		
		hp += heal;
		setHp(hp);
	}
	
	@Override
	public void die(Dungeon floor) {
		super.die(floor);
		GameManager.getInstance().gameClear();
	}
}
