package de.binary101.core.data.area.tavern;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.ItemTypeEnum;
import de.binary101.core.constants.enums.SpecialQuestTypeEnum;
import de.binary101.core.data.item.Item;

public class Quest {
	
	private final static Logger logger = LogManager.getLogger(Quest.class);
	
	@Getter private int index;
	@Getter private int duration;
	@Getter private long silver;
	@Getter private int exp;
	@Getter private Item item;
	@Getter private int monsterId;
	
	//Special Check
	@Getter private Boolean isSpecial;
	private Boolean isRedQuest;
	@Getter private SpecialQuestTypeEnum specialQuestType;
	
	public double getExpPerSecond() {
		return this.exp / this.duration;
	}
	
	public double getSilverPerSecond() {
		double itemSilver = 0;
		
		if (this.item.getType().getId() >= 1 && this.item.getType().getId() <= 10) {
			itemSilver = this.item.getSilverPrice();
		}
		
		return (this.silver + itemSilver) / this.duration;
	}
	
	public Quest(int index, int duration, long silver, int exp, Item item, int monsterId) {
		this.index = index;
		this.duration = duration;
		this.silver = silver;
		this.exp = exp;
		this.item = item;
		this.monsterId = monsterId;
		this.isRedQuest = checkForRedQuest(monsterId);
		this.isSpecial = false;
		this.specialQuestType = SpecialQuestTypeEnum.None;
		
		if (isRedQuest) {
			this.isSpecial = true;
			this.specialQuestType = SpecialQuestTypeEnum.RedQuest;
		}
		
		if (item.getType() != ItemTypeEnum.None && item.getIsEpic()) {
			this.isSpecial = true;
			this.specialQuestType = SpecialQuestTypeEnum.hasEpicItem;
		}
		
		if (item.getType() != ItemTypeEnum.None && item.getType().getId() > 100) {
			this.isSpecial = true;
			
			switch (item.getType()) {
			case MirrorPiece:
				this.specialQuestType = SpecialQuestTypeEnum.hasMirrorpiece;
				break;
			case Toiletkey:
				this.specialQuestType = SpecialQuestTypeEnum.hasToiletKey;
				break;
			case DungeonKey:
				this.specialQuestType = SpecialQuestTypeEnum.hasDungeonKey;
				break;
			default:
				this.specialQuestType = SpecialQuestTypeEnum.None;
				logger.error("Dieser Fall sollte nicht eintreten");
				break;
			}
		}
	}
	
	private Boolean checkForRedQuest(int monsterId) {
		Boolean result;
		
		switch (monsterId) {
		case 148:
        case 152:
        case 155:
        case 157:
        case 139:
        case 145:
			result = true;
			break;
		default:
			result = false;
			break;
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quest " + this.index + ":\n");
		builder.append("	Dauer: " + this.duration + "\n");
		builder.append("	Silber: " + this.silver + "\n");
		builder.append("	Exp: " + this.exp + "\n");
		if (this.item.getType() != ItemTypeEnum.None) {
			builder.append("	Item: " + this.item.toString() + "\n");
		}
		builder.append("	MonsterId: " + this.monsterId + "\n");
		
		return builder.toString();
	}
}
