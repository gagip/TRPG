package place;

import java.util.ArrayList;
import java.util.List;

import npc.Npc;

/**
 * 장소 클래스
 * @author gagip
 *
 */
public class Place {
	protected String name;
	protected String comment;
	protected Npc npc;
	public List<Place> availablePlace;
	
	public Place() {
		name = "";
		comment = "";
		npc = null;
		availablePlace = new ArrayList<Place>();
	}
	
	public List<Place> getAvailablePlace(){
		return availablePlace;
	}
	
	public String getComment() {
		return comment;
	}
	
	public Npc getNpc() {
		return npc;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
