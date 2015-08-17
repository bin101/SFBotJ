package de.binary101.core.data.area;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.format.DateTimeFormat;

import de.binary101.core.constants.enums.ActionEnum;
import de.binary101.core.constants.enums.EventEnum;
import de.binary101.core.constants.enums.ItemTypeEnum;
import de.binary101.core.constants.enums.RequestEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.area.tavern.Quest;
import de.binary101.core.request.QuestFinishRequest;
import de.binary101.core.request.QuestStartRequest;
import de.binary101.core.request.Request;
import de.binary101.core.response.Response;
import de.binary101.core.utils.Helper;

public class TavernArea extends BaseArea{
	
	private final static Logger logger = LogManager.getLogger(TavernArea.class);
	
	private Boolean gotNewItem = false;

	public TavernArea(Account account) {
		super(account);
	}
	
	@Override
	public void performArea() {		
		if (!account.getSetting().getPerformQuesten() 
			|| account.getOwnCharacter().getBackpack().getIsFull()) {
			return;
		}
		
		if (!account.getHasRunningAction() && account.getHasEnoughALUForOneQuest()) {
			logger.info("Betrete Taverne");
			Helper.threadSleepRandomBetween(1000, 2000);
			
			int aluSeconds = account.getTavern().getRemainingALUSeconds();
			
			
			//Gucke, ob ich ein Bier moechte
			int maxBeerToBuy = account.getSetting().getMaxBeerToBuy();
			int savedMushrooms = account.getSetting().getSavedMushrooms();
			if (maxBeerToBuy > 0) {
				int usedBeer = account.getTavern().getUsedBeer();
				int mushrooms = account.getOwnCharacter().getMushrooms();
				int maxBeer = 10; //TODO kann durch Enchantments auch 11 sein
				
				if (usedBeer < maxBeer && usedBeer < maxBeerToBuy && aluSeconds <= 20 * 60) {
					while (aluSeconds + 20 * 60 < 100 * 60 
							&& mushrooms > 1
							&& mushrooms > savedMushrooms
							&& usedBeer < maxBeer 
							&& usedBeer < maxBeerToBuy) {
						Helper.threadSleepRandomBetween(600, 1200);
						this.buyBeer();
						aluSeconds += 20 * 60;
						usedBeer += 1;
						mushrooms -= 1;
						logger.info("Habe mir ein Bier gegoennt, ist auch erst das " + usedBeer + ". fuer heute.");
					}
				}
			}
			
			logger.info("Gehe auf den Questgeber zu");
			
			if (!startBestQuest(account.getTavern().getAvailableQuests(), true)) {
				logger.error("Es gab einen Fehler beim Queststart.");
			}
			
			
			
		} else {
			if ( account.getActionEndTime().isBeforeNow() && account.getActionType() == ActionEnum.Quest) {
				Helper.threadSleepRandomBetween(600, 1200);
				finishQuest();
				logger.info("Habe die Quest beendet.");
				
				if (gotNewItem) {
					account.setGotNewItem(true);
					gotNewItem = false;
				}
			}
		}
	}
	
	private Boolean startBestQuest(List<Quest> quests, Boolean overwriteInventory) {
		Boolean result = false;
		Helper.threadSleepRandomBetween(2100, 3500);
		
		Boolean overwriteFullInventory = true; //TODO Sollte eigentlich aus den Settings gelesen werden.
		
		Boolean oneQuestIsSpecial = false;
		
		Quest chosenQuest = null;
		String responseString = null;
		int remainingAluSeconds = account.getTavern().getRemainingALUSeconds();
		
		if (account.getSetting().getPreferSpecialQuests()) {
			try {
				chosenQuest = quests
						.stream()
						.filter(quest -> quest.getDuration() <= remainingAluSeconds
								&& quest.getIsSpecial())
						.min((quest1, quest2) -> Integer.compare(
								quest1.getDuration(), quest2.getDuration())).get();
				
				oneQuestIsSpecial = true;
				
			} catch (NoSuchElementException e) {
				oneQuestIsSpecial = false;
			}
		}
		
		if (!oneQuestIsSpecial){
			
			EventEnum currentSpecialEvent = account.getTavern().getSpecialEvent();
			String settingsQuestMode = account.getSetting().getQuestMode();
			if (currentSpecialEvent == EventEnum.ExperienceEvent || currentSpecialEvent == EventEnum.GoldEvent) {
				String targetQuestMode = currentSpecialEvent == EventEnum.ExperienceEvent ? "exp" : "gold";
				
				logger.info(String.format("Es herrscht gerade das %s, bevorzuge daher %s", currentSpecialEvent, targetQuestMode));
				account.getSetting().setQuestMode(targetQuestMode);
			}
			
			switch (account.getSetting().getQuestMode()) {
			case "exp":
				chosenQuest = quests
						.stream()
						.filter(quest -> quest.getDuration() <= remainingAluSeconds)
						.max((quest1, quest2) -> Integer.compare(
								quest1.getExp(), quest2.getExp())).get();			
				break;
			case "gold":
				chosenQuest = quests
						.stream()
						.filter(quest -> quest.getDuration() <= remainingAluSeconds)
						.max((quest1, quest2) -> Double.compare(
								quest1.getSilverPerSecond(), quest2.getSilverPerSecond())).get();			
				break;
			default:
				logger.error("Kein oder falscher Questmode gesetzt, entscheide dich fuer den Mode gold oder exp");
				break;
			}
			
			if (currentSpecialEvent == EventEnum.ExperienceEvent || currentSpecialEvent == EventEnum.GoldEvent) {
				account.getSetting().setQuestMode(settingsQuestMode);
			}
		} else {
			logger.info(String.format("Habe den Questmode ignoriert, da Quest %s %s", chosenQuest.getIndex(), chosenQuest.getSpecialQuestType().toString()));
		}
		
		if (chosenQuest != null) {
			responseString = sendRequest(new QuestStartRequest(chosenQuest.getIndex(), overwriteFullInventory));
			Response response = new Response(responseString, account);
			if (!response.getHasError()) {
				result = true;
				
				logger.info(String.format("Habe mich fuer Quest Nummer %s entschieden, werde in %.2f Minute/n wieder da sein", chosenQuest.getIndex(), (double)chosenQuest.getDuration() / 60));
				logger.info(chosenQuest.toString());
				logger.info("Also so gegen: " + account.getActionEndTime().toString(DateTimeFormat.forPattern("HH:mm:ss")));
				
				if (chosenQuest.getItem().getType() != ItemTypeEnum.None) {
					this.gotNewItem = true;
				}
			}
		}
		return result;
	}
	
	@SuppressWarnings("unused")
	private void finishQuest() {
		String responseString = sendRequest(new QuestFinishRequest(false));
		Response response = new Response(responseString, account);
	}
	
	private Boolean buyBeer() {
		Boolean result = false;
		String responseString = this.sendRequest(new Request(RequestEnum.BuyBeer));
		Response response = new Response(responseString, account);
		
		if (!response.getHasError()) {
			result = true;
		}
		return result;
	}
}
