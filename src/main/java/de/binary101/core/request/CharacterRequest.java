package de.binary101.core.request;

import de.binary101.core.constants.enums.RequestEnum;

public class CharacterRequest extends Request {

	private String name;

	public CharacterRequest(String name) {
		super(RequestEnum.Character);

		this.name = name;
	}

	@Override
	public String toString() {
		return super.toString() + this.name;
	}

}
