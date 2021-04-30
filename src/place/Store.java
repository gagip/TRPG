package place;

import npc.Npc;
import npc.StoreNpc;

public class Store extends Place {
	
	public Store() {
		this.name = "잡화점";
		this.comment = "소모용 아이템을 파는 잡화점입니다.\n";
		this.npc = new StoreNpc();
	}
}
