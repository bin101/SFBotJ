package de.binary101.core.constants.enums;

public enum OwnPlayerSaveEnum {

	// Eigener Spieler
	Level(7),
	Armor(447),
	MinDamageBase(448),
	MaxDamageBase(449),
	ExpForNextLevel(8),
	Exp(9),
	Honor(10),
	Rank(11),
	Silver(13),
	Mushroom(14),
	Mirror(28),
	CharClass(29),
	Mount(286), // Must be
				// normalized
	JoinedGuildDate(443),
	
	MountEndTime(451),
	DonateableMushrooms(437),
	NextArenaBattleTime(460),
	Potion_1(493),
	Potion_2(494),
	Potion_3(495),
	DungeonTimer(459),
	ArenaTimer(460),

	ActionType(45),
	ActionLength(46),
	ActionEndTime(47),
	BackpackDataStart(168),
	EquipmentDataStart(48),
	// Taverne
	ALUinSeconds(456),
	ActionUntil(47),
	UsedBeerToday(457),
	Quest_1_Duration(241),
	Quest_1_Silver(283),
	Quest_1_Exp(280),
	Quest_1_ItemStart(244),
	Quest_1_MonsterID(235),

	Quest_2_Duration(242),
	Quest_2_Silver(284),
	Quest_2_Exp(281),
	Quest_2_ItemStart(256),
	Quest_2_MonsterID(236),

	Quest_3_Duration(243),
	Quest_3_Silver(285),
	Quest_3_Exp(282),
	Quest_3_ItemStart(268),
	Quest_3_MonsterID(237);

	private final Integer id;

	private OwnPlayerSaveEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
}
