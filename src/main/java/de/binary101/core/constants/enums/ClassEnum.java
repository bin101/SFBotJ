package de.binary101.core.constants.enums;

public enum ClassEnum {
	All(0), Warrior(1), Mage(2), Scout(3);

	private final Integer id;

	private ClassEnum(Integer id) {
		this.id = id;
	}

	public static ClassEnum fromInt(int value) {
		ClassEnum result = null;
		for (ClassEnum type : ClassEnum.values()) {
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
