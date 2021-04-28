package item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 아이템을 보관하는 가방 클래스
 * @author gagip
 *
 */
public class Inventory {
	private List<Item> items = new ArrayList<Item>(10);
	
	
	public Inventory() {
		for (int i=0; i<10; i++)
			items.add(null);
	}
	@Override
	public String toString() {
		StringBuffer strbuf = new StringBuffer();
		for (int i=0; i<items.size(); i++) {
			strbuf.append(String.format("%d: %s", 
						i, Objects.isNull(items.get(i)) ? "(비어있음)" : items.get(i) ));
			strbuf.append( (i%2)!=0 ? "\n" : "\t" );
		}
		return strbuf.toString();
	}
	
	public List<Item> getItems() {
		return items;
	}
}
