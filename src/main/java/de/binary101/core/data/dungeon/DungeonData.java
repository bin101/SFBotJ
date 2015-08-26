package de.binary101.core.data.dungeon;

import lombok.Getter;

public class DungeonData {
	@Getter private Boolean unlocked;
	@Getter private Boolean completed;
	@Getter private int progress;

	public DungeonData(Boolean unlocked, Boolean completed, int progress) {
		this.unlocked = unlocked;
		this.completed = completed;
		this.progress = progress;
	}
}
