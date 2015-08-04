package de.binary101.core.data.character;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.data.item.Item;

public class Equipment {
	private final static Logger logger = LogManager.getLogger(Equipment.class);
	
	@Getter private List<Item> items;
	
	public Equipment() {
		this.items = Arrays.asList(new Item[10]);
	}
	
	public void updateEquipment(Long[] equipmentData) {		
		for (int i = 0; i < items.size(); i++) {
			items.set(i, Item.createItem(equipmentData, i * 12, i));
		}
	}
}
