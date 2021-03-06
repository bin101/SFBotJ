package de.binary101.core.data.character;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import de.binary101.core.constants.enums.ItemTypeEnum;
import de.binary101.core.data.item.Item;

public class Backpack {
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

	public Item getLeastValueableItem() {
		Optional<Item> leastValueableItem = null;

		leastValueableItem = items.stream()
				.filter(item -> item.getType().getId() >= 1 && item.getType().getId() <= 10 && !item.getIsEpic())
				.min((i1, i2) -> Double.compare(i1.getSilverPrice(), i2.getSilverPrice()));

		return leastValueableItem.isPresent() ? leastValueableItem.get() : null;
	}

	public Item getEmptyItem() {
		Optional<Item> emptySlot = items.stream().filter(item -> item.getType() == ItemTypeEnum.None).findFirst();

		return emptySlot.isPresent() ? emptySlot.get() : null;
	}

	public synchronized void updateBackpack(Long[] backpackData) {
		for (int i = 0; i < items.size(); i++) {
			items.set(i, Item.createItem(backpackData, i * 12));
		}
	}
}
