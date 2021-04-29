package place;

import java.util.ArrayList;
import java.util.List;

/**
 * 장소 클래스
 * @author gagip
 *
 */
public class Place {
	protected String name;
	protected String comment;
	public List<Place> availablePlace;
	
	public Place() {
		name = "";
		comment = "";
		availablePlace = new ArrayList<Place>();
	}
	
	public List<Place> getAvailablePlace(){
		return availablePlace;
	}
	
	public String getComment() {
		return comment;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
