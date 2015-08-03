package de.binary101.core.data.character;

import lombok.Getter;
import lombok.Setter;

public class Character {
	@Getter @Setter private String name;
	@Getter @Setter private int guildId;
	@Getter @Setter private int level;
	@Getter @Setter private long experience;
	@Getter @Setter private long experienceForNextLevel;
	@Getter @Setter private AttributeList attributeList;
	
	public Character() {
		
	}
}
