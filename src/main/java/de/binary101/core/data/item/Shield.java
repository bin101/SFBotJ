package de.binary101.core.data.item;

import lombok.Getter;

public class Shield extends Item {

	@Getter int blockChance;
	
	public Shield(Long[] itemData, int backpackIndex) {
		super(itemData, backpackIndex);
		
		this.blockChance = itemData[2].intValue();
	}
	
	@Override
	public String toString() {
		return String.format("Type:%s BlockChance:%s Str:%s Dex:%s Int:%s Sta:%s Luc:%s Epic:%s Value:%s", this.getType(), this.blockChance, this.getStrength(), this.getDexterity(), this.getIntelligence(), this.getStamina(), this.getLuck(), this.getEpicValue(), this.getSilverPrice());
	}
}
