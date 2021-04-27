package manager;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import enemy.Enemy;

public class DungeonManager {
	private static DungeonManager dm = new DungeonManager();
	
	private Map<String, Queue<Enemy>> dungeons = new TreeMap<String, Queue<Enemy>>();
	
	
	private DungeonManager() {
		// TODO 데이터 베이스 만들기
		// 각 층마다 몬스터들 설정
		Queue<Enemy> enemy1 = new LinkedList<Enemy>();
		enemy1.offer(new Enemy(5, 1, 0));
		enemy1.offer(new Enemy(10, 2, 1));
		enemy1.offer(new Enemy(15, 2, 1));
		Queue<Enemy> enemy2 = new LinkedList<Enemy>();
		enemy2.offer(new Enemy(10, 1, 0));
		enemy2.offer(new Enemy(5, 3, 1));
		enemy2.offer(new Enemy(10, 2, 1));
		
		// 던전에 몬스터들 배치
		dungeons.put("1", enemy1);
		dungeons.put("2", enemy2);
	}
	
	public static DungeonManager getInstance() {
		return dm;
	}
	
	
}
