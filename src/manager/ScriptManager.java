package manager;

import java.util.List;

import character.PlayerAction;
import character.PlayerState;
import javafx.print.PrintColor;
import place.Dungeon;
import place.Place;
import place.Village;



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
		gm.printGameInfo(String.format("%s(을)를 선택\n", action));
	}
	
	public void move(Place place) {
		gm.printGameInfo(String.format("플레이어 %s(으)로 이동\n", place));
		gm.printGameInfo(place.getComment());
	}
	

	
	public <T> String printChoice(List<T> t) {
		StringBuffer strBuf = new StringBuffer();
		for (int i=0; i<t.size(); i++) {
			strBuf.append(String.format("[%d] %s ", i, t.get(i).toString()));
		}
		strBuf.append("\n");
		
		return strBuf.toString();
	}
	
	public void startScene() {
		gm.printGameInfo("안녕하세요. TRPG에 오신 여러분을 환영합니다.\n");
	}
	
}
