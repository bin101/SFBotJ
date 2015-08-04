package de.binary101.core.data.item;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.PotionEnum;
import de.binary101.core.constants.enums.PotionSizeEnum;

public class Potion extends Item {
	
	private final static Logger logger = LogManager.getLogger(Potion.class);
	
	@Getter PotionEnum potionType;
	@Getter PotionSizeEnum potionSize;

	public Potion(Long[] itemData, int backpackIndex) {
		super(itemData, backpackIndex);
		
		this.potionType = getPotionType(itemData[1].intValue());
		this.potionSize = getPotionSize(itemData[1].intValue());
	}
	
	@Override
	public String toString() {
		return String.format("Type:%s PotionType:%s PotionSize:%s Value:%s", this.getType(), this.potionType, this.potionSize, this.getSilverPrice());
	}
	
	private PotionEnum getPotionType (int rawValue) {
		PotionEnum potionType = PotionEnum.None;
		
		switch (rawValue) {
		case 1:
		case 6:
		case 11:
			potionType = PotionEnum.Strength;
			break;
		case 2:
		case 7:
		case 12:
			potionType = PotionEnum.Dexterity;
			break;
		case 3:
		case 8:
		case 13:
			potionType = PotionEnum.Intelligence;
			break;
		case 4:
		case 9:
		case 14:
			potionType = PotionEnum.Stamina;
			break;
		case 5:
		case 10:
		case 15:
			potionType = PotionEnum.Luck;
			break;
		case 16:
			potionType = PotionEnum.Eternal_Life;
		default:
			logger.error("Unbekannter Potion Typ");
			break;
		}
		
		return potionType;
	}
	
	private PotionSizeEnum getPotionSize (int rawValue) {
		PotionSizeEnum potionSize = PotionSizeEnum.Small;

		switch (rawValue) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			potionSize = PotionSizeEnum.Small;
			break;
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			potionSize = PotionSizeEnum.Medium;
			break;
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
			potionSize = PotionSizeEnum.Big;
			break;
		default:
			logger.error("Unbekannte Potion Groesse");
			break;
		}

		return potionSize;
	}
}
