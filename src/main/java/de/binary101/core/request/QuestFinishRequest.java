package de.binary101.core.request;

import lombok.Getter;
import de.binary101.core.constants.enums.RequestEnum;

public class QuestFinishRequest extends Request {
	@Getter private Boolean skipQuest;

	public QuestFinishRequest(Boolean skipQuest) {
		super(RequestEnum.FinishQuest);

		this.skipQuest = skipQuest;
	}

	@Override
	public String toString() {
		return super.toString() + (this.skipQuest ? "1" : "0");
	}
}
