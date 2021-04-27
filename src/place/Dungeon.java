package place;

import java.util.Stack;

import enemy.Enemy;

public class Dungeon extends Place {
	
	private Stack<Enemy> enemies = new Stack<Enemy>();
	
	public Dungeon(String name, Stack<Enemy> enemies) {
		this.name = name;
		this.enemies = enemies;
	}
	
	@Override
	public String getName() {
		return String.format("´øÀü %sÃş", name);
	}

	public Stack<Enemy> getEnemies() {
		return enemies;
	}

	
}
