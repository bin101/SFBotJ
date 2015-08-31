package de.binary101.core.request;

import de.binary101.core.constants.enums.RequestEnum;
import de.binary101.core.data.item.Item;

public class BuyShopItemRequest extends Request {

	private int shopType;
	private int shopItemPosition;
	private int backpackItemPosition;

	public BuyShopItemRequest(Boolean isWeaponShop, Item shopItem, Item backpackItem) {
		super(RequestEnum.ACT_INVENTORY_CHANGE);

		this.shopType = isWeaponShop ? 3 : 4;
		this.shopItemPosition = shopItem.getSlotIndex();
		this.backpackItemPosition = backpackItem.getSlotIndex();
	}

	@Override
	public String toString() {
		return super.toString() + this.shopType + "/" + this.shopItemPosition + "/2/" + this.backpackItemPosition;
	}

}
