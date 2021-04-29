package manager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import enemy.Bat;
import enemy.Enemy;
import enemy.Goblin;
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
		enemy1.offer(new Bat(5, 1, 0, 50));
		enemy1.offer(new Bat(7, 2, 1, 100));
		enemy1.offer(new Bat(15, 2, 1, 100));
		Queue<Enemy> enemy2 = new LinkedList<Enemy>();
		enemy2.offer(new Bat(10, 3, 0, 100));
		enemy2.offer(new Goblin(20, 3, 1, 200));
		enemy2.offer(new Goblin(30, 5, 2, 300));
		
		Dungeon dungeon1 = new Dungeon("던전 1층", enemy1);
		Dungeon dungeon2 = new Dungeon("던전 2층", enemy2);
		// 맵에 몬스터를 배치
		dungeons.add(dungeon1);
		dungeons.add(dungeon2);
		
		// 마지막 항목에는 마을로 돌아갈 수 있게 제어
		dungeons.add(PlaceManager.getInstance().village);
	}
	
	public List<Place> getDungeons(){
		return dungeons;
	}
	
	public void removeEnemy(Dungeon dungeon) {
		int dungeonIdx = dungeons.indexOf(dungeon);
		Dungeon targetDungeon = (Dungeon) dungeons.get(dungeonIdx);
		targetDungeon.getEnemies().poll();
	}
}
