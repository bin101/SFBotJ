package de.binary101.core.request;

import de.binary101.core.constants.enums.GuildUpgradeTypeEnum;
import de.binary101.core.constants.enums.RequestEnum;

public class UpgradeGuildRequest extends Request {

	private GuildUpgradeTypeEnum upgrade;
	
	public UpgradeGuildRequest(GuildUpgradeTypeEnum upgrade) {
		super(RequestEnum.ImproveGuild);
		
		this.upgrade = upgrade;
	}
	
	@Override
	public String toString() {
		return super.toString() + this.upgrade.getId();
	}

}
