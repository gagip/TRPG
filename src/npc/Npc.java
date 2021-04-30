package npc;

import java.util.ArrayList;
import java.util.List;

import character.Player;
import item.Item;
import manager.GameManager;

/**
 * Player와 상호작용할 수 있는 NPC 클래스
 * @author gagip
 *
 */
public class Npc {
	// 필드
	protected String name;
	protected String script;
	protected List<Item> displayRack = new ArrayList<Item>();
	
	// 메소드
	public String talk() {
		return String.format("%s: \"%s\"\n", name, script);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public List<Item> getDisplayRack() {
		return displayRack;
	}
	
	
}
