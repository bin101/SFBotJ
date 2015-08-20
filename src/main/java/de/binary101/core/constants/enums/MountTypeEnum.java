package de.binary101.core.constants.enums;

public enum MountTypeEnum {
	None(0), Cow(1), Horse(2), Tiger(3), Dragon(4);

	private final Integer id;

	private MountTypeEnum(Integer id) {
		this.id = id;
	}

	public static MountTypeEnum fromInt(int value) {
		MountTypeEnum result = null;
		for (MountTypeEnum type : MountTypeEnum.values()) {
			if (value == (type.id)) {
				result = type;
				break;
			}
		}
		return result;
	}

	public Integer getId() {
		return this.id;
	}
}
