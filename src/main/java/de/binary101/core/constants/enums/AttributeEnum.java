package de.binary101.core.constants.enums;

public enum AttributeEnum {
	None(0), Strength(1), Dexterity(2), Intelligence(3), Stamina(4), Luck(5), Epic(
			6);

	private final Integer id;

	private AttributeEnum(Integer id) {
		this.id = id;
	}

	public static AttributeEnum fromInt(int value) {
		AttributeEnum result = null;
		for (AttributeEnum type : AttributeEnum.values()) {
			if (value == (type.id)) {
				result = type;
			}
		}
		return result;
	}

	public Integer getId() {
		return this.id;
	}

}
