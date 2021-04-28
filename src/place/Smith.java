package place;

import npc.Npc;
import npc.SmithNpc;

public class Smith extends Place implements Itrade {
	private Village village;
	private Npc npc;
	
	public Smith() {
		this.name = "대장간";
		this.comment = "어서오게, 우리 마을에만 있는 무기와 장비를 둘러보게\n";
		this.npc = new SmithNpc();
	}
}
