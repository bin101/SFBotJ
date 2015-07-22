package de.binary101.core.constants.enums;

public enum PotionEnum
{
  None("0"),
  Strength("1"),
  Dexterity("2"),
  Intelligence("3"),
  Constitution("4"),
  Luck("5"),
  Eternal_Life("16");

	private final String name;

	private PotionEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

}
