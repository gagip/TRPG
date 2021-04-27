package character;

import enemy.Enemy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import place.Place;

/**
 * 유저가 제어하는 캐릭터 클래스
 * @author gagip
 *
 */
public class Player{
	// 전투 변수
	private int maxHp;
	private int hp;
	private int maxAttack;
	private int attack;
	private int maxDefense;
	private int defense;
	
	// 재화 변수
	private int money;
	
	// 현재 위치
	private Place place;
	
	
	// 생성자
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
	 * 적을 공격하는 메소드 
	 * @param enemy 공격할 몬스터
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
