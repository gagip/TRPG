package item;

import character.Player;

/**
 * 소비와 장착 아이템 상위 클래스
 * @author gagip
 *
 */
public class Item {
	protected String name;							// 이름
	protected String description;					// 설명

	public int part;								// 부위
	protected int maxHp;
	protected int hp;
	protected int attack;
	protected int defense;
	protected int gold;
	
	
	@Override
	public String toString() {
		if (name.isEmpty()) return "(비어있음)";
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	
	public String getDescription() {
		return description;
	}

	public int getGold() {
		return gold;
	}
	
	public int getPart() {
		return part;
	}
}
