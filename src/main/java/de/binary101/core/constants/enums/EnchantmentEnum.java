package de.binary101.core.constants.enums;

public enum EnchantmentEnum
{
  None("0"),
  SwordOfVengeance("1"),
  MariosBeard("2"),
  Boots36960Feet("4"),
  ShadowOfTheCowboy("8"),
  ThirstyWanderer("16"),
  UnholyAcquisitiveness("32"),
  GraveRobbersPrayer("64"),
  RobberBaronRitual("128"),
  AdventurersArchaeologicalAura("256");
  
  private final String name;

	private EnchantmentEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
