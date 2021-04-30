package npc;

import character.Player;
import item.Inventory;
import item.Item;
import item.equipment.Armor;
import item.equipment.EnchantArmor;
import item.equipment.EnchantHat;
import item.equipment.Hat;
import item.equipment.LongSword;
import item.equipment.Sword;
import manager.GameManager;

/**
 * 대장장이 클래스
 * @author gagip
 *
 */
public class SmithNpc extends Npc implements Itrade {

	
	
	public SmithNpc() {
		name = "대장장이";
		script = "...";
		
		displayRack.add(new Sword());
		displayRack.add(new LongSword());
		displayRack.add(new Hat());
		displayRack.add(new EnchantHat());
		displayRack.add(new Armor());
		displayRack.add(new EnchantArmor());
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
