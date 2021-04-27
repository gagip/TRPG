package place;

import java.util.ArrayList;
import java.util.List;

import npc.Inn;
import npc.Npc;
import npc.Smith;
import npc.Store;


/**
 * NPC들이 존재하는 마을 클래스
 * @author gagip
 *
 */
public class Village extends Place {
	private List<Npc> NpcList = new ArrayList<Npc>();
	
	public Village() {
		NpcList.add(new Inn());
		NpcList.add(new Smith());
		NpcList.add(new Store());
	}
	
	
}
