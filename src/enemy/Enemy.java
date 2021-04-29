package enemy;

import java.lang.annotation.Inherited;

import character.Player;
import manager.DungeonManager;
import place.Dungeon;

/**
 * 몬스터 클래스
 * @author gagip
 *
 */
public class Enemy {
	protected String name;
	protected String passiveDescription;
	protected int maxHp;
	protected int hp;
	protected int attack;
	protected int defense;
	protected int gold;
	
	
	
	public Enemy(int maxHp, int attack, int defense, int gold) {
		this.name = "적";
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attack = attack;
		this.defense = defense;
		this.gold = gold;
	}
	
	
	
	public void attack(Player player) {
		int damage = this.attack = player.getDefense();
		if (damage <= 0) damage = 1;
		
		int playerCurHp = player.getHp();
		playerCurHp -= damage;
		
		player.setHp(playerCurHp);
	}

	
	public void die(Dungeon floor) {
		DungeonManager.getInstance().removeEnemy(floor);;
	}
	
	public void passive(Player player) {}
	
	
	@Override
	public String toString() {
		return name;
	}

	
	public int getMaxHp() {
		return maxHp;
	}


	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}


	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		if (hp > getMaxHp())
			this.hp = getMaxHp();
		else if (hp > 0)
			this.hp = hp;
		else
			this.hp = 0;
	}


	public int getAttack() {
		return attack;
	}


	public void setAttack(int attack) {
		this.attack = attack;
	}


	public int getDefense() {
		return defense;
	}


	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public int getGold() {
		return gold;
	}
	
	public String getPassiveDescription() {
		return passiveDescription;
	}
	
	
}
