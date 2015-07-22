package de.binary101.core.constants.enums;

public enum SkillEnum {
	Strength("1"),
	Dexterity("2"),
	Intelligence("3"),
	Stamina("4"),
	Luck("5");

	private final String name;

	private SkillEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

}
