package de.binary101.core.data.area.tavern;

import lombok.Getter;

public class Quest {
	
	@Getter private int index;
	@Getter private int duration;
	@Getter private long silver;
	@Getter private int exp;
	//TODO @Getter private Item item			Item auch mit in die Gold Berechnung
	@Getter private int monsterId;
	@Getter private Boolean isRedQuest;
	
	public double getExpPerSecond() {
		return this.exp / this.duration;
	}
	
	public double getSilverPerSecond() {
		return this.silver / this.duration;
	}
	
	public Quest(int index, int duration, long silver, int exp/*TODO, Item item*/, int monsterId) {
		this.index = index;
		this.duration = duration;
		this.silver = silver;
		this.exp = exp;
		this.monsterId = monsterId;
		this.isRedQuest = checkForRedQuest(monsterId);
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
}
