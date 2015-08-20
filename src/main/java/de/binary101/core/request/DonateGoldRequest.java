package de.binary101.core.request;

import de.binary101.core.constants.enums.RequestEnum;

public class DonateGoldRequest extends Request {

	private long silverAmountToDonate;

	public DonateGoldRequest(long silverAmountToDonate) {
		super(RequestEnum.GuildDonateGold);

		this.silverAmountToDonate = silverAmountToDonate;
	}

	@Override
	public String toString() {
		return super.toString() + this.silverAmountToDonate;
	}

}
