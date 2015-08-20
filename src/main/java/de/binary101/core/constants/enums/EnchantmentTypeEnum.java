package de.binary101.core.constants.enums;

public enum EnchantmentTypeEnum {
	SwordOfVengeance(11), MariosBeard(31), Boots36960Feet(41), ShadowOfTheCowboy(
			51), AdventurersArchaeologicalAura(61), ThirstyWanderer(71), UnholyAcquisitiveness(
			81), GraveRobbersPrayer(91), RobberBaronRitual(101);

	private final Integer id;

	private EnchantmentTypeEnum(Integer id) {
		this.id = id;
	}

	public static EnchantmentTypeEnum fromInt(int value) {
		EnchantmentTypeEnum result = null;
		for (EnchantmentTypeEnum type : EnchantmentTypeEnum.values()) {
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
