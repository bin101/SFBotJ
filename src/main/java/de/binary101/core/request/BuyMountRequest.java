package de.binary101.core.request;

import lombok.Getter;
import de.binary101.core.constants.enums.MountTypeEnum;
import de.binary101.core.constants.enums.RequestEnum;

public class BuyMountRequest extends Request {

	@Getter
	private MountTypeEnum mountType;

	public BuyMountRequest(MountTypeEnum mountType) {
		super(RequestEnum.BuyMount);

		this.mountType = mountType;
	}

	@Override
	public String toString() {
		return super.toString() + this.mountType.getId();
	}
}
