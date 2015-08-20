package de.binary101.core.constants.enums;

public enum RaceEnum {
	Human("1"),
	Elf("2"),
	Dwarf("3"),
	Gnome("4"),
	Orc("5"),
	Dark_Elf("6"),
	Goblin("7"),
	Demon("8");

	private final String name;

	private RaceEnum(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
