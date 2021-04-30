package npc;

import character.Player;
import item.Item;
import item.consumption.DpsBook;
import item.consumption.RedPortion;
import item.consumption.WhitePortion;
import manager.GameManager;

/**
 * 잡화점 클래스
 * @author gagip
 *
 */
public class StoreNpc extends Npc implements Itrade {

	public StoreNpc() {
		name = "잡화점 주인";
		script = "살 수 있는 수량은 한정적이니깐 알아서 잘 골라봐";
		
		displayRack.add(new RedPortion());
		displayRack.add(new RedPortion());
		displayRack.add(new RedPortion());
		displayRack.add(new RedPortion());
		displayRack.add(new RedPortion());
		displayRack.add(new WhitePortion());
		displayRack.add(new WhitePortion());
		displayRack.add(new DpsBook());
		displayRack.add(new DpsBook());
		displayRack.add(new DpsBook());
	}
	
	@Override
	public void buy(int invenIdx, Player player) {
		
		// 아이템 가져오고
		Item item = player.getInven().getItem(invenIdx);
		
		if (item != null) {
			// 플레이어에게 지불
			int gold = (int) (item.getGold() * DEPRECIATION);
			player.setGold(player.getGold()+gold);
			
		}
		// 결과 출력
		GameManager.getInstance().printGameInfo("감사합니다. 또 이용해주세요\n");
	}

	@Override
	public void sell(int rackIdx, Player player) {
		int gold = player.getGold();
		Item item = displayRack.get(rackIdx);
		int price = item.getGold();
		
		if (gold >= price) {
			player.setGold(gold-price);
			
			player.getInven().setItem(displayRack.remove(rackIdx));
			GameManager.getInstance().printGameInfo("감사합니다. 또 이용해주세요\n");
		} else {
			GameManager.getInstance().printGameInfo("돈이 없으신거 같네요...\n");
		}
	}
}
