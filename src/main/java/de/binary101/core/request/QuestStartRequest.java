package de.binary101.core.request;

import lombok.Getter;
import de.binary101.core.constants.enums.RequestEnum;

public class QuestStartRequest extends Request {

	@Getter
	private int questNumber;
	@Getter
	private Boolean overwriteFullInventory;

	public QuestStartRequest(int questNumber, Boolean overwriteFullInventory) {
		super(RequestEnum.StartQuest);

		this.questNumber = questNumber;
		this.overwriteFullInventory = overwriteFullInventory;
	}

	@Override
	public String toString() {
		return super.toString() + this.questNumber + "/"
				+ (this.overwriteFullInventory ? "1" : "0");
	}
}
