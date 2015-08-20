package de.binary101.core.constants.enums;

public enum AttackStatusEnum {
	None(0), Fight(1), Defense(2), Both(3);

	private final Integer id;

	private AttackStatusEnum(Integer id) {
		this.id = id;
	}

	public static AttackStatusEnum fromInt(int value) {
		AttackStatusEnum result = null;
		for (AttackStatusEnum type : AttackStatusEnum.values()) {
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
