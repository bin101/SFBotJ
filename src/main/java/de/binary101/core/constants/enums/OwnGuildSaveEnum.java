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
	NextDefenseTime(367),
	
	//Member
	MemberLevelStartIndex(64),
	MemberLastOnlineStartIndex(114),
	MemberDonatedSilverStartIndex(214),
	MemberDonatedMushroomsStartIndex(264),
	MemberGuildRankStartIndex(314),
	MemberLastPortalFoughtDateStartIndex(164),
	MemberPlayerIDStartIndex(14);
	
	private final Integer id;

	private OwnGuildSaveEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

}
