package de.binary101.core.constants.enums;

public enum RequestEnum
{
	Login("accountlogin"),
	PasswordUpdate("accountpasswordupdate"),
	ShowPlayerHallOfFame("playergethalloffame"),
	DungeonScreen("playerdungeonopen"),
	BuyMount("playermountbuy"),
	BuySkill("playerattributincrease"),
	ShellPlayerBetGold("playergamblegold"),
	ShellPlayerBetMush("playergamblecoins"),
	ShowOtherGuild("grouplookat"),
	ShowGuildHallOfFame("groupgethalloffame"),
	DeletePotion("playerpotionkill"),
	FoundGuild("groupfound"),
	InvitePlayer("groupinvitemember"),
	KickPlayer("groupkick"),
	SetMaster("groupsetleader"),
	ToggleOfficer("groupsetofficer"),
	ImproveGuild("groupincreasebuilding"),
	SetGuildInfo("groupsetdescription"),
	GuildDonateGold("groupspendgold"),
	GuildDonateMush("groupspendcoins"),
	JoinAttack("groupreadyattack"),
	JoinDefense("groupreadydefense"),
	AttackGuild("groupattackdeclare"),
	DeclareRaid("groupraiddeclare"),
	WC_Flush("playertoilettflush"),
	TowerAttack("playertowerbattle"),
	LevelCompanion("playertowerbuylevel"),
	EquipCompanion("playerItemMove"),
	CauldronDrop("playerItemMove"),
	EnchantItem("playerwitchenchantitem"),
	GuildPortalBattle("groupportalbattle"),
	PlayerPortalBattle("playerportalbattle"),
	StartTownwatch("playerworkstart"),
	ACT_INVENTORY_CHANGE("playerItemMove"),
	StopTownwatch("playerworkstop"),
	BrowseShop("playernewwares"),
	ViewMail("playermessageview"),
	DeleteMail("playermessagedelete"),
	SendMail("playermessagesend"),
	StartQuest("playeradventurestart"),
	StopQuest("playeradventurestop"),
	Attack("playerarenafight"),
	Character("playerlookat"),
	SetCharacterDescription("playersetdescription"),
	SendGuildMessage("groupchat"),
	BuyBeer("playerbeerbuy"),
	EnterDungeon("playerdungeonbattle"),
	SendWhisperMessage("playermessagewhisper"),
	Poll("poll"),
	FinishQuest("playeradventurefinished"),
	FinishTownwatch("playerworkfinished"),
	ReliveFight("playercombatlogview");
  
	private final String name;

	private RequestEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
