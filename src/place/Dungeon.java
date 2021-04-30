package place;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import enemy.Enemy;
import manager.DungeonManager;

/**
 * 몬스터들이 있는 던전 클래스
 * @author gagip
 *
 */
public class Dungeon extends Place {
	
	private Queue<Enemy> enemies = new LinkedList<Enemy>();
	private boolean canEnter = false;
	
	// 임시 던전
	public Dungeon() {
		this.name = "던전 입구";
	}
	
	public Dungeon(String name, Queue<Enemy> enemies) {
		this.name = name;
		this.enemies = enemies;
		this.comment = "무서운 던전이군요\n";
	}
	
	public Enemy getMonster() {
		if (enemies.size() > 0)
			return enemies.peek();
		else 
			return null;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public Queue<Enemy> getEnemies() {
		return enemies;
	}
	
	public boolean getCanEnter(){
		return canEnter;
	}
	
	public void setCanEnter() {
		canEnter = true;
	}
}
