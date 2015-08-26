package de.binary101.core.request;

import de.binary101.core.constants.enums.RequestEnum;
import de.binary101.core.data.dungeon.DungeonType;

public class EnterDungeonRequest extends Request {
	private DungeonType dungeon;

	public EnterDungeonRequest(DungeonType dungeon) {
		super(RequestEnum.EnterDungeon);

		this.dungeon = dungeon;
	}

	@Override
	public String toString() {
		return super.toString() + this.dungeon.dungeonID + "/0";
	}

}
