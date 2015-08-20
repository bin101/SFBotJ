package de.binary101.core.constants.enums;

public enum MessageEnum {
	Normal("0"),
	GuildDeleted("1"),
	GuildKick("3"),
	GuildInvite("5"),
	LostFight("6"),
	WonFight("7"),
	LostGuildFight("8"),
	WonGuildFight("9");

	private final String name;

	private MessageEnum(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
