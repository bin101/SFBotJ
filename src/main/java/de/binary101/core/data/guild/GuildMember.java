package de.binary101.core.data.guild;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import de.binary101.core.constants.enums.AttackStatusEnum;
import de.binary101.core.constants.enums.GuildRankEnum;

public class GuildMember {
	@Getter @Setter private String name;
	@Getter @Setter private int level;
	@Getter @Setter private int playerID;
	@Getter @Setter private int playerGuildIndex;
	@Getter @Setter private Boolean portalFought;
	@Getter @Setter private long donatedSilver;
	@Getter @Setter private int donatedMushrooms;
	@Getter @Setter private DateTime lastOnline;
	@Getter @Setter private AttackStatusEnum attackStatus;
	@Getter @Setter private GuildRankEnum guildRank;

}
