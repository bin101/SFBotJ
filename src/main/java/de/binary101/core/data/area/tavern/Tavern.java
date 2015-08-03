package de.binary101.core.data.area.tavern;

import lombok.Getter;
import lombok.Setter;
import de.binary101.core.constants.enums.PlayerSaveEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.item.Item;
import de.binary101.core.utils.TimeManager;

public class Tavern {

	@Getter @Setter
	private int remainingALUSeconds;
	@Getter @Setter
	private int usedBeer;
	@Getter
	private Quest[] availableQuests = new Quest[3];

	public void updateTavern(Account account, Integer[] ownplayersave) {
		
		this.remainingALUSeconds = ownplayersave[PlayerSaveEnum.ALUinSeconds.getId()];
		
		account.setActionEndTime(TimeManager
				.UTCunixTimestampToLocalDateTime(ownplayersave[PlayerSaveEnum.ActionUntil.getId()]));
		
		this.availableQuests[0] = new Quest(
				1,
				ownplayersave[PlayerSaveEnum.Quest_1_Duration.getId()],
				ownplayersave[PlayerSaveEnum.Quest_1_Silver.getId()],
				ownplayersave[PlayerSaveEnum.Quest_1_Exp.getId()],
				Item.createItem(ownplayersave, PlayerSaveEnum.Quest_1_ItemStart.getId(), -1),
				ownplayersave[PlayerSaveEnum.Quest_1_MonsterID.getId()]);
		
		this.availableQuests[1] = new Quest(
				2,
				ownplayersave[PlayerSaveEnum.Quest_2_Duration.getId()],
				ownplayersave[PlayerSaveEnum.Quest_2_Silver.getId()],
				ownplayersave[PlayerSaveEnum.Quest_2_Exp.getId()],
				Item.createItem(ownplayersave, PlayerSaveEnum.Quest_2_ItemStart.getId(), -1),
				ownplayersave[PlayerSaveEnum.Quest_2_MonsterID.getId()]);
		
		this.availableQuests[2] = new Quest(
				3,
				ownplayersave[PlayerSaveEnum.Quest_3_Duration.getId()],
				ownplayersave[PlayerSaveEnum.Quest_3_Silver.getId()],
				ownplayersave[PlayerSaveEnum.Quest_3_Exp.getId()],
				Item.createItem(ownplayersave, PlayerSaveEnum.Quest_3_ItemStart.getId(), -1),
				ownplayersave[PlayerSaveEnum.Quest_3_MonsterID.getId()]);
		
		this.usedBeer = ownplayersave[PlayerSaveEnum.UsedBeerToday.getId()];
	}

}
