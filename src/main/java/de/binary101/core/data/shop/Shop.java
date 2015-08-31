package de.binary101.core.data.shop;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import de.binary101.core.constants.enums.OwnPlayerSaveEnum;
import de.binary101.core.data.item.Item;

public class Shop {
	@Getter private List<Item> shopItems;

	public Shop() {
		this.shopItems = Arrays.asList(new Item[6]);
	}

	public synchronized void updateShop(Long[] shopData) {
		for (int i = 0; i < shopItems.size(); i++) {
			shopItems.set(i, Item.createItem(shopData, i * 12));
		}
	}
}
