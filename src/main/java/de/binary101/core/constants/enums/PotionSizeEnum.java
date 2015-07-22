package de.binary101.core.constants.enums;

public enum PotionSizeEnum
{
  Small("10"),
  Medium("15"),
  Big("25");
  
	private final String name;

	private PotionSizeEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

}
