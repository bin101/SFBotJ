package de.binary101.core.constants.enums;

public enum EventEnum {
  None("0"),
  ExperienceEvent("1"),
  EpicEvent("2"),
  GoldEvent("3"),
  MushroomEvent("4"),
  Beerfest("5");
  
  private final String name;

	private EventEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
