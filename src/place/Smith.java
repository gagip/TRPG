package place;

import item.Item;
import npc.Npc;
import npc.SmithNpc;

public class Smith extends Place {

	
	public Smith() {
		this.name = "대장간";
		this.comment = "장비 아이템을 살 수 있는 대장간입니다.\n";
		this.npc = new SmithNpc();
	}

}
