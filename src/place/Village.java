package place;

import java.util.ArrayList;
import java.util.List;

import npc.InnNpc;
import npc.Npc;
import npc.SmithNpc;
import npc.StoreNpc;


/**
 * NPC들이 존재하는 마을 클래스
 * @author gagip
 *
 */
public class Village extends Place {
	
	public Village() {
		comment = "한적한 마을입니다\n";
		Inn inn = new Inn();
		Smith smith = new Smith();
		Store store = new Store();
		availablePlace.add(inn);
		availablePlace.add(smith);
		availablePlace.add(store);
	}
	
	@Override
	public String toString() {
		return "마을";
	}
}
