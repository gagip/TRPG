package enemy;

import character.Player;


public class Goblin extends Enemy {

	
	public Goblin(int maxHp, int attack, int defense, int gold) {
		super(maxHp, attack, defense, gold);
		this.name = "고블린";
		this.passiveDescription = "턴마다 체력이 소폭 회복합니다";
	}
	
	@Override
	public void passive(Player player) {
		int hp = getHp();
		int heal = maxHp / 10;
		
		hp += heal;
		setHp(hp);
	}
	
}
