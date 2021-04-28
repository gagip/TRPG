package manager;


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
	
	
	
	public String action() {
		return "[1] 이동 [2] 휴식\n";
	}
	
	public String startScene() {
		return "안녕하세요. TRPG에 오신 여러분을 환영합니다.\n"
				+ "어떤 일을 하시겠습니까?\n";
	}
	
}
