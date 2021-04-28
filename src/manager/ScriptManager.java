package manager;

import application.Controller;
import character.Player;
import javafx.beans.property.StringProperty;

/**
 * 게임 흐름 및 대사를 제어하는 클래스
 * @author gagip
 *
 */
public class ScriptManager {
	private static ScriptManager sm = new ScriptManager();
	
	private GameManager gm = GameManager.getInstance();
	private String script;
		
	private ScriptManager() {}
	
	public static ScriptManager getInstance() {
		return sm;
	}
	
	public void startScene() {
		
	}
	
	public String getScript() {
		return script;
	}

}
