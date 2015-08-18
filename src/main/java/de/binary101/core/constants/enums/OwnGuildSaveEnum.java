package de.binary101.core.constants.enums;

public enum OwnGuildSaveEnum {
	Silver(1),
	Mushroom(2), //Normalize
	
	FortressLevel(5), //Normalize
	TreasureLevel(6), //Normalize
	InstructorLevel(7), //Normalize
	NextRaidID(8),
	
	Honor(13),
	
	NextFightTime(365),
	NextDefenseTime(367);
	
	private final Integer id;

	private OwnGuildSaveEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

}
