package de.binary101.core.data.item;

import lombok.Getter;
import lombok.Setter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.AttributeEnum;
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
	@Getter private long silverPrice;
	@Getter private int mushroomPrice;
	@Getter private Boolean stinks;
	@Getter private EnchantmentTypeEnum enchantment;
	@Getter private int slotIndex;

	public Item(Long[] itemData, int slotIndex) {

		int type = itemData[0].intValue();
		int picNumber = itemData[1].intValue();
		int mushroomPrice = itemData[11].intValue();
		int helper1 = (int) (type / Math.pow(2.0, 24.0));
		int helper2 = (int) (picNumber / Math.pow(2.0, 16.0));

		this.slotIndex = slotIndex;

		if (type > 99) {
			type = (int) (type - helper1 * Math.pow(2.0, 24.0));
			picNumber = (int) (picNumber - helper2 * Math.pow(2.0, 16.0));
			int helper3 = (int) (mushroomPrice / Math.pow(2.0, 16.0));
			mushroomPrice = (int) (mushroomPrice - helper3 * Math.pow(2.0, 16.0));
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
				this.armor = itemData[2].intValue();
			}

			if (!(this instanceof Potion) && !(this instanceof MirrorpieceOrKey)) {
				appendAttribute(AttributeEnum.fromInt(itemData[4].intValue()), itemData[7].intValue());
				appendAttribute(AttributeEnum.fromInt(itemData[5].intValue()), itemData[8].intValue());
				appendAttribute(AttributeEnum.fromInt(itemData[6].intValue()), itemData[9].intValue());
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

	public static Item createItem(Long[] longArray, int itemOffset) {
		Item resultingItem = null;

		int slotIndex = (itemOffset / 12) + 1;

		Long[] itemData = new Long[12];
		System.arraycopy(longArray, itemOffset, itemData, 0, 12);

		int helper = (int) ((double) itemData[0] / Math.pow(2, 24));
		ItemTypeEnum type = ItemTypeEnum.fromInt(itemData[0].intValue() > 99 ? (int) (itemData[0].intValue() - helper
				* Math.pow(2.0, 24.0)) : itemData[0].intValue());

		switch (type) {
			case Shield:
				resultingItem = new Shield(itemData, slotIndex);
				break;
			case Weapon:
				resultingItem = new Weapon(itemData, slotIndex);
				break;
			case Potion:
				resultingItem = new Potion(itemData, slotIndex);
				break;
			case MirrorOrKey:
				resultingItem = new MirrorpieceOrKey(itemData, slotIndex);
				break;
			default:
				resultingItem = new Item(itemData, slotIndex);
				break;
		}

		return resultingItem;

	}

	@Override
	public String toString() {
		return String.format("Type:%s Str:%s Dex:%s Int:%s Sta:%s Luc:%s Epic:%s isEpic:%s Value:%s", this.type,
				this.strength, this.dexterity, this.intelligence, this.stamina, this.luck, this.epicValue, this.isEpic,
				this.silverPrice);
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

	private void appendAttribute(AttributeEnum attributeType, int attributeValue) {
		switch (attributeType) {
			case Strength:
				this.strength = attributeValue;
				break;
			case Dexterity:
				this.dexterity = attributeValue;
				break;
			case Intelligence:
				this.intelligence = attributeValue;
				break;
			case Stamina:
				this.stamina = attributeValue;
				break;
			case Luck:
				this.luck = attributeValue;
				break;
			case Epic:
				this.epicValue = attributeValue;
				break;
			case None:
				// Nothing to add
				break;
			default:
				logger.error("Unbekannter AttributeType bei Item: " + this.toString());
				break;
		}
	}

	private Boolean isEpicCheck(int picNumber) {
		Boolean result = false;

		while (picNumber > 1000) {
			picNumber -= 1000;
		}

		result = (picNumber >= 50);

		return result;
	}
}
