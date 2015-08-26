package de.binary101.core.data.guild;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import de.binary101.core.constants.enums.AttackStatusEnum;
import de.binary101.core.constants.enums.GuildRaidTypeEnum;
import de.binary101.core.constants.enums.GuildRankEnum;
import de.binary101.core.constants.enums.GuildUpgradeTypeEnum;
import de.binary101.core.constants.enums.OwnGuildSaveEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.utils.GuildUpgradePrices;
import de.binary101.core.utils.Helper;
import de.binary101.core.utils.SFStringManager;
import de.binary101.core.utils.TimeManager;

public class Guild {

	@Getter @Setter private String name;
	@Getter @Setter private CopyOnWriteArrayList<GuildMember> members;
	@Getter @Setter private String description;
	@Getter @Setter private int honor;
	@Getter @Setter private int rank;
	@Getter @Setter private long silver;
	@Getter @Setter private int mushrooms;
	@Getter @Setter private DateTime nextAttackTime;
	@Getter @Setter private DateTime nextDefenseTime;
	@Getter @Setter private GuildRaidTypeEnum nextRaidType;
	@Getter @Setter private List<GuildUpgrade> guildUpgrades;

	public Guild() {
		this.name = "None";
		this.members = new CopyOnWriteArrayList<GuildMember>();
		this.description = "None";
		this.honor = 0;
		this.rank = 0;
		this.silver = 0l;
		this.mushrooms = 0;
		this.guildUpgrades = Arrays.asList(new GuildUpgrade[3]);
		this.nextAttackTime = new DateTime();
		this.nextDefenseTime = new DateTime();
		this.nextRaidType = GuildRaidTypeEnum.None;
	}

	public synchronized void updateGuild(Account account, Long[] ownGuildSave, String ownGuildName, int ownGuildRank,
			String ownGuildDescription, String[] ownGuildMembers) {

		if (ownGuildName != null && !ownGuildName.equals("")) {
			this.name = ownGuildName;
		}

		if (ownGuildDescription != null && !ownGuildDescription.equals("")) {
			this.description = SFStringManager.GetSFDecodingString(ownGuildDescription);
		}

		this.honor = ownGuildSave[OwnGuildSaveEnum.Honor.getId()].intValue();
		this.rank = ownGuildRank;
		this.silver = ownGuildSave[OwnGuildSaveEnum.Silver.getId()];
		this.mushrooms = Helper.normalizeResponseInt(ownGuildSave[OwnGuildSaveEnum.Mushroom.getId()].intValue());

		int fortressLevel = Helper
				.normalizeResponseInt(ownGuildSave[OwnGuildSaveEnum.FortressLevel.getId()].intValue());
		int treasureLevel = Helper
				.normalizeResponseInt(ownGuildSave[OwnGuildSaveEnum.TreasureLevel.getId()].intValue());
		int instructorLevel = Helper.normalizeResponseInt(ownGuildSave[OwnGuildSaveEnum.InstructorLevel.getId()]
				.intValue());

		this.guildUpgrades.set(
				0,
				new GuildUpgrade(GuildUpgradeTypeEnum.Fortress, fortressLevel, GuildUpgradePrices
						.getPrice(fortressLevel + 1)));
		this.guildUpgrades.set(
				1,
				new GuildUpgrade(GuildUpgradeTypeEnum.Treasure, treasureLevel, GuildUpgradePrices
						.getPrice(treasureLevel + 1)));
		this.guildUpgrades.set(
				2,
				new GuildUpgrade(GuildUpgradeTypeEnum.Instructor, instructorLevel, GuildUpgradePrices
						.getPrice(instructorLevel + 1)));

		this.nextAttackTime = TimeManager.UTCunixTimestampToLocalDateTime(
				ownGuildSave[OwnGuildSaveEnum.NextFightTime.getId()].intValue()).plusHours(10);
		this.nextAttackTime = TimeManager.UTCunixTimestampToLocalDateTime(
				ownGuildSave[OwnGuildSaveEnum.NextDefenseTime.getId()].intValue()).plusHours(10);
		this.nextRaidType = GuildRaidTypeEnum.fromInt(ownGuildSave[OwnGuildSaveEnum.NextRaidID.getId()].intValue());

		if (ownGuildMembers != null && ownGuildMembers.length != 0) {
			this.members.clear();

			for (int i = 0; i < ownGuildMembers.length; ++i) {
				GuildMember member = new GuildMember();

				member.setName(ownGuildMembers[i]);
				int attackStatus = 0;
				member.setLevel(ownGuildSave[OwnGuildSaveEnum.MemberLevelStartIndex.getId() + i].intValue());
				while (member.getLevel() > 1000) {
					member.setLevel(member.getLevel() - 1000);
					++attackStatus;
				}
				member.setAttackStatus(AttackStatusEnum.fromInt(attackStatus));
				member.setLastOnline(TimeManager
						.UTCunixTimestampToLocalDateTime(ownGuildSave[OwnGuildSaveEnum.MemberLastOnlineStartIndex
								.getId() + i].intValue()));
				member.setDonatedSilver(ownGuildSave[OwnGuildSaveEnum.MemberDonatedSilverStartIndex.getId() + i]);
				member.setDonatedMushrooms(ownGuildSave[OwnGuildSaveEnum.MemberDonatedMushroomsStartIndex.getId() + i]
						.intValue());
				member.setGuildRank(GuildRankEnum.fromInt(ownGuildSave[OwnGuildSaveEnum.MemberGuildRankStartIndex
						.getId() + i].intValue()));
				member.setPortalFought(ownGuildSave[OwnGuildSaveEnum.MemberLastPortalFoughtDateStartIndex.getId() + i]
						.intValue() == 0
						|| TimeManager.UTCunixTimestampToLocalDateTime(
								ownGuildSave[OwnGuildSaveEnum.MemberLastPortalFoughtDateStartIndex.getId() + i]
										.intValue()).dayOfYear() == DateTime.now().dayOfYear());
				member.setPlayerID(ownGuildSave[OwnGuildSaveEnum.MemberPlayerIDStartIndex.getId() + i].intValue());
				member.setPlayerGuildIndex(i);

				members.add(member);
			}
		}
	}
}
