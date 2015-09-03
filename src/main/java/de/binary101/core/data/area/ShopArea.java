package de.binary101.core.data.area;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import de.binary101.core.constants.enums.ItemTypeEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.item.Item;
import de.binary101.core.data.shop.Shop;
import de.binary101.core.request.BuyShopItemRequest;
import de.binary101.core.request.SellRequest;
import de.binary101.core.response.Response;
import de.binary101.core.utils.Helper;
import de.binary101.core.utils.SettingsManager;

public class ShopArea extends BaseArea {
	private final static Logger logger = LogManager.getLogger(ShopArea.class);

	@Getter private Response sellResponse;

	public ShopArea(Account account) {
		super(account);
	}

	@Override
	public void performArea() {
		if (!account.getSetting().getPerformShop()) {
			return;
		}

		if (account.getOwnCharacter().getBackpack().getIsFull()) {
			this.sellItem();
		}

		if (account.getSetting().getBuyShopItems()) {
			for (int i = 1; i <= 2; i++) {
				Boolean isWeaponShop = i == 1 ? true : false;
				Shop currentShop = isWeaponShop ? account.getOwnCharacter().getWeaponShop() : account.getOwnCharacter()
						.getMagicShop();
				Helper.threadSleepRandomBetween(1000, 2000);

				Item emptyBackpackItem = account.getOwnCharacter().getBackpack().getEmptyItem();

				if (emptyBackpackItem != null
						&& ((isWeaponShop && DateTime.now().isAfter(
								account.getSetting().getLastWeaponShopCheckTime().plusHours(1))) || (!isWeaponShop && DateTime
								.now().isAfter(account.getSetting().getLastMagicShopCheckTime().plusHours(1))))) {

					Item betterShopItem = getBetterShopItem(currentShop.getShopItems(), account.getOwnCharacter()
							.getSilver(), account.getOwnCharacter().getMushrooms(), false);

					if (betterShopItem != null) {
						logger.info(String.format("Habe mir im %s folgendes Item gekauft: \n %s",
								isWeaponShop ? "Waffenladen" : "Magieladen", betterShopItem.toString()));
						String responseString = sendRequest(new BuyShopItemRequest(isWeaponShop, betterShopItem,
								emptyBackpackItem));
						new Response(responseString, account);
						account.setGotNewItem(true);
					} else {
						logger.info(String.format("Habe kein Item im %s gefunden", isWeaponShop ? "Waffenladen"
								: "Magieladen"));
					}

					if (isWeaponShop) {
						account.getSetting().setLastWeaponShopCheckTime(DateTime.now());
					} else {
						account.getSetting().setLastMagicShopCheckTime(DateTime.now());
					}
					SettingsManager.saveSettings();
				}

				if (account.getOwnCharacter().getBackpack().getIsFull()) {
					this.sellItem();
				}
			}
		}
	}

	private void sellItem() {
		CharacterScreenArea charScreenArea = new CharacterScreenArea(account);
		logger.info("Betrete einen der beiden Shops");

		// TODO Vll kann man das besser lösen
		charScreenArea.tryToEquipItems();

		logger.info("Rucksack ist voll, verkaufe das billigste Item");

		Helper.threadSleepRandomBetween(600, 1200);

		Item itemToSell = account.getOwnCharacter().getBackpack().getLeastValueableItem();

		if (itemToSell != null) {
			logger.info("Verkaufe folgendes Item: " + itemToSell.toString());

			String sellResponseString = sendRequest(new SellRequest(itemToSell));
			this.sellResponse = new Response(sellResponseString, account);

			Helper.threadSleepRandomBetween(600, 1200);

			if (!this.sellResponse.getHasError()) {
				logger.info("Verkauf war erfolgreich");
			}
		} else {
			logger.info("Habe kein Item zum Verkaufen gefunden");
		}
	}

	private Item getBetterShopItem(List<Item> shopItems, long currentSilver, int currentMushrooms, Boolean useMushrooms) {
		Optional<Item> result = null;

		result = shopItems.stream()
				.filter(item -> item.getType() == ItemTypeEnum.Scrapbook && item.getSilverPrice() <= currentSilver)
				.findFirst();

		if (!result.isPresent()) {
			// welche Items kann ich mir leisten
			List<Item> availableItems = shopItems
					.stream()
					.filter(item -> item.getSilverPrice() <= currentSilver
							&& item.getMushroomPrice() <= currentMushrooms && item.getType() != ItemTypeEnum.Potion)
					.collect(Collectors.toList());

			// darf ich Pilze nutzen
			if (!useMushrooms) {
				availableItems = availableItems.stream().filter(item -> item.getMushroomPrice() == 0)
						.collect(Collectors.toList());
			}

			// welche Items sind besser
			availableItems = availableItems.stream().filter(item -> Helper.isShopItemBetter(item, account))
					.collect(Collectors.toList());

			if (availableItems.size() > 0) {
				result = availableItems.stream().max(
						(item1, item2) -> Long.compare(item1.getSilverPrice(), item2.getSilverPrice()));
			}
		}

		return result.isPresent() ? result.get() : null;
	}
}
