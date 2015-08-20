package de.binary101.core.data.area;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.data.account.Account;
import de.binary101.core.data.item.Item;
import de.binary101.core.request.SellRequest;
import de.binary101.core.response.Response;
import de.binary101.core.utils.Helper;

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

			CharacterScreenArea charScreenArea = new CharacterScreenArea(account);
			logger.info("Betrete einen der beiden Shops");

			// TODO Vll kann man das besser lösen
			charScreenArea.tryToEquipItems();

			logger.info("Rucksack ist voll, verkaufe das billigste Item");

			Helper.threadSleepRandomBetween(600, 1200);

			int backIndexForItemToSell = account.getOwnCharacter().getBackpack().getIndexForLeastValueableItem();
			Item itemToSell = account.getOwnCharacter().getBackpack().getItems().get(backIndexForItemToSell);

			logger.info("Verkaufe folgendes Item: " + itemToSell.toString());

			String sellResponseString = sendRequest(new SellRequest(backIndexForItemToSell));
			this.sellResponse = new Response(sellResponseString, account);

			Helper.threadSleepRandomBetween(600, 1200);

			if (!this.sellResponse.getHasError()) {
				logger.info("Verkauf war erfolgreich");
			}
		}
	}
}
