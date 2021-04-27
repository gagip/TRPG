package manager;


/**
 * Character와 Enemy 간의 전투를 관리하는 클래스
 * @author gagip
 *
 */
public class BattleManager {
	private static BattleManager bm = new BattleManager();
	

	
	private BattleManager() {
		
	}
	
	public static BattleManager getInstance() {
		return bm;
	}
	
	
}
