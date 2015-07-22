package de.binary101.core.constants.enums;

public enum ItemPositionEnum {
  None("0"),
  Helmet("1"),
  Torso("2"),
  Hand("3"),
  Shoes("4"),
  Amulet("5"),
  Belt("6"),
  Ring("7"),
  Talisman("8"),
  Weapon("9"),
  Shield("10"),
  Potion("-1"), //12
  Scherbe("-1"),
  Scrapbook("-1"),
  Dungeonkey("-1"),
  Toiletkey("-1");
  
  private final String name;

	private ItemPositionEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
