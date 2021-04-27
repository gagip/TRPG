package character;

import enemy.Enemy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import place.Place;

/**
 * ������ �����ϴ� ĳ���� Ŭ����
 * @author gagip
 *
 */
public class Player{
	// ���� ����
	private int maxHp;
	private int hp;
	private int maxAttack;
	private int attack;
	private int maxDefense;
	private int defense;
	
	// ��ȭ ����
	private int money;
	
	// ���� ��ġ
	private Place place;
	
	
	// ������
	public Player(int maxHp, int maxAttack, int maxDefense, int money) {
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.maxAttack = maxAttack;
		this.attack = maxAttack;
		this.maxDefense = maxDefense;
		this.defense = maxDefense;
		this.money = money;
	}
	
	
	public void move(Place place) {
		this.place = place;
	}
	
	
	/**
	 * ���� �����ϴ� �޼ҵ� 
	 * @param enemy ������ ����
	 */
	public void attack(Enemy enemy) {
		int damage = this.attack = enemy.getDefense();
		if (damage <= 0) damage = 1;
		
		int enemyCurHp = enemy.getHp();
		enemyCurHp -= damage;
		
		enemy.setHp(enemyCurHp);
	}


	
	
	
	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp > 0 ? hp : 0;
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


	public int getMoney() {
		return money;
	}


	public void setMoney(int money) {
		this.money = money > 0 ? money : 0;
	}


	public int getMaxHp() {
		return maxHp;
	}


	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}


	public int getMaxAttack() {
		return maxAttack;
	}


	public void setMaxAttack(int maxAttack) {
		this.maxAttack = maxAttack;
	}


	public int getMaxDefense() {
		return maxDefense;
	}


	public void setMaxDefense(int maxDefense) {
		this.maxDefense = maxDefense;
	}
}
