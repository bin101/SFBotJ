package de.binary101.core.data.character;

import lombok.Getter;
import lombok.Setter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.ActionEnum;
import de.binary101.core.constants.enums.ClassEnum;
import de.binary101.core.constants.enums.PlayerSaveEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.utils.Helper;
import de.binary101.core.utils.TimeManager;

public class OwnCharacter extends Character{
	
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
	@Getter @Setter private AttributeList attributes;
	@Getter @Setter private int armor;
	//TODO @Getter @Setter private Mount mount;
	//TODO @Getter @Setter private Backpack backpack;
	//TODO @Getter @Setter private Dungeons dungeons;
	//TODO @Getter @Setter private PotionsSlots potions;
	//TODO @Getter @Setter private Equipment equipment;
	@Getter @Setter private String description;
	@Getter @Setter private String guildName;
	//TODO @Getter @Setter private int portalLifeBonus;
	//TODO @Getter @Setter private int portalDamageBonus;
	@Getter @Setter private int minDamageBase;
	@Getter @Setter private int maxDamageBase;

	public void updateOwnCharacter(Account account, Integer[] ownplayersave) {
		account.setActionType(ActionEnum.fromInt(Helper.normalizeResponseInt(ownplayersave[PlayerSaveEnum.ActionType.getId()])));
		
		if (account.getActionType() == ActionEnum.None) {
			account.setHasRunningAction(false);
		} else {
			account.setHasRunningAction(true);
		}
		
		account.setActionLength(Helper.normalizeResponseInt(ownplayersave[PlayerSaveEnum.ActionLength.getId()]));
		account.setActionEndTime(TimeManager.UTCunixTimestampToLocalDateTime(ownplayersave[PlayerSaveEnum.ActionEndTime.getId()]).plusSeconds(2));
	}

}
