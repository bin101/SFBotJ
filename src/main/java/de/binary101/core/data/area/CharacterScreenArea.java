package de.binary101.core.data.area;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.ItemTypeEnum;
import de.binary101.core.constants.enums.RequestEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.character.Attribute;
import de.binary101.core.data.item.Item;
import de.binary101.core.request.BuyAttributeRequest;
import de.binary101.core.request.EquipRequest;
import de.binary101.core.request.Request;
import de.binary101.core.response.Response;
import de.binary101.core.utils.Helper;
import de.binary101.core.utils.SettingsManager;

public class CharacterScreenArea extends BaseArea {
	private final static Logger logger = LogManager.getLogger(CharacterScreenArea.class);

	public CharacterScreenArea(Account account) {
		super(account);
	}

	@Override
	public void performArea() {
		if (account.getSetting().getStrengthPercentage() == -1 || account.getSetting().getDexterityPercentage() == -1
				|| account.getSetting().getIntelligencePercentage() == -1
				|| account.getSetting().getStaminaPercentage() == -1 || account.getSetting().getLuckPercentage() == -1) {

			switch (account.getOwnCharacter().getCharClass()) {
				case Mage:
					account.getSetting().setStrengthPercentage(15);
					account.getSetting().setDexterityPercentage(15);
					account.getSetting().setIntelligencePercentage(25);
					account.getSetting().setStaminaPercentage(25);
					account.getSetting().setLuckPercentage(20);
					break;
				case Scout:
					account.getSetting().setStrengthPercentage(15);
					account.getSetting().setDexterityPercentage(26);
					account.getSetting().setIntelligencePercentage(15);
					account.getSetting().setStaminaPercentage(23);
					account.getSetting().setLuckPercentage(21);
					break;
				case Warrior:
					account.getSetting().setStrengthPercentage(30);
					account.getSetting().setDexterityPercentage(15);
					account.getSetting().setIntelligencePercentage(15);
					account.getSetting().setStaminaPercentage(22);
					account.getSetting().setLuckPercentage(18);
					break;
				default:
					logger.warn("Characterklasse war noch nicht geladen");
					break;
			}

			SettingsManager.saveSettings();
		}

		if (!account.getSetting().getPerformItemEquip() || !account.getSetting().getPerformAttributeBuy()) {
			return;
		} else if ((account.getGotNewItem() && account.getSetting().getPerformItemEquip())
				|| (account.getSetting().getPerformAttributeBuy() && ((!account.getSetting().getPerformQuesten() || !account
						.getHasEnoughALUForOneQuest()) && !account.getHasRunningAction()))) {
			logger.info("Betrete die Charakteruebersicht");

			if (account.getSetting().getPerformItemEquip() && account.getGotNewItem()) {
				Helper.threadSleepRandomBetween(600, 1200);

				tryToEquipItems();

				account.setGotNewItem(false);
			}

			if (account.getSetting().getPerformAttributeBuy()
					&& ((!account.getSetting().getPerformQuesten() || !account.getHasEnoughALUForOneQuest()) && !account
							.getHasRunningAction())) {
				Boolean canBuyStats = account.getOwnCharacter().getAttributeList().getCanBuyAtLeastOne();

				if (canBuyStats) {

					int strIncrement = 0;
					int intIncrement = 0;
					int dexIncrement = 0;
					int staIncrement = 0;
					int lckIncrement = 0;

					Boolean haveBuy = false;

					while (canBuyStats) {
						Helper.threadSleepRandomBetween(100, 300);
						haveBuy = false;

						Attribute strength = this.account.getOwnCharacter().getAttributeList().getStrength();
						Attribute intelligence = this.account.getOwnCharacter().getAttributeList().getIntelligence();
						Attribute dexterity = this.account.getOwnCharacter().getAttributeList().getDexterity();
						Attribute stamina = this.account.getOwnCharacter().getAttributeList().getStamina();
						Attribute luck = this.account.getOwnCharacter().getAttributeList().getLuck();

						int fullAttributeSum = 0;
						if (account.getSetting().getStrengthPercentage() > 0) {
							fullAttributeSum += strength.getBaseValue();
						}

						if (account.getSetting().getIntelligencePercentage() > 0) {
							fullAttributeSum += intelligence.getBaseValue();
						}

						if (account.getSetting().getDexterityPercentage() > 0) {
							fullAttributeSum += dexterity.getBaseValue();
						}

						if (account.getSetting().getStaminaPercentage() > 0) {
							fullAttributeSum += stamina.getBaseValue();
						}

						if (account.getSetting().getLuckPercentage() > 0) {
							fullAttributeSum += luck.getBaseValue();
						}

						int strengthLimit = fullAttributeSum * account.getSetting().getStrengthPercentage() / 100;
						int intelligenceLimit = fullAttributeSum * account.getSetting().getIntelligencePercentage()
								/ 100;
						int dexterityLimit = fullAttributeSum * account.getSetting().getDexterityPercentage() / 100;
						int staminaLimit = fullAttributeSum * account.getSetting().getStaminaPercentage() / 100;
						int luckLimit = fullAttributeSum * account.getSetting().getLuckPercentage() / 100;

						if (strength.getBaseValue() <= strengthLimit
								&& canAffordBySaveValue(strength.getPriceForNextUpgrade())) {
							++strIncrement;
							buyAttribute(strength);
							haveBuy = true;
						}

						if (intelligence.getBaseValue() <= intelligenceLimit
								&& canAffordBySaveValue(intelligence.getPriceForNextUpgrade())) {
							++intIncrement;
							buyAttribute(intelligence);
							haveBuy = true;
						}

						if (dexterity.getBaseValue() <= dexterityLimit
								&& canAffordBySaveValue(dexterity.getPriceForNextUpgrade())) {
							++dexIncrement;
							buyAttribute(dexterity);
							haveBuy = true;
						}

						if (stamina.getBaseValue() <= staminaLimit
								&& canAffordBySaveValue(stamina.getPriceForNextUpgrade())) {
							++staIncrement;
							buyAttribute(stamina);
							haveBuy = true;
						}

						if (luck.getBaseValue() <= luckLimit && canAffordBySaveValue(luck.getPriceForNextUpgrade())) {
							++lckIncrement;
							buyAttribute(luck);
							haveBuy = true;
						}

						if (!haveBuy) {
							canBuyStats = false;
						}

						Helper.threadSleepRandomBetween(100, 300);
					}

					if (strIncrement != 0 || intIncrement != 0 || dexIncrement != 0 || staIncrement != 0
							|| lckIncrement != 0) {
						logger.info("Dann wollen wir mal meine Attribute verbessern");
						logger.info(String.format("Puh, bin fertig. Str:+%s Int:+%s Dex:+%s Sta:+%s Lck:+%s",
								strIncrement, intIncrement, dexIncrement, staIncrement, lckIncrement));
					}
				}
			}
		} else {
			return;
		}
	}

	private Boolean canAffordBySaveValue(long price) {
		Boolean result = false;

		if ((account.getOwnCharacter().getSilver() - price) >= (account.getWagesPerHour() * 15)) {
			result = true;
		}

		return result;
	}

	private void buyAttribute(Attribute target) {
		String responseString = sendRequest(new BuyAttributeRequest(target));
		new Response(responseString, this.account);
	}

	public void tryToEquipItems() {
		Boolean hasSthEquipped = false;
		String equipRespString = null;

		logger.info("Gucke, ob Items ausgeruestet werden koennen");

		for (Item backpackItem : account.getOwnCharacter().getBackpack().getItems().stream()
				.filter(item -> item.getType() != ItemTypeEnum.None).collect(Collectors.toList())) {
			Helper.threadSleepRandomBetween(500, 1000);
			if (backpackItem.getType().getId() >= ItemTypeEnum.Weapon.getId()
					&& backpackItem.getType().getId() <= ItemTypeEnum.Talisman.getId()) {

				Optional<Item> equippedItem = account.getOwnCharacter().getEquipment().getItems().stream()
						.filter(item -> item.getType().name().equals(backpackItem.getType().name())).findFirst();
				Boolean backpackItemIsBetter = false;

				if (equippedItem.isPresent()) {
					backpackItemIsBetter = Helper.isFirstItemBetterThanSecondItem(backpackItem, equippedItem.get(),
							account);
				} else {
					backpackItemIsBetter = true;
				}

				if (backpackItemIsBetter) {
					logger.info(String.format("Lege folgendes Item an: %s", backpackItem.toString()));

					hasSthEquipped = true;
					equipRespString = sendRequest(new EquipRequest(backpackItem));
					new Response(equipRespString, account);
				}

			} else if (backpackItem.getType() == ItemTypeEnum.MirrorPiece
					|| backpackItem.getType() == ItemTypeEnum.Toiletkey
					|| backpackItem.getType() == ItemTypeEnum.DungeonKey
					|| backpackItem.getType() == ItemTypeEnum.Scrapbook) {
				logger.info(String.format("Benutze folgendes Item: %s", backpackItem.toString()));

				hasSthEquipped = true;
				equipRespString = backpackItem.getType() == ItemTypeEnum.DungeonKey ? sendRequest(new Request(
						RequestEnum.DungeonScreen)) : sendRequest(new EquipRequest(backpackItem));
				new Response(equipRespString, account);
			}
			Helper.threadSleepRandomBetween(500, 1000);
		}

		if (!hasSthEquipped) {
			logger.info("Habe nichts zum Ausruesten gefunden");
		}
	}
}
