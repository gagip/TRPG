package npc;

/**
 * Character와 상호작용 가능한 NPC 추상 클래스
 * @author gagip
 *
 */
public abstract class Npc {
	// 필드
	
	
	// 메소드
	public abstract void talk();				// 대화
	public abstract void interact();			// 상호작용 (NPC마다 다름)
}
