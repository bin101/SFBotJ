package de.binary101.core.request;

import lombok.Getter;
import de.binary101.core.constants.enums.ItemTypeCharacterSlotEnum;
import de.binary101.core.constants.enums.ItemTypeEnum;
import de.binary101.core.constants.enums.RequestEnum;
import de.binary101.core.data.item.Item;

public class EquipRequest extends Request {

	@Getter private int backpackPosition;
	@Getter private int characterSlotPosition;

	public EquipRequest(Item backpackItem) {
		super(RequestEnum.ACT_INVENTORY_CHANGE);

		this.backpackPosition = backpackItem.getBackpackIndex() + 1;
		this.characterSlotPosition = backpackItem.getType().getId() >= ItemTypeEnum.MirrorOrKey.getId() ? -1
				: ItemTypeCharacterSlotEnum.fromItemTypeEnum(backpackItem.getType()).getId();
	}

	@Override
	public String toString() {
		return super.toString() + "2/" + this.backpackPosition + "/1/" + this.characterSlotPosition;
	}

}
