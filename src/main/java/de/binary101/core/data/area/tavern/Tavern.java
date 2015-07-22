package de.binary101.core.data.area.tavern;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import de.binary101.core.constants.enums.PlayerSaveEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.utils.TimeManager;

public class Tavern {

	@Getter @Setter
	private int remainingALUSeconds;
	@Getter @Setter
	private int usedBeer;
	@Getter
	private ArrayList<Quest> availableQuests = new ArrayList<Quest>();

	public void updateTavern(Account account, Integer[] ownplayersave) {
		
		this.remainingALUSeconds = ownplayersave[PlayerSaveEnum.ALUinSeconds.getId()];
		
		account.setActionEndTime(TimeManager
				.UTCunixTimestampToLocalDateTime(ownplayersave[PlayerSaveEnum.ActionUntil.getId()]));
		
		this.availableQuests.add(new Quest(
				1,
				ownplayersave[PlayerSaveEnum.Quest_1_Duration.getId()],
				ownplayersave[PlayerSaveEnum.Quest_1_Silver.getId()],
				ownplayersave[PlayerSaveEnum.Quest_1_Exp.getId()],
				ownplayersave[PlayerSaveEnum.Quest_1_MonsterID.getId()]));
		
		this.availableQuests.add(new Quest(
				2,
				ownplayersave[PlayerSaveEnum.Quest_2_Duration.getId()],
				ownplayersave[PlayerSaveEnum.Quest_2_Silver.getId()],
				ownplayersave[PlayerSaveEnum.Quest_2_Exp.getId()],
				ownplayersave[PlayerSaveEnum.Quest_2_MonsterID.getId()]));
		
		this.availableQuests.add(new Quest(
				3,
				ownplayersave[PlayerSaveEnum.Quest_3_Duration.getId()],
				ownplayersave[PlayerSaveEnum.Quest_3_Silver.getId()],
				ownplayersave[PlayerSaveEnum.Quest_3_Exp.getId()],
				ownplayersave[PlayerSaveEnum.Quest_3_MonsterID.getId()]));
		
		this.usedBeer = ownplayersave[PlayerSaveEnum.UsedBeerToday.getId()];
	}

}
