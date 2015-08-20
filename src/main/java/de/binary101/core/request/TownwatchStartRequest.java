package de.binary101.core.request;

import lombok.Getter;
import de.binary101.core.constants.enums.RequestEnum;

public class TownwatchStartRequest extends Request {

	@Getter private int hours;

	public TownwatchStartRequest(int hours) {
		super(RequestEnum.StartTownwatch);

		this.hours = hours;
	}

	@Override
	public String toString() {
		return super.toString() + this.hours;
	}

}
