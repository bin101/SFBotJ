package de.binary101.core.data.area.tavern;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import de.binary101.core.constants.enums.EventEnum;
import de.binary101.core.constants.enums.PlayerSaveEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.item.Item;
import de.binary101.core.utils.TimeManager;

public class Tavern {

	@Getter @Setter	private int remainingALUSeconds;
	@Getter @Setter	private int usedBeer;
	@Getter	private List<Quest> availableQuests;
	@Getter @Setter private EventEnum specialEvent;
	
	public Tavern() {
		this.availableQuests = Arrays.asList(new Quest[3]);
		this.remainingALUSeconds  = 0;
		this.usedBeer = 0;
		this.specialEvent = EventEnum.None;
	}

	public void updateTavern(Account account, Long[] ownplayersave) {
		
		this.remainingALUSeconds = ownplayersave[PlayerSaveEnum.ALUinSeconds.getId()].intValue();
		this.usedBeer = ownplayersave[PlayerSaveEnum.UsedBeerToday.getId()].intValue();
		
		account.setActionEndTime(TimeManager
				.UTCunixTimestampToLocalDateTime(ownplayersave[PlayerSaveEnum.ActionUntil.getId()].intValue()));
		
		this.availableQuests.set(0, new Quest(
				1,
				ownplayersave[PlayerSaveEnum.Quest_1_Duration.getId()].intValue(),
				ownplayersave[PlayerSaveEnum.Quest_1_Silver.getId()].intValue(),
				ownplayersave[PlayerSaveEnum.Quest_1_Exp.getId()].intValue(),
				Item.createItem(ownplayersave, PlayerSaveEnum.Quest_1_ItemStart.getId(), -1),
				ownplayersave[PlayerSaveEnum.Quest_1_MonsterID.getId()].intValue()));
		
		this.availableQuests.set(1, new Quest(
				2,
				ownplayersave[PlayerSaveEnum.Quest_2_Duration.getId()].intValue(),
				ownplayersave[PlayerSaveEnum.Quest_2_Silver.getId()].intValue(),
				ownplayersave[PlayerSaveEnum.Quest_2_Exp.getId()].intValue(),
				Item.createItem(ownplayersave, PlayerSaveEnum.Quest_2_ItemStart.getId(), -1),
				ownplayersave[PlayerSaveEnum.Quest_2_MonsterID.getId()].intValue()));
		
		this.availableQuests.set(2, new Quest(
				3,
				ownplayersave[PlayerSaveEnum.Quest_3_Duration.getId()].intValue(),
				ownplayersave[PlayerSaveEnum.Quest_3_Silver.getId()].intValue(),
				ownplayersave[PlayerSaveEnum.Quest_3_Exp.getId()].intValue(),
				Item.createItem(ownplayersave, PlayerSaveEnum.Quest_3_ItemStart.getId(), -1),
				ownplayersave[PlayerSaveEnum.Quest_3_MonsterID.getId()].intValue()));
	}

}
