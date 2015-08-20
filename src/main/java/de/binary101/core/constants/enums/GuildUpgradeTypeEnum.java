package de.binary101.core.constants.enums;

public enum GuildUpgradeTypeEnum {
	Fortress(1), Treasure(2), Instructor(3);

	private final Integer id;

	private GuildUpgradeTypeEnum(Integer id) {
		this.id = id;
	}

	public static GuildUpgradeTypeEnum fromInt(int value) {
		GuildUpgradeTypeEnum result = null;
		for (GuildUpgradeTypeEnum type : GuildUpgradeTypeEnum.values()) {
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
