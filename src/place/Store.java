package place;

import npc.Npc;
import npc.StoreNpc;

public class Store extends Place implements Itrade{
	private Village village;
	private Npc npc;
	
	public Store() {
		this.name = "잡화점";
		this.comment = "필요하신 물건이 있으면 말씀해주세요\n";
		this.npc = new StoreNpc();
	}
}
