package de.binary101.core.response;

import de.binary101.core.constants.enums.ItemTypeEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.item.Item;

public class PlayerFightResponse extends Response {

	private Boolean hasWonFight;
	private long silverGet;
	private long expGet;
	private int mushroomGet;
	private int honorGet;
	private Item itemGet;

	public PlayerFightResponse(String rawData, Account account) {
		super(rawData, account);

		if (this.parsedData.containsKey("fightresult")) {
			Long[] fightResult = convertStringArrayToLongArray(this.parsedData.get("fightresult").get(0).split("/"));

			this.hasWonFight = fightResult[0] == 1;
			this.silverGet = fightResult[2];
			this.expGet = fightResult[3];
			this.mushroomGet = fightResult[4].intValue();
			this.honorGet = fightResult[5].intValue();

			this.itemGet = Item.createItem(fightResult, 9, -1);

			if (this.itemGet.getType() != ItemTypeEnum.None) {
				account.setGotNewItem(true);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (this.hasWonFight) {
			builder.append("Habe den Kampf gewonnen und folgendes erhalten:\n");

			if (this.silverGet != 0) {
				builder.append(String.format("	Silber: %d \n", this.silverGet));
			}

			if (this.expGet != 0) {
				builder.append(String.format("	Erfahrung: %d \n", this.expGet));
			}

			if (this.mushroomGet != 0) {
				builder.append(String.format("	Pilze: %d \n", this.mushroomGet));
			}

			if (this.honorGet != 0) {
				builder.append(String.format("	Ehre: %d \n", this.honorGet));
			}

			if (this.itemGet.getType() != ItemTypeEnum.None) {
				builder.append(String.format("	Item: %s \n", this.itemGet.toString()));
			}
		} else {
			builder.append("Habe den Kampf verloren");
		}

		return builder.toString();
	}

}
