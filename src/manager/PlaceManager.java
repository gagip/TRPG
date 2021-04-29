package manager;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import enemy.Enemy;
import place.Dungeon;
import place.Inn;
import place.Smith;
import place.Store;
import place.Village;

public class PlaceManager {
	private static PlaceManager pm = new PlaceManager();
	private GameManager gm;
	private DungeonManager dm;
	private ScriptManager script;
	
	public Village village;
	public Inn inn;
	public Smith smith;
	public Store store;
	
	
	public Dungeon tempDungeon;
	
	private PlaceManager() {
		this.gm = GameManager.getInstance();
		this.script = ScriptManager.getInstance();
		
		// 장소 객체 생성 
		this.village = new Village();
		this.inn = new Inn();
		this.smith = new Smith();
		this.store = new Store();
		
		this.dm = DungeonManager.getInstance();
		this.tempDungeon = new Dungeon();
	}
	
	public static PlaceManager getInstance() {
		return pm;
	}
	
	
	public void init() {
		// 이동 가능한 지역 설정
		village.availablePlace.add(inn);
		village.availablePlace.add(smith);
		village.availablePlace.add(store);
		village.availablePlace.add(tempDungeon);
		inn.availablePlace.add(village);
		smith.availablePlace.add(village);
		store.availablePlace.add(village);
		
		tempDungeon.availablePlace.add(village);
	}
	
//	
//	/**
//	 * 해당 층 던전으로 이동
//	 * @param floor
//	 */
//	public void goToDungeon(int floor) {
//		List<Dungeon> dungeons = dm.getDungeons();
//		try {
//			Dungeon toDungeon = dungeons.get(floor);
//			gm.player.setWhere(toDungeon);
//		} catch (Exception e) {}
//	}
}
