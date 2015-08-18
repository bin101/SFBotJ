package de.binary101.core.data.guild;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import de.binary101.core.constants.enums.GuildRaidTypeEnum;
import de.binary101.core.constants.enums.OwnGuildSaveEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.utils.Helper;
import de.binary101.core.utils.SFStringManager;
import de.binary101.core.utils.TimeManager;

public class Guild {
	
	@Getter @Setter private String name;
	@Getter @Setter private String[] memberNames;
	@Getter @Setter private String description;
	@Getter @Setter private int honor;
	@Getter @Setter private int rank;
	@Getter @Setter private long silver;
	@Getter @Setter private int mushrooms;
	@Getter @Setter private int fortressLevel;
	@Getter @Setter private int treasureLevel;
	@Getter @Setter private int instructorLevel;
	@Getter @Setter private DateTime nextAttackTime;
	@Getter @Setter private DateTime nextDefenseTime;
	@Getter @Setter private GuildRaidTypeEnum nextRaidType;
	@Getter @Setter private DateTime lastDonateTime;

	public Guild() {
		this.name = "None";
		this.memberNames = new String[0];
		this.description = "None";
		this.honor = 0;
		this.rank = 0;
		this.silver = 0l;
		this.mushrooms = 0;
		this.fortressLevel = 0;
		this.treasureLevel = 0;
		this.instructorLevel = 0;
		this.nextAttackTime = new DateTime();
		this.nextDefenseTime = new DateTime();
		this.nextRaidType = GuildRaidTypeEnum.None;
		this.lastDonateTime = new DateTime();
	}
	
	public void updateGuild(Account account, Long[] ownGuildSave, String ownGuildName, int ownGuildRank, String ownGuildDescription, String[] ownGuildMembers) {
		this.name = ownGuildName;
		this.memberNames = ownGuildMembers;
		this.description = SFStringManager.GetSFDecodingString(ownGuildDescription);
		this.honor = ownGuildSave[OwnGuildSaveEnum.Honor.getId()].intValue();
		this.rank = ownGuildRank;
		this.silver = ownGuildSave[OwnGuildSaveEnum.Silver.getId()];
		this.mushrooms = Helper.normalizeResponseInt(ownGuildSave[OwnGuildSaveEnum.Mushroom.getId()].intValue());
		this.fortressLevel = Helper.normalizeResponseInt(ownGuildSave[OwnGuildSaveEnum.FortressLevel.getId()].intValue());
		this.treasureLevel = Helper.normalizeResponseInt(ownGuildSave[OwnGuildSaveEnum.TreasureLevel.getId()].intValue());
		this.instructorLevel = Helper.normalizeResponseInt(ownGuildSave[OwnGuildSaveEnum.InstructorLevel.getId()].intValue());
		this.nextAttackTime = TimeManager.UTCunixTimestampToLocalDateTime(ownGuildSave[OwnGuildSaveEnum.NextFightTime.getId()].intValue());
		this.nextAttackTime = TimeManager.UTCunixTimestampToLocalDateTime(ownGuildSave[OwnGuildSaveEnum.NextDefenseTime.getId()].intValue());
		this.nextRaidType = GuildRaidTypeEnum.fromInt(ownGuildSave[OwnGuildSaveEnum.NextRaidID.getId()].intValue());
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		this.lastDonateTime = formatter.parseDateTime(account.getSetting().getLastDonateDate());
	}
}
