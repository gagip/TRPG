package character;

import java.util.ArrayList;
import java.util.List;

import enemy.Enemy;
import item.Inventory;
import item.Item;
import item.consumption.IConsumption;
import item.equipment.IEquipment;
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
	private int attack;
	private int defense;
	private PlayerState state;
	
	// 재화 변수
	private int gold;
	
	// 참조 변수
	private Place place;
	private Inventory inven;
	public Object connect;
	
	// 머리, 옷, 무기
	private List<IEquipment> equipments = new ArrayList<IEquipment>(3);

	public Player(int maxHp, int attack, int defense, int gold) {
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attack = attack;
		this.defense = defense;
		this.gold = gold;
		
		inven = new Inventory();
		for (int i=0; i<3; i++)
			equipments.add(null);
	}
	
	
	/**
	 * 장소 이동
	 * @param place
	 */
	public void move(Place place) {
		this.place = place;
	}
	
	
	
	/**
	 * 몬스터를 공격
	 * @param enemy
	 */
	public void attack(Enemy enemy) {
		int damage = this.attack - enemy.getDefense();
		if (damage <= 0) damage = 1;
		
		int enemyCurHp = enemy.getHp();
		enemyCurHp -= damage;
		
		enemy.setHp(enemyCurHp);
	}

	
	public void equip(IEquipment equipment) {
		equipment.equip(this);
	}
	
	public void unequip(IEquipment equipment) {
		equipment.unEquip(this);
	}
	
	public void use(IConsumption consumption) {
		consumption.use(this);
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


	public void setGold(int gold) {
		this.gold = gold > 0 ? gold : 0;
	}


	public int getMaxHp() {
		return maxHp;
	}


	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	
	public Inventory getInven() {
		return inven;
	}
	
	
	public Place getWhere() {
		return place;
	}


	public PlayerState getState() {
		return state;
	}


	public void setState(PlayerState state) {
		this.state = state;
	}
	
	
	public void setWhere(Place place) {
		this.place = place;
	}
	
	
	public void setEquipment(int idx, IEquipment equipment) {
		equipments.set(idx, equipment);
	}
	
	
	public List<IEquipment> getEquipment() {
		return equipments;
	}
}
