package manager;

import java.util.List;

import character.Player;
import character.PlayerAction;
import enemy.Enemy;
import item.Item;
import item.consumption.IConsumption;
import item.equipment.IEquipment;
import javafx.application.Platform;
import place.Place;


/**
 * 게임 흐름 및 대사를 제어하는 클래스
 * @author gagip
 *
 */
public class ScriptManager {
	private static ScriptManager sm = new ScriptManager();
	
	private GameManager gm = GameManager.getInstance();
		
	private ScriptManager() {}
	
	public static ScriptManager getInstance() {
		return sm;
	}
	
	public void idle(PlayerAction action) {
		gm.printGameInfo(String.format("%s(을)를 선택\n\n\n", action));
	}
	
	public void move(Place place) {
		gm.printGameInfo(String.format("플레이어 %s(으)로 이동\n\n\n", place));
		gm.printGameInfo(place.getComment());
	}
	
	
	public <T> String printChoice(List<T> t) {
		StringBuffer strBuf = new StringBuffer();
		
		for (int i=0; i<t.size(); i++) {
			if (t.get(i) == null) continue;
			strBuf.append(String.format("[%d] %s ", i, t.get(i).toString()));
		}
		strBuf.append("\n");
		
		return strBuf.toString();
	}
	
	public String printItem(List<Item> items) {
		StringBuffer strBuf = new StringBuffer();
		
		for (int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			strBuf.append(String.format("[%d] %s (%s) %dG\n"
										, i, item, item.getDescription(), item.getGold()));
		}
		strBuf.append("\n");
		
		return strBuf.toString();
	}
	
	public void startScene() {
		gm.printGameInfo("안녕하세요. TRPG에 오신 여러분을 환영합니다.\n");
	}
	
	public void printBattle(Player player, Enemy enemy, int turn) {
		StringBuffer strBuf = new StringBuffer();
		
		strBuf.append(String.format("----------%d번째 턴--------\n", turn));
		strBuf.append(String.format(
									"player               enemy\n"
									+ "%d      현재체력       %d\n"
									+ "%d       공격력        %d\n"
									+ "%d       방어력        %d\n"
									, player.getHp(), enemy.getHp()
									, player.getAttack(), enemy.getAttack()
									, player.getDefense(), enemy.getDefense()				
					));
		
		Platform.runLater(() -> gm.printGameInfo(strBuf.toString()));
	}
	
	public void endBattle() {
		gm.printGameInfo("아무키나 입력해주세요\n");
		gm.printGameInfo("\n\n\n");
	}
	
	public void usedItem(Item item) {
		if (item instanceof IEquipment) {
			gm.printGameInfo(String.format("%s(을)를 장착하였습니다.\n", item));
		}
		else if (item instanceof IConsumption) {
			gm.printGameInfo(String.format("%s(을)를 사용하였습니다.\n", item));
		}
	}
}
