package de.binary101.core.data.character;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import de.binary101.core.data.item.Item;

public class Equipment {
	@Getter private List<Item> items;

	public Equipment() {
		this.items = Arrays.asList(new Item[10]);
	}

	public synchronized void updateEquipment(Long[] equipmentData) {
		for (int i = 0; i < items.size(); i++) {
			items.set(i, Item.createItem(equipmentData, i * 12));
		}
	}
}
