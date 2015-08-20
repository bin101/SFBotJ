package de.binary101.core.constants.enums;

public enum EventEnum {
	None(0),
	ExperienceEvent(1),
	EpicEvent(2),
	GoldEvent(3),
	MushroomEvent(4),
	Beerfest(5);

	private final Integer id;

	private EventEnum(Integer id) {
		this.id = id;
	}

	public static EventEnum fromInt(int value) {
		EventEnum result = null;
		for (EventEnum type : EventEnum.values()) {
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
