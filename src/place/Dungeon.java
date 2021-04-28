package place;

import java.util.Stack;

import enemy.Enemy;

/**
 * 몬스터들이 있는 던전 클래스
 * @author gagip
 *
 */
public class Dungeon extends Place {
	
	private Stack<Enemy> enemies = new Stack<Enemy>();
	
	public Dungeon(String name, Stack<Enemy> enemies) {
		this.name = name;
		this.enemies = enemies;
		this.comment = "무서운 던전이군요\n";
	}
	
	@Override
	public String toString() {
		return String.format("던전 %s층", name);
	}

	public Stack<Enemy> getEnemies() {
		return enemies;
	}

	
}
