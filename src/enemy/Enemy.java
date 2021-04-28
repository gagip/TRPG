package enemy;

import character.Player;

/**
 * 몬스터 클래스
 * @author gagip
 *
 */
public class Enemy {
	protected String name;
	protected int maxHp;
	protected int hp;
	protected int maxAttack;
	protected int attack;
	protected int maxDefense;
	protected int defense;
	
	
	public Enemy(int maxHp, int maxAttack, int maxDefense) {
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.maxAttack = maxAttack;
		this.attack = maxAttack;
		this.maxDefense = maxDefense;
		this.defense = maxDefense;
	}
	
	
	public void attack(Player player) {
		int damage = this.attack = player.getDefense();
		if (damage <= 0) damage = 1;
		
		int playerCurHp = player.getHp();
		playerCurHp -= damage;
		
		player.setHp(playerCurHp);
	}

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
		this.hp = hp > 0 ? hp : 0;
	}


	public int getMaxAttack() {
		return maxAttack;
	}


	public void setMaxAttack(int maxAttack) {
		this.maxAttack = maxAttack;
	}


	public int getAttack() {
		return attack;
	}


	public void setAttack(int attack) {
		this.attack = attack;
	}


	public int getMaxDefense() {
		return maxDefense;
	}


	public void setMaxDefense(int maxDefense) {
		this.maxDefense = maxDefense;
	}


	public int getDefense() {
		return defense;
	}


	public void setDefense(int defense) {
		this.defense = defense;
	}
}
