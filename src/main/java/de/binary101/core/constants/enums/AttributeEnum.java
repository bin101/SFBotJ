package de.binary101.core.constants.enums;

public enum AttributeEnum {
	Strength(1),
	Dexterity(2),
	Intelligence(3),
	Stamina(4),
	Luck(5);

	private final Integer id;

	private AttributeEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

}
