package manager;


/**
 * Character�� Enemy ���� ������ �����ϴ� Ŭ����
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
