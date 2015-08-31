package de.binary101.core.data.character;

import lombok.Getter;
import lombok.Setter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import de.binary101.core.constants.enums.ActionEnum;
import de.binary101.core.constants.enums.ClassEnum;
import de.binary101.core.constants.enums.MountTypeEnum;
import de.binary101.core.constants.enums.OwnPlayerSaveEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.shop.Shop;
import de.binary101.core.utils.Helper;
import de.binary101.core.utils.TimeManager;

public class OwnCharacter extends Character {

	private final static Logger logger = LogManager.getLogger(OwnCharacter.class);

	@Getter @Setter private String name;
	@Getter @Setter private int level;
	@Getter @Setter private long silver;
	@Getter @Setter private int mushrooms;
	@Getter @Setter private long exp;
	@Getter @Setter private long expForNextLevel;
	@Getter @Setter private int honor;
	@Getter @Setter private int rank;
	@Getter @Setter private ClassEnum charClass;
	@Getter @Setter private int armor;
	@Getter @Setter private MountTypeEnum mountType;
	@Getter @Setter private DateTime mountEndTime;
	@Getter @Setter private Backpack backpack;
	// TODO @Getter @Setter private PotionsSlots potions;
	@Getter @Setter private Equipment equipment;

	@Getter @Setter private Shop weaponShop;
	@Getter @Setter private Shop magicShop;

	@Getter @Setter private String description;
	@Getter @Setter private String guildName;
	@Getter @Setter private int portalLifeBonus;
	@Getter @Setter private int portalDamageBonus;
	@Getter @Setter private int minDamageBase;
	@Getter @Setter private int maxDamageBase;

	@Getter @Setter private DateTime joinedGuildDate;
	@Getter @Setter private DateTime arenaTimer;
	@Getter @Setter private DateTime dungeonTimer;

	public OwnCharacter() {
		super();

		this.backpack = new Backpack();
		this.equipment = new Equipment();
		this.weaponShop = new Shop();
		this.magicShop = new Shop();
	}

	public synchronized void updateOwnCharacter(Account account, Long[] ownplayersave) {

		int newLevel = Helper.normalizeResponseInt(ownplayersave[OwnPlayerSaveEnum.Level.getId()].intValue());
		if (this.level != 0 && this.level < newLevel) {
			logger.info(String.format("Yeah, Levelup. Ich bin jetzt Level %s", newLevel));
		}
		this.level = newLevel;

		this.exp = ownplayersave[OwnPlayerSaveEnum.Exp.getId()];
		this.expForNextLevel = ownplayersave[OwnPlayerSaveEnum.ExpForNextLevel.getId()];
		this.honor = ownplayersave[OwnPlayerSaveEnum.Honor.getId()].intValue();
		this.rank = ownplayersave[OwnPlayerSaveEnum.Rank.getId()].intValue();
		this.silver = ownplayersave[OwnPlayerSaveEnum.Silver.getId()];
		this.mushrooms = ownplayersave[OwnPlayerSaveEnum.Mushroom.getId()].intValue();
		this.setAttributeList(new AttributeList(ownplayersave[30].intValue(), ownplayersave[31].intValue(),
				ownplayersave[32].intValue(), ownplayersave[33].intValue(), ownplayersave[34].intValue(),
				ownplayersave[35].intValue(), ownplayersave[36].intValue(), ownplayersave[37].intValue(),
				ownplayersave[38].intValue(), ownplayersave[39].intValue(), Helper
						.getRealAttributePrice(ownplayersave[40].intValue()), Helper
						.getRealAttributePrice(ownplayersave[41].intValue()), Helper
						.getRealAttributePrice(ownplayersave[42].intValue()), Helper
						.getRealAttributePrice(ownplayersave[43].intValue()), Helper
						.getRealAttributePrice(ownplayersave[44].intValue()), account));

		// Portal Zeug
		double portalHelper = ownplayersave[445] / 65536.0;
		this.portalLifeBonus = (int) (portalHelper / 256.0);
		this.portalDamageBonus = (int) (portalHelper - (double) (this.portalLifeBonus * 256));

		this.joinedGuildDate = TimeManager
				.UTCunixTimestampToLocalDateTime(ownplayersave[OwnPlayerSaveEnum.JoinedGuildDate.getId()].intValue());
		this.dungeonTimer = TimeManager.UTCunixTimestampToLocalDateTime(ownplayersave[OwnPlayerSaveEnum.DungeonTimer
				.getId()].intValue());
		this.arenaTimer = TimeManager.UTCunixTimestampToLocalDateTime(ownplayersave[OwnPlayerSaveEnum.ArenaTimer
				.getId()].intValue());

		String mirrorHelper = ownplayersave[OwnPlayerSaveEnum.Mirror.getId()].toString();
		while (mirrorHelper.length() < 32) {
			mirrorHelper = "0" + mirrorHelper;
		}
		account.setHasCompletedMirror(mirrorHelper.substring(23, 24) == "1");
		/*
		 * TODO Mirrorpieces this.MirrorPieces = new List<int>(); for (int index
		 * = 0; index < 13; ++index)
		 * this.MirrorPieces.Add(Convert.ToInt32(str.Substring(index + 1, 1)));
		 */
		this.charClass = ClassEnum.fromInt(Helper.normalizeResponseInt(ownplayersave[OwnPlayerSaveEnum.CharClass
				.getId()].intValue()));

		this.mountType = MountTypeEnum.fromInt(Helper.normalizeResponseInt(ownplayersave[OwnPlayerSaveEnum.Mount
				.getId()].intValue()));
		this.mountEndTime = TimeManager.UTCunixTimestampToLocalDateTime(ownplayersave[OwnPlayerSaveEnum.MountEndTime
				.getId()].intValue());

		// Beschaeftigungszeiten und Flags setzten
		account.setActionType(ActionEnum.fromInt(Helper.normalizeResponseInt(ownplayersave[OwnPlayerSaveEnum.ActionType
				.getId()].intValue())));

		if (account.getActionType() == ActionEnum.None) {
			account.setHasRunningAction(false);
		} else {
			account.setHasRunningAction(true);
		}

		account.setActionLength(Helper.normalizeResponseInt(ownplayersave[OwnPlayerSaveEnum.ActionLength.getId()]
				.intValue()));
		account.setActionEndTime(TimeManager.UTCunixTimestampToLocalDateTime(
				ownplayersave[OwnPlayerSaveEnum.ActionEndTime.getId()].intValue()).plusSeconds(2));

		// Update Backpack
		Long[] backpackData = new Long[12 * 5];
		System.arraycopy(ownplayersave, OwnPlayerSaveEnum.BackpackDataStart.getId(), backpackData, 0, 12 * 5);
		this.backpack.updateBackpack(backpackData);

		// Update Equipment
		Long[] equipmentData = new Long[12 * 10];
		System.arraycopy(ownplayersave, OwnPlayerSaveEnum.EquipmentDataStart.getId(), equipmentData, 0, 12 * 10);
		this.equipment.updateEquipment(equipmentData);

		// Update WeaponShop
		Long[] weaponShopData = new Long[12 * 6];
		System.arraycopy(ownplayersave, OwnPlayerSaveEnum.WeaponShopDataStart.getId(), weaponShopData, 0, 12 * 6);
		this.weaponShop.updateShop(weaponShopData);

		// Update WeaponShop
		Long[] magicShopData = new Long[12 * 6];
		System.arraycopy(ownplayersave, OwnPlayerSaveEnum.MagicShopDataStart.getId(), magicShopData, 0, 12 * 6);
		this.magicShop.updateShop(magicShopData);
	}

}
