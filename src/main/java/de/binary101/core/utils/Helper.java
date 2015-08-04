package de.binary101.core.utils;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.ClassEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.item.Item;
import de.binary101.core.data.item.Weapon;

public class Helper {
	private Helper() {}
	
	private final static Logger logger = LogManager.getLogger(Helper.class);
	
	public static void ThreadSleep(int minMilliSec, int maxMilliSec) {
		try {
			Thread.sleep((long)((Math.random() * (maxMilliSec - minMilliSec)) + minMilliSec));
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}
	
	public static int normalizeResponseInt(int value) {
		int result = 0;
		
		result = (int)((double)value - Math.round((double)value / 65536.0f) * 65536.0f);
		
		return result;
	}
	
	public static Boolean isBackpackItemBetter(Item backpackItem, Item equippedItem, Account account) {
		
		int backpackItemStrength = backpackItem.getEpicValue() > 0 ? backpackItem.getEpicValue() : backpackItem.getStrength();
		int backpackItemDexterity = backpackItem.getEpicValue() > 0 ? backpackItem.getEpicValue() : backpackItem.getDexterity();
		int backpackItemIntelligence = backpackItem.getEpicValue() > 0 ? backpackItem.getEpicValue() : backpackItem.getIntelligence();
		int backpackItemStamina = backpackItem.getEpicValue() > 0 ? backpackItem.getEpicValue() : backpackItem.getStamina();
		int backpackItemLuck = backpackItem.getEpicValue() > 0 ? backpackItem.getEpicValue() : backpackItem.getLuck();
		int backpackItemArmor = backpackItem.getArmor();
		int backpackItemAverageWeaponDmg = (backpackItem instanceof Weapon) ? (((Weapon)backpackItem).getMinDmg() + ((Weapon)backpackItem).getMaxDmg()) / 2 : 0;
		
		int equippedItemStrength = equippedItem.getEpicValue() > 0 ? equippedItem.getEpicValue() : equippedItem.getStrength();
		int equippedItemDexterity = equippedItem.getEpicValue() > 0 ? equippedItem.getEpicValue() : equippedItem.getDexterity();
		int equippedItemIntelligence = equippedItem.getEpicValue() > 0 ? equippedItem.getEpicValue() : equippedItem.getIntelligence();
		int equippedItemStamina = equippedItem.getEpicValue() > 0 ? equippedItem.getEpicValue() : equippedItem.getStamina();
		int equippedItemLuck = equippedItem.getEpicValue() > 0 ? equippedItem.getEpicValue() : equippedItem.getLuck();
		int equippedItemArmor = equippedItem.getArmor();
		int equippedItemAverageWeaponDmg = (equippedItem instanceof Weapon) ? (((Weapon)equippedItem).getMinDmg() + ((Weapon)equippedItem).getMaxDmg()) / 2 : 0;
		
		int mainMultiplier = 10;
		int dmgMultiplier = 9;
		int ausMultiplier = 8;
		int luckMultiplier = 6;
		int armorMultiplier = account.getOwnCharacter().getCharClass() == ClassEnum.Warrior ? 7 : account.getOwnCharacter().getCharClass() == ClassEnum.Scout ? 5 : 2;
		
		int backpackItemSummary = 0;
		int equippedItemSummary = 0;
		
		switch (account.getOwnCharacter().getCharClass()) {
		case Mage:
			backpackItemSummary = (backpackItemIntelligence * mainMultiplier) + (backpackItemStamina * ausMultiplier) + (backpackItemLuck * luckMultiplier) + (backpackItemAverageWeaponDmg * dmgMultiplier) + (backpackItemArmor * armorMultiplier);
			equippedItemSummary = (equippedItemIntelligence * mainMultiplier) + (equippedItemStamina * ausMultiplier) + (equippedItemLuck * luckMultiplier) + (equippedItemAverageWeaponDmg * dmgMultiplier) + (equippedItemArmor * armorMultiplier);
			break;
		case Scout:
			backpackItemSummary = (backpackItemDexterity * mainMultiplier) + (backpackItemStamina * ausMultiplier) + (backpackItemLuck * luckMultiplier) + (backpackItemAverageWeaponDmg * dmgMultiplier) + (backpackItemArmor * armorMultiplier);
			equippedItemSummary = (equippedItemDexterity * mainMultiplier) + (equippedItemStamina * ausMultiplier) + (equippedItemLuck * luckMultiplier) + (equippedItemAverageWeaponDmg * dmgMultiplier) + (equippedItemArmor * armorMultiplier);
			break;
		case Warrior:
			backpackItemSummary = (backpackItemStrength * mainMultiplier) + (backpackItemStamina * ausMultiplier) + (backpackItemLuck * luckMultiplier) + (backpackItemAverageWeaponDmg * dmgMultiplier) + (backpackItemArmor * armorMultiplier);
			equippedItemSummary = (equippedItemStrength * mainMultiplier) + (equippedItemStamina * ausMultiplier) + (equippedItemLuck * luckMultiplier) + (equippedItemAverageWeaponDmg * dmgMultiplier) + (equippedItemArmor * armorMultiplier);
			break;
		default:
			logger.error("Unbekannte Klasse");
			break;
		}
		
		return backpackItemSummary > equippedItemSummary;
	}
}
