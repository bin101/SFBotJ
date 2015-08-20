package de.binary101.core.constants.enums;

public enum ItemTypeEnum {
	None(0), Weapon(1), Shield(2), Torso(3), Shoes(4), Gloves(5), Helmet(6), Belt(
			7), Amulet(8), Ring(9), Talisman(10), MirrorOrKey(11), Potion(12), DontKnow(
			13), Enchantment(14),

	// Werden nur intern benutzt, nicht serverseitig
	DungeonKey(200), Toiletkey(210), MirrorPiece(220);

	private final Integer id;

	private ItemTypeEnum(Integer id) {
		this.id = id;
	}

	public static ItemTypeEnum fromInt(int value) {
		ItemTypeEnum result = null;
		for (ItemTypeEnum itemPosition : ItemTypeEnum.values()) {
			if (value == (itemPosition.id)) {
				result = itemPosition;
			}
		}
		return result;
	}

	public Integer getId() {
		return this.id;
	}
}
