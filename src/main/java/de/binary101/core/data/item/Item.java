package de.binary101.core.data.item;

import lombok.Getter;
import lombok.Setter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.ClassEnum;
import de.binary101.core.constants.enums.EnchantmentTypeEnum;
import de.binary101.core.constants.enums.ItemTypeEnum;

public class Item {
	
	private final static Logger logger = LogManager.getLogger(Item.class);
	
	@Getter @Setter private ItemTypeEnum type;
	@Getter private int picNumber;
	@Getter private int strength, dexterity, intelligence, stamina, luck, armor;
	@Getter private ClassEnum itemClass;
	@Getter private Boolean isEpic;
	@Getter private int epicValue;
	@Getter private double silverPrice;
	@Getter private int mushroomPrice;	
	@Getter private Boolean stinks;
	@Getter private EnchantmentTypeEnum enchantment;
	@Getter private int backpackIndex;

	public Item(Integer[] itemData, int backpackIndex) {
		
		int type = itemData[0];
		int picNumber = itemData[1];
		int mushroomPrice = itemData[11];
		int helper1 = (int) ((double) type / Math.pow(2.0, 24.0));
		int helper2 = (int) ((double) picNumber / Math.pow(2.0, 16.0));
		
		if(backpackIndex > -1) {
			this.backpackIndex = backpackIndex;
		}
		
		if (type > 99) {
			type = (int) ((double) type - (double) helper1 * Math.pow(2.0, 24.0));
	        picNumber = (int) ((double) picNumber - (double) helper2 * Math.pow(2.0, 16.0));
	        int helper3 = (int) ((double) mushroomPrice / Math.pow(2.0, 16.0));
	        mushroomPrice = (int) ((double) mushroomPrice - (double) helper3 * Math.pow(2.0, 16.0));
		}
		
		this.type = ItemTypeEnum.fromInt(type);
		
		if (this.type != ItemTypeEnum.None) {
			this.picNumber = picNumber;
			this.silverPrice = itemData[10];
			this.stinks = silverPrice == 0;
			this.isEpic = isEpicCheck(picNumber);
			this.mushroomPrice = mushroomPrice;
			this.enchantment = EnchantmentTypeEnum.fromInt(helper1);
		
			if (!(this instanceof Weapon) && !(this instanceof Potion) && !(this instanceof MirrorpieceOrKey)) {
				this.armor = itemData[2];
			}
			
			if (!(this instanceof Potion) && !(this instanceof MirrorpieceOrKey)) {
				appendAttribute(itemData[4], itemData[7]);
				appendAttribute(itemData[5], itemData[8]);
				appendAttribute(itemData[6], itemData[9]);
			}
			
			switch (this.type) {
			case Amulet:
			case Ring:
			case Talisman:
			case MirrorOrKey:
				this.itemClass = ClassEnum.All;
				break;
			default:
				this.itemClass = calcItemClass(this.picNumber);
				break;
			}
		}
	}
	
	public static Item createItem(Integer[] responseArray, int itemOffset, int backpackIndex) {
		Item resultingItem = null;
		
		Integer[] itemData = new Integer[12];
		System.arraycopy(responseArray, itemOffset, itemData, 0, 12);
		
		int helper = (int)((double)itemData[0] / Math.pow(2, 24));
		ItemTypeEnum type = ItemTypeEnum.fromInt(itemData[0] > 99 ? (int)((double)itemData[0] - (double)helper * Math.pow(2.0, 24.0)) : itemData[0]);
		
		switch (type) {
		case Shield:
			resultingItem = new Shield(itemData, backpackIndex);
			break;
		case Weapon:
			resultingItem = new Weapon(itemData, backpackIndex);
			break;
		case Potion:
			resultingItem = new Potion(itemData, backpackIndex);
			break;
		case MirrorOrKey:
			resultingItem = new MirrorpieceOrKey(itemData, backpackIndex);
			break;
		default:
			resultingItem = new Item(itemData, backpackIndex);
			break;
		}
		
		return resultingItem;
		
	}
	
	@Override
	public String toString() {
		return String.format("Type:%s Str:%s Dex:%s Int:%s Sta:%s Luc:%s Epic:%s Value:%s", this.type, this.strength, this.dexterity, this.intelligence, this.stamina, this.luck, this.epicValue, this.silverPrice);
	}
	
	private ClassEnum calcItemClass(int picNumber) {
		ClassEnum result = null;
		
		int i = 1;
		while (picNumber >= 1000) {
			picNumber -= 1000;
			++i;
		}
		
		result = ClassEnum.fromInt(i);
		
		return result;
	}
	
	private void appendAttribute (int attributeType, int attributeValue) {
		switch (attributeType) {
		case 1:
			this.strength = attributeValue;
			break;
		case 2:
			this.dexterity = attributeValue;
			break;
		case 3:
			this.intelligence = attributeValue;
			break;
		case 4:
			this.stamina = attributeValue;
			break;
		case 5:
			this.luck = attributeValue;
			break;
		case 6:
			this.epicValue = attributeValue;
			break;
		default:
			logger.error("Unbekannter AttributeType");
			break;
		}
	}
	
	private Boolean isEpicCheck (int picNumber) {
		Boolean result = false;
		
		while (picNumber > 1000) {
			picNumber -= 1000;
		}
		
		result = (picNumber >= 50);
		
		return result;
	}
}
