package de.binary101.core.constants.enums;

public enum GuildRankEnum {
	None(0),
	Invited(4),
	Member(3),
	Officer(2),
	Leader(1);

	private final Integer id;

	private GuildRankEnum(Integer id) {
		this.id = id;
	}

	public static GuildRankEnum fromInt(int value) {
		GuildRankEnum result = null;
		for (GuildRankEnum type : GuildRankEnum.values()) {
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
