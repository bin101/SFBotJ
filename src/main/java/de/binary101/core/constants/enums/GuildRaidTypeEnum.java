package de.binary101.core.constants.enums;

public enum GuildRaidTypeEnum {
	None(0),
	Wading_Pool(1),
	The_6_1_2_Seas(2),
	Shallows_Of_The_Deep_Sea(3),
	Community_Garden_Colony(4),
	Preschool_of_Horror(5),
	Realm_Of_The_Dark_Dwarves(6),
	Glass_Castle(7),
	Downtown_Brooklyn(8),
	Bat_Cave(9),
	Gobbot_Land(10),
	Realm_of_the_Magic_Lantern(11),
	East_Pole(12),
	Realm_of_The_Titans(13),
	Absurdistan(14),
	Bone_Castle(15),
	Myths_and_Mysteries(16),
	Ancawatri_Dromedary(17),
	Barbaria(18),
	Extraterra_IV(19),
	Path_to_Hell(20),
	Hellish_Hell(21),
	Petting_Zoo_of_Death(22),
	In_the_dragons_den(23),
	Blackwater_Moor(24),
	Monster_kindergarten(25),
	Cabinet_of_horrors(26),
	Wild_Monster_Party(27),
	Cave_of_the_graverobbers(28),
	Crypt_of_the_undead(29),
	The_emperors_fighting_guard(30),
	Topsy_Turvy_World(31),
	Harbringers_of_the_dead(32),
	The_predators_feast(33),
	Monkey_Business(34),
	The_booger_population(35),
	Dodging_the_blade(36),
	In_the_dark_of_the_night(37),
	Asocial_combustion_point(38),
	The_Old_Cemetry(39),
	Primodial_beasts(40),
	The_black_magic_mountain(41),
	Gragoshs_Dread(42),
	Ragorth_the_Bandit(43),
	Slobba_the_Mudd(44),
	Xanthippopothamia(45),
	In_the_vegetable_garden(46),
	premature_end(47),
	Debugging(48),
	System_error(49),
	At_the_big_boss_place(50);

	private final Integer id;

	private GuildRaidTypeEnum(Integer id) {
		this.id = id;
	}

	public static GuildRaidTypeEnum fromInt(int value) {
		GuildRaidTypeEnum result = null;
		for (GuildRaidTypeEnum type : GuildRaidTypeEnum.values()) {
			if (value == (type.id)) {
				result = type;
				break;
			}
		}
		return result;
	}

	public Integer getId() {
		return this.id;
	}
}
