package de.binary101.core.utils;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.ClassEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.item.Item;
import de.binary101.core.data.item.Weapon;

public class Helper {
	private Helper() {
	}

	private final static Logger logger = LogManager.getLogger(Helper.class);

	private static int getRealAttributePrice(int statAmount, int[] goldCurve) {
		int gold = goldCurve[statAmount];
		return gold <= 0 || gold > 10000000 || statAmount > 15000 ? 10000000 : gold;
	}

	public static int getRealAttributePrice(int attributePriceToTransform) {
		return getRealAttributePrice(attributePriceToTransform, getGoldCurve());
	}

	private static int[] goldCurve;

	private static int[] getGoldCurve() {
		if (goldCurve == null) {
			goldCurve = calcGoldCurve();
		}
		return goldCurve;
	}

	private static int[] calcGoldCurve() {
		int priceLimit = 10000000;

		Boolean attPriceLimitation = false;
		int[] GoldKurve = new int[15001];
		int[] TrueAttPreis = new int[15001];
		GoldKurve[1] = 25;
		GoldKurve[2] = 50;
		GoldKurve[3] = 75;
		int i = 4;
		while (i <= 15000) {
			GoldKurve[i] = (((GoldKurve[(i - 1)]) + ((GoldKurve[((i / 2))] / 3))) + ((GoldKurve[((i / 3))] / 4)));
			GoldKurve[i] = ((GoldKurve[i] / 5));
			GoldKurve[i] = (GoldKurve[i] * 5);
			i = (i + 1);
		}
		;
		i = 0;
		while (i <= 15000) {
			TrueAttPreis[i] = GoldKurve[((1 + (i / 5)))];
			i = (i + 1);
		}
		;
		i = 0;
		while (i <= 14996) {
			if (attPriceLimitation) {
				TrueAttPreis[i] = priceLimit;
			} else {
				TrueAttPreis[i] = (((((TrueAttPreis[i]) + (TrueAttPreis[(i + 1)])) + (TrueAttPreis[(i + 2)])) + (TrueAttPreis[(i + 3)])) + (TrueAttPreis[(i + 4)]));
				TrueAttPreis[i] = ((TrueAttPreis[i] / 5));
				TrueAttPreis[i] = ((TrueAttPreis[i] / 5));
				TrueAttPreis[i] = ((TrueAttPreis[i] * 5));
				if (TrueAttPreis[i] > priceLimit) {
					TrueAttPreis[i] = priceLimit;
					attPriceLimitation = true;
				}
				;
			}
			;
			i = (i + 1);
		}
		;

		return TrueAttPreis;
	}

	public static int randomBetween(double min, double max) {
		return (int) (Math.random() * (max - min) + min);
	}

	public static void threadSleep(Long sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void threadSleepRandomBetween(int minMilliSec, int maxMilliSec) {
		try {
			Thread.sleep((long) ((Math.random() * (maxMilliSec - minMilliSec)) + minMilliSec));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static int normalizeResponseInt(int value) {
		int result = 0;

		result = (int) ((double) value - Math.round((double) value / 65536.0f) * 65536.0f);

		return result;
	}
	
	public static Boolean isShopItemBetter(Item shopItem, Account account) {
		Boolean result = false;
		
		Optional<Item> equippedItem = account.getOwnCharacter().getEquipment().getItems().stream()
				.filter(item -> item.getType().name().equals(shopItem.getType().name()))
				.findFirst();


		if (equippedItem.isPresent()) {
			result = Helper.isFirstItemBetterThanSecondItem(shopItem, equippedItem.get(),
					account);
		} else {
			result = true;
		}
		
		return result;
	}

	public static Boolean isFirstItemBetterThanSecondItem(Item firstItem, Item secondItem, Account account) {

		int backpackItemStrength = firstItem.getEpicValue() > 0 ? firstItem.getEpicValue() : firstItem
				.getStrength();
		int backpackItemDexterity = firstItem.getEpicValue() > 0 ? firstItem.getEpicValue() : firstItem
				.getDexterity();
		int backpackItemIntelligence = firstItem.getEpicValue() > 0 ? firstItem.getEpicValue() : firstItem
				.getIntelligence();
		int backpackItemStamina = firstItem.getEpicValue() > 0 ? firstItem.getEpicValue() : firstItem
				.getStamina();
		int backpackItemLuck = firstItem.getEpicValue() > 0 ? firstItem.getEpicValue() : firstItem.getLuck();
		int backpackItemArmor = firstItem.getArmor();
		int backpackItemAverageWeaponDmg = (firstItem instanceof Weapon) ? (((Weapon) firstItem).getMinDmg() + ((Weapon) firstItem)
				.getMaxDmg()) / 2 : 0;

		int equippedItemStrength = secondItem.getEpicValue() > 0 ? secondItem.getEpicValue() : secondItem
				.getStrength();
		int equippedItemDexterity = secondItem.getEpicValue() > 0 ? secondItem.getEpicValue() : secondItem
				.getDexterity();
		int equippedItemIntelligence = secondItem.getEpicValue() > 0 ? secondItem.getEpicValue() : secondItem
				.getIntelligence();
		int equippedItemStamina = secondItem.getEpicValue() > 0 ? secondItem.getEpicValue() : secondItem
				.getStamina();
		int equippedItemLuck = secondItem.getEpicValue() > 0 ? secondItem.getEpicValue() : secondItem.getLuck();
		int equippedItemArmor = secondItem.getArmor();
		int equippedItemAverageWeaponDmg = (secondItem instanceof Weapon) ? (((Weapon) secondItem).getMinDmg() + ((Weapon) secondItem)
				.getMaxDmg()) / 2 : 0;

		int mainMultiplier = 10;
		int dmgMultiplier = 9;
		int ausMultiplier = 8;
		int luckMultiplier = 6;
		int armorMultiplier = account.getOwnCharacter().getCharClass() == ClassEnum.Warrior ? 7 : account
				.getOwnCharacter().getCharClass() == ClassEnum.Scout ? 5 : 2;

		int backpackItemSummary = 0;
		int equippedItemSummary = 0;

		switch (account.getOwnCharacter().getCharClass()) {
			case Mage:
				backpackItemSummary = (backpackItemIntelligence * mainMultiplier)
						+ (backpackItemStamina * ausMultiplier) + (backpackItemLuck * luckMultiplier)
						+ (backpackItemAverageWeaponDmg * dmgMultiplier) + (backpackItemArmor * armorMultiplier);
				equippedItemSummary = (equippedItemIntelligence * mainMultiplier)
						+ (equippedItemStamina * ausMultiplier) + (equippedItemLuck * luckMultiplier)
						+ (equippedItemAverageWeaponDmg * dmgMultiplier) + (equippedItemArmor * armorMultiplier);
				break;
			case Scout:
				backpackItemSummary = (backpackItemDexterity * mainMultiplier) + (backpackItemStamina * ausMultiplier)
						+ (backpackItemLuck * luckMultiplier) + (backpackItemAverageWeaponDmg * dmgMultiplier)
						+ (backpackItemArmor * armorMultiplier);
				equippedItemSummary = (equippedItemDexterity * mainMultiplier) + (equippedItemStamina * ausMultiplier)
						+ (equippedItemLuck * luckMultiplier) + (equippedItemAverageWeaponDmg * dmgMultiplier)
						+ (equippedItemArmor * armorMultiplier);
				break;
			case Warrior:
				backpackItemSummary = (backpackItemStrength * mainMultiplier) + (backpackItemStamina * ausMultiplier)
						+ (backpackItemLuck * luckMultiplier) + (backpackItemAverageWeaponDmg * dmgMultiplier)
						+ (backpackItemArmor * armorMultiplier);
				equippedItemSummary = (equippedItemStrength * mainMultiplier) + (equippedItemStamina * ausMultiplier)
						+ (equippedItemLuck * luckMultiplier) + (equippedItemAverageWeaponDmg * dmgMultiplier)
						+ (equippedItemArmor * armorMultiplier);
				break;
			default:
				logger.error("Unbekannte Klasse");
				break;
		}

		return backpackItemSummary > equippedItemSummary;
	}
}
