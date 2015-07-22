package de.binary101.core.constants.enums;

public enum PlayerSaveEnum {
	
	//Eigener Spieler
	ActionType(45),
	ActionLength(46),
	ActionEndTime(47),
	//Taverne
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
	
//	PaymentID(0),
//	PlayerID(1),
//	LastActionDate(2),
//	RegistrationDate(3),
//	RegistrationIP(4),
//	MessageCount(5),
//	LockStatus(6),
//	Level(7),
//	PvPBattleWithExpCount(8),
//	ExpForNextLevel(9),
//	Exp(10),
//	Honor(11),
//	Rang(12),
//	SilverGoldDungeonAdvance(13),
//	Gold(14),
//	Mushroom(15),
//	MushroomGained(16),
//	MushroomSpend(17),
//	Face(18),
//	Race(28),
//	LastDevilRefreshDay(29),
//	Gender(30),
//	Mirror(31),
//	MirrorPieces(32),
//	Class(33),
//	LastDevilFightDungeonday(34),
//	Attribute_1(35),
//	Attribute_2(36),
//	Attribute_3(37),
//	Attribute_4(38),
//	Attribute_5(39),
//	AttributeBonus_1(40),
//	AttributeBonus_2(41),
//	AttributeBonus_3(42),
//	AttributeBonus_4(43),
//	AttributeBonus_5(44),
//	AttributeBuyed_1(45),
//	AttributeBuyed_2(46),
//	AttributeBuyed_3(47),
//	AttributeBuyed_4(48),
//	AttributeBuyed_5(49),
//	ActionStatus(50),
//	DevelEnemyLifePartOne(51),
//	ActionIndex(52),
//	DevelEnemyLifePartTwo(53),
//	ActionEndTime(54),
//	EquipedItem_Item_1_Start(55),
//	EquipedItem_Item_2_Start(71),
//	EquipedItem_Item_3_Start(87),
//	EquipedItem_Item_4_Start(103),
//	EquipedItem_Item_5_Start(119),
//	EquipedItem_Item_6_Start(135),
//	EquipedItem_Item_7_Start(151),
//	EquipedItem_Item_8_Start(167),
//	EquipedItem_Item_9_Start(183),
//	EquipedItem_Item_10_Start(199),
//	BackpackItem_Item_1_Start(215),
//	BackpackItem_Item_2_Start(231),
//	BackpackItem_Item_3_Start(247),
//	BackpackItem_Item_4_Start(263),
//	BackpackItem_Item_5_Start(279),
//	QuestRerollTime(295),
//	QuestOffer_1_Level(296),
//	QuestOffer_2_Level(297),
//	QuestOffer_3_Level(298),
//	QuestOffer_1_Type(299),
//	QuestOffer_2_Type(300),
//	QuestOffer_3_Type(301),
//	QuestOffer_1_Enemy(302),
//	QuestOffer_2_Enemy(303),
//	QuestOffer_3_Enemy(304),
//	QuestOffer_1_Location(305),
//	QuestOffer_2_Location(306),
//	QuestOffer_3_Location(307),
//	QuestOffer_1_Duration(308),
//	QuestOffer_2_Duration(309),
//	QuestOffer_3_Duration(310),
//	QuestOffer_1_RewardItemStart(311),
//	QuestOffer_2_RewardItemStart(327),
//	QuestOffer_3_RewardItemStart(343),
//	QuestOffer_1_Exp(359),
//	QuestOffer_2_Exp(360),
//	QuestOffer_3_Exp(361),
//	QuestOffer_1_Gold(362),
//	QuestOffer_2_Gold(363),
//	QuestOffer_3_Gold(364),
//	Mount(365),
//	TowerLevel(367),
//	ShakesRerollTime(368),
//	ShakesItem_1_Start(369),
//	ShakesItem_2_Start(385),
//	ShakesItem_3_Start(401),
//	ShakesItem_4_Start(417),
//	ShakesItem_5_Start(433),
//	ShakesItem_6_Start(449),
//	FidgetRerollTime(465),
//	FidgetItem_1_Start(466),
//	FidgetItem_2_Start(482),
//	FidgetItem_3_Start(498),
//	FidgetItem_4_Start(514),
//	FidgetItem_5_Start(530),
//	FidgetItem_6_Start(546),
//	MaxRaidLevel(562),
//	GoldFactor(563),
//	UnreadMessages(564),
//	GroupId(565),
//	GroupRank(566),
//	MushroomRealBought(567),
//	Scrapbook(568),
//	LastGroupFightExp(569),
//	AccountProtectionDate(570),
//	SilverDungeon(571),
//	GoldDungeon(572),
//	GroupJoinDate(573),
//	SpecialFlags(574),
//	MushroomBuyedSinceLastLogin(575),
//	DamageBonusGroup(576),
//	LifeBonusSingle(577),
//	WeMissYou(578),
//	Armor(579),
//	DamageMin(580),
//	DamageMax(581),
//	Life(582),
//	MountDurationTo(583),
//	WireTransferCount(584),
//	MushroomCheat(585),
//	Recruited(586),
//	AbenteuerRerollDate(587),
//	AbenteuerZeit(588),
//	BeerBuyed(589),
//	DungeonQuestState(590),
//	DungeonQuestNextFreeTry(591),
//	PvPNextFreeFight(592),
//	AddedExpGroup(593),
//	AddedExpGold(594),
//	EmailValid(595),
//	EmailValidateDate(596),
//	Archivment_1(597),
//	Archivment_2(598),
//	Archivment_3(599),
//	Archivment_4(600),
//	Archivment_5(601),
//	Archivment_6(602),
//	Archivment_7(603),
//	Archivment_8(604),
//	Archivment_9(605),
//	Archivment_10(606),
//	LockDuration(607),
//	BuffedUserIp(608),
//	MushroomProtectionType(609),
//	MushroomProtectionTime(610),
//	FirstPaymentUserFor(611),
//	DungeonLevel_1(612),
//	DungeonLevel_2(613),
//	DungeonLevel_3(614),
//	DungeonLevel_4(615),
//	DungeonLevel_5(616),
//	DungeonLevel_6(617),
//	DungeonLevel_7(618),
//	DungeonLevel_8(619),
//	DungeonLevel_9(620),
//	DungeonLevel_10(621),
	
	
	private final Integer id;

	private PlayerSaveEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
}
/*
"thirteendungeon",
"arcanetoilett",
"arcaneexp",
"potiontyp(3)",
"potiontime(3)",
"potionpower(3)",
"powerlifepotion",
"lastloginip(3)",
"coinsbuyed",
"coinsdate",
"guildfightstatus",
"eventtriggercount",
"language(2)",
"logincount",
"lockdescription",
"lasttoilettdate",
"toilettexpnext",
"stashcount",
"hidegoldframe",
"messagecountpro",
"witchprice",
"scrapbookrepairstate",
"nogroupinvite",
"promotionmount",
"promotionmountinuse",
"fortressbuildinglevel.w(20)",
"fortressbuildindex.w(6)",
"fortressbuildfinishtime(6)",
"fortressresource(3)",
"fortressupdate.w(10)",
"fortressunits.w(10)",
"free(54)"
*/