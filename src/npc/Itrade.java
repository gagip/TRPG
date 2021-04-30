package npc;

import character.Player;
import item.Inventory;
import item.Item;

public interface Itrade {
	static final float DEPRECIATION = 0.4f;			// 감가상각비(?)
	
	public void buy(int invenIdx, Player player);
	public void sell(int rackIdx, Player player);
}
