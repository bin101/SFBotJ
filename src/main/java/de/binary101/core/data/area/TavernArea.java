package de.binary101.core.data.area;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.format.DateTimeFormat;

import de.binary101.core.constants.enums.ActionEnum;
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

	public TavernArea(Account account) {
		super(account);
	}
	
	@Override
	public void performArea() {
		
		if (!account.getSetting().getPerformQuesten()) {
			return;
		}
		
		if (!account.getHasRunningAction()) {
			logger.info("Betrete Taverne");
			Helper.ThreadSleep(600, 1200);
			
			int aluSeconds = account.getTavern().getRemainingALUSeconds();
			
			
			//Gucke, ob ich ein Bier moechte
			int maxBeerToBuy = account.getSetting().getMaxBeerToBuy();
			if (maxBeerToBuy > 0) {
				int usedBeer = account.getTavern().getUsedBeer();
				int mushrooms = account.getOwnCharacter().getMushrooms();
				int maxBeer = 10; //TODO kann durch Enchantments auch 11 sein
				
				if (usedBeer < maxBeer && usedBeer < maxBeerToBuy && aluSeconds <= 20 * 60) {
					while (aluSeconds + 20 * 60 < 100 * 60 && mushrooms > 1 && usedBeer < maxBeer) {
						Helper.ThreadSleep(600, 1200);
						this.buyBeer();
						aluSeconds += 20 * 60;
						usedBeer += 1;
						mushrooms -= 1;
						logger.info("Habe mir ein Bier gegoennt, ist auch erst das " + usedBeer + ". fuer heute.");
					}
				}
			}
			
			
			//Haben wir heute noch Zeit fuer eine Quest?
			if (aluSeconds == 0) {
				logger.info("Habe keine ALU mehr");
				return;
			}
			
			Quest quest_1 = account.getTavern().getAvailableQuests().get(0);
			Quest quest_2 = account.getTavern().getAvailableQuests().get(1);
			Quest quest_3 = account.getTavern().getAvailableQuests().get(2);
			
			if (aluSeconds < quest_1.getDuration() && aluSeconds < quest_2.getDuration() && aluSeconds < quest_3.getDuration()) {
				logger.info("ALU reicht fuer keine Quest mehr");
				return;
			}
			
			if (startBestQuest(quest_1, quest_2, quest_3, true)) {
				logger.info("Mach mich auf die Reise");
			} else {
				logger.error("Es gab einen Fehler beim Queststart.");
			}
			
			
			
		} else {
			if ( account.getActionEndTime().isBeforeNow() && account.getActionType() == ActionEnum.Quest) {
				Helper.ThreadSleep(600, 1200);
				finishQuest();
				logger.info("Habe die Quest beendet.");
			}
		}
	}
	
	private Boolean startBestQuest(Quest quest_1, Quest quest_2, Quest quest_3, Boolean overwriteInventory) {
		Boolean result = false;
		Helper.ThreadSleep(2100, 3500);
		//TODO Nehme auf jeden Fall Spiegelstuecke, Schluessel oder rote Quests
		
		Boolean overwriteFullInventory = true; //TODO Sollte eigentlich aus den Settings gelesen werden.
		
		Quest chosenQuest = null;
		String responseString = null;
		
		switch (account.getSetting().getQuestMode()) {
		case "exp":
			if (quest_1.getExpPerSecond() >= quest_2.getExpPerSecond() && quest_1.getExpPerSecond() >= quest_3.getExpPerSecond()) {
				chosenQuest = quest_1;
			} else if (quest_2.getExpPerSecond() >= quest_1.getExpPerSecond() && quest_2.getExpPerSecond() >= quest_3.getExpPerSecond() ) {
				chosenQuest = quest_2;
			} else {
				chosenQuest = quest_3;
			}			
			break;
		case "gold":
			if (quest_1.getSilverPerSecond() >= quest_2.getSilverPerSecond() && quest_1.getSilverPerSecond() >= quest_3.getSilverPerSecond()) {
				chosenQuest = quest_1;
			} else if (quest_2.getSilverPerSecond() >= quest_1.getSilverPerSecond() && quest_2.getSilverPerSecond() >= quest_3.getSilverPerSecond() ) {
				chosenQuest = quest_2;
			} else {
				chosenQuest = quest_3;
			}			
			break;
		default:
			logger.error("Kein oder falscher Questmode gesetzt, entscheide dich fuer den Mode gold oder exp");
			break;
		}
		
		if (chosenQuest != null) {
			responseString = sendRequest(new QuestStartRequest(chosenQuest.getIndex(), overwriteFullInventory));
			Response response = new Response(responseString, account);
			if (!response.getHasError()) {
				result = true;
				
				logger.info(String.format("Habe mich fuer Quest Nummer %s entschieden, werde in %.2f Minute/n wieder da sein", chosenQuest.getIndex(), (double)chosenQuest.getDuration() / 60));
				logger.info(chosenQuest.toString());
				logger.info("Also so gegen: " + account.getActionEndTime().toString(DateTimeFormat.forPattern("HH:mm:ss")));
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
