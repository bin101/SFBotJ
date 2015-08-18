package de.binary101.core.data.guild;

import lombok.Getter;
import de.binary101.core.constants.enums.GuildUpgradeTypeEnum;
import de.binary101.core.utils.UpgradePrice;

public class GuildUpgrade {
	@Getter private GuildUpgradeTypeEnum upgradeType;
	@Getter private int level;
	@Getter private UpgradePrice prices;
	
	public GuildUpgrade(GuildUpgradeTypeEnum upgradeType, int level, UpgradePrice prices) {
		this.upgradeType = upgradeType;
		this.level = level;
		this.prices = prices;
	}
}
