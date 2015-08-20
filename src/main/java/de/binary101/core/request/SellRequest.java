package de.binary101.core.request;

import lombok.Getter;
import de.binary101.core.constants.enums.RequestEnum;

public class SellRequest extends Request {

	@Getter
	private int backpackPosition;

	public SellRequest(int backpackPosition) {
		super(RequestEnum.ACT_INVENTORY_CHANGE);

		this.backpackPosition = backpackPosition + 1;
	}

	@Override
	public String toString() {
		return super.toString() + "2/" + this.backpackPosition + "/4/1";
	}
}
