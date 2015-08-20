package de.binary101.core.constants.enums;

public enum ItemTypeCharacterSlotEnum {
	None(0),
	Helmet(1),
	Torso(2),
	Gloves(3),
	Shoes(4),
	Amulet(5),
	Belt(6),
	Ring(7),
	Talisman(8),
	Weapon(9),
	Shield(10),
	Potion(12);

	private final Integer id;

	private ItemTypeCharacterSlotEnum(Integer id) {
		this.id = id;
	}

	public static ItemTypeCharacterSlotEnum fromItemTypeEnum(ItemTypeEnum value) {
		ItemTypeCharacterSlotEnum result = null;

		for (ItemTypeCharacterSlotEnum item : ItemTypeCharacterSlotEnum.values()) {
			if (item.name().equals(value.name())) {
				result = item;
			}
		}
		return result;
	}

	public Integer getId() {
		return this.id;
	}
}
