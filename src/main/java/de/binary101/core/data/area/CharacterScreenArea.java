package de.binary101.core.data.area;

import java.util.Optional;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.ItemTypeEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.item.Item;
import de.binary101.core.request.EquipRequest;
import de.binary101.core.response.Response;
import de.binary101.core.utils.Helper;

public class CharacterScreenArea extends BaseArea {
	private final static Logger logger = LogManager.getLogger(CharacterScreenArea.class);
	
	@Getter private Response characterScreenResponse;
	
	public CharacterScreenArea(Account account) {
		super(account);
	}
	
	@Override
	public void performArea() {
		
		if((!account.getHasEnoughALUForOneQuest() || !account.getSetting().getPerformQuesten()) && !account.getHasRunningAction()) {
			logger.info("Betrete die Charakteruebersicht");
			Helper.ThreadSleep(600, 1200);
			
			logger.info("Gucke, ob Items ausgeruestet werden koennen");
		}

	}
	
	
	private void equipItems() {
		Boolean hasSthEquipped = false;
		String equipRespString = null;
		
		for (Item backpackItem : account.getOwnCharacter().getBackpack().getItems()) {
			if (backpackItem.getType().getId() >= ItemTypeEnum.Weapon.getId() && backpackItem.getType().getId() <= ItemTypeEnum.Talisman.getId()) {
				
				Optional<Item> equippedItem = account
						.getOwnCharacter()
						.getEquipment()
						.getItems()
						.stream()
						.filter(item -> item.getType().name()
								.equals(backpackItem.getType().name()))
						.findFirst();
				
				Boolean backpackItemIsBetter = Helper.isBackpackItemBetter(backpackItem, equippedItem, account);
				
				if (backpackItemIsBetter) {
					logger.info(String.format("Lege folgendes Item an: %s", backpackItem.toString()));
					
					hasSthEquipped = true;
					equipRespString = sendRequest(new EquipRequest(backpackItem));
					this.characterScreenResponse = new Response(equipRespString, account);
				}
				
			} else if (backpackItem.getType() == ItemTypeEnum.MirrorPiece
					   || backpackItem.getType() == ItemTypeEnum.Toiletkey
					   || backpackItem.getType() == ItemTypeEnum.DungeonKey) {
				logger.info(String.format("Benutze folgendes Item: %s", backpackItem.toString()));
				
				hasSthEquipped = true;
				equipRespString = sendRequest(new EquipRequest(backpackItem));
				this.characterScreenResponse = new Response(equipRespString, account);
			}
			Helper.ThreadSleep(1000, 2000);
		}
		
		if (!hasSthEquipped) {
			logger.info("Habe nichts zum Ausruesten gefunden");
		}
	}
}
