package place;

import npc.InnNpc;
import npc.Npc;

public class Inn extends Place {
	private Village village;
	
	public Inn() {
		this.name = "여관";
		this.comment = "어서오세요. 여긴 모험가들이 휴식할 수 있는 여관입니다.\n";
		this.npc = new InnNpc();
	}
}
