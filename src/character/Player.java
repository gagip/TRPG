package character;

import enemy.Enemy;
import item.Inventory;
import manager.GameManager;
import place.Place;

/**
 * 플레이어가 제어하는 클래스
 * @author gagip
 *
 */
public class Player{
	// 능력치 변수
	private int maxHp;
	private int hp;
	private int maxAttack;
	private int attack;
	private int maxDefense;
	private int defense;
	
	// 재화 변수
	private int money;
	
	// 참조 변수
	private Place place;
	private Inventory inven;
	private GameManager gm;
	
	public Player(int maxHp, int maxAttack, int maxDefense, int money) {
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.maxAttack = maxAttack;
		this.attack = maxAttack;
		this.maxDefense = maxDefense;
		this.defense = maxDefense;
		this.money = money;
		
		inven = new Inventory();
		gm = GameManager.getInstance();
	}
	
	
	public void move(Place place) {
		this.place = place;
	}
	
	
	public void attack(Enemy enemy) {
		int damage = this.attack = enemy.getDefense();
		if (damage <= 0) damage = 1;
		
		int enemyCurHp = enemy.getHp();
		enemyCurHp -= damage;
		
		enemy.setHp(enemyCurHp);
	}


	@Override
	public String toString() {
		return String.format(
				"체력: %d/%d\n"
				+ "공격력: %d\n"
				+ "방어력: %d\n"
				+ "돈: %d"
				, hp, maxHp
				, attack
				, defense
				, money);
	}
	
	
	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp > 0 ? hp : 0;
		gm.controllor.setPlayerProperty(this);
	}


	public int getAttack() {
		return attack;
	}


	public void setAttack(int attack) {
		this.attack = attack;
		gm.controllor.setPlayerProperty(this);
	}


	public int getDefense() {
		return defense;
	}


	public void setDefense(int defense) {
		this.defense = defense;
		gm.controllor.setPlayerProperty(this);
	}


	public int getMoney() {
		return money;
	}


	public void setMoney(int money) {
		this.money = money > 0 ? money : 0;
		gm.controllor.setPlayerProperty(this);
	}


	public int getMaxHp() {
		return maxHp;
	}


	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
		gm.controllor.setPlayerProperty(this);
	}


	public int getMaxAttack() {
		return maxAttack;
	}


	public void setMaxAttack(int maxAttack) {
		this.maxAttack = maxAttack;
		gm.controllor.setPlayerProperty(this);
	}


	public int getMaxDefense() {
		return maxDefense;
	}


	public void setMaxDefense(int maxDefense) {
		this.maxDefense = maxDefense;
		gm.controllor.setPlayerProperty(this);
	}
	
	public Inventory getInven() {
		return inven;
	}
}
