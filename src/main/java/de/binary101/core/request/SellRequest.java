package de.binary101.core.request;

import lombok.Getter;
import de.binary101.core.constants.enums.RequestEnum;
import de.binary101.core.data.item.Item;

public class SellRequest extends Request {

	@Getter private int backpackPosition;

	public SellRequest(Item backpackItem) {
		super(RequestEnum.ACT_INVENTORY_CHANGE);

		this.backpackPosition = backpackItem.getSlotIndex();
	}

	@Override
	public String toString() {
		return super.toString() + "2/" + this.backpackPosition + "/4/1";
	}
}
