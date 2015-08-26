package de.binary101.core.data.dungeon;

import lombok.Getter;

public class DungeonType {
	@Getter public int dungeonID;
	@Getter public int dungeonEbene;
	@Getter public int dungeonMonsterLvl;

	public DungeonType(int id, int ebene, int lvl) {
		dungeonID = id;
		dungeonEbene = ebene;
		dungeonMonsterLvl = lvl;
	}
}
