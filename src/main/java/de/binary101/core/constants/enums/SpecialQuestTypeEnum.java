package de.binary101.core.constants.enums;

public enum SpecialQuestTypeEnum {
	RedQuest("eine Rote Quest ist"),
	hasDungeonKey("einen Dungeon Schluessel hat"),
	hasToiletKey("den Toiletten Schluessel hat"),
	hasMirrorpiece("ein Spiegelstueck hat"),
	hasEpicItem("ein episches Item hat"),
	None("nichts besonderes hat");

	private final String name;

	private SpecialQuestTypeEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
