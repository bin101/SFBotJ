package de.binary101.core.data.character;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.ItemTypeEnum;
import de.binary101.core.data.item.Item;

public class Backpack {
	
	private final static Logger logger = LogManager.getLogger(Backpack.class);

	@Getter private List<Item> items;
	
	public Boolean getIsFull() {
		Boolean result = false;
		
		if (this.items.stream().filter(item -> item.getType() == ItemTypeEnum.None).count() == 0) {
			result = true;
		}
		
		return result;
	}
	
	public Backpack() {
		this.items = Arrays.asList(new Item[5]);
	}
	
	public int getIndexForLeastValueableItem() {
		int indexForLeastValueableItem = -1;

		indexForLeastValueableItem = items
				.stream()
				.filter(item -> item.getType().getId() >= 1
						&& item.getType().getId() <= 10 && !item.getIsEpic())
				.min((i1, i2) -> Double.compare(i1.getSilverPrice(),
						i2.getSilverPrice())).get().getBackpackIndex();

		return indexForLeastValueableItem;
	}
	
	
	
	public void updateBackpack (Long[] backpackData) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < items.size(); i++) {
			items.set(i, Item.createItem(backpackData, i * 12, i + 1));
			builder.append("\n" + items.get(i).toString());
		}
		
		logger.debug(builder.toString());
	}
}
