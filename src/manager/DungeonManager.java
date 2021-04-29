package manager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import enemy.Enemy;
import place.Dungeon;
import place.Place;

/**
 * 던전 및 몬스터를 관리하는 클래스
 * @author gagip
 *
 */
public class DungeonManager {
	private static DungeonManager dm = new DungeonManager();
	
	private List<Place> dungeons = new ArrayList<Place>();
	
	public static DungeonManager getInstance() {
		return dm;
	}
	
	private DungeonManager() {
		// TODO 몬스터 DB 연동		
		// 몬스터 파티 생성
		Queue<Enemy> enemy1 = new LinkedList<Enemy>();
		enemy1.offer(new Enemy(5, 1, 0));
		enemy1.offer(new Enemy(10, 2, 1));
		enemy1.offer(new Enemy(15, 2, 1));
		Queue<Enemy> enemy2 = new LinkedList<Enemy>();
		enemy2.offer(new Enemy(10, 1, 0));
		enemy2.offer(new Enemy(5, 3, 1));
		enemy2.offer(new Enemy(10, 2, 1));
		
		Dungeon dungeon1 = new Dungeon("던전 1층", enemy1);
		Dungeon dungeon2 = new Dungeon("던전 2층", enemy2);
		// 맵에 몬스터를 배치
		dungeons.add(dungeon1);
		dungeons.add(dungeon2);
		
		dungeons.add(PlaceManager.getInstance().village);
	}
	
	public List<Place> getDungeons(){
		return dungeons;
	}
}
