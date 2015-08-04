package de.binary101.core.data.character;

import java.util.HashMap;
import java.util.Map;

import de.binary101.core.constants.enums.AttributeEnum;

public class AttributeList {
	private Map<AttributeEnum, Attribute> attributeList;
	
	public Attribute getStrength() {
		return this.attributeList.get(AttributeEnum.Strength);
	}
	
	public Attribute getDexterity() {
		return this.attributeList.get(AttributeEnum.Dexterity);
	}
	
	public Attribute getIntelligence() {
		return this.attributeList.get(AttributeEnum.Intelligence);
	}
	
	public Attribute getStamina() {
		return this.attributeList.get(AttributeEnum.Stamina);
	}
	
	public Attribute getLuck() {
		return this.attributeList.get(AttributeEnum.Luck);
	}

	public AttributeList(	final int strength,		final int dexterity,		final int intelligence,		final int stamina,		final int luck, 
							final int strengthBonus, 	final int dexterityBonus,	final int intelligenceBonus, 	final int staminaBonus, 	final int luckBonus,
							final int strengthPrice, 	final int dexterityPrice, final int intelligencePrice,	final int staminaPrice,	final int luckPrice) {

		this.attributeList = new HashMap<AttributeEnum, Attribute>();

		this.attributeList.put(AttributeEnum.Strength, new Attribute() {
			{
				setType(AttributeEnum.Strength);
				setBaseValue(strength);
				setBonusValue(strengthBonus);
				setPriceForNextUpgrade(strengthPrice);
			}
		});

		this.attributeList.put(AttributeEnum.Dexterity, new Attribute() {
			{
				setType(AttributeEnum.Dexterity);
				setBaseValue(dexterity);
				setBonusValue(dexterityBonus);
				setPriceForNextUpgrade(dexterityPrice);
			}
		});

		this.attributeList.put(AttributeEnum.Intelligence, new Attribute() {
			{
				setType(AttributeEnum.Intelligence);
				setBaseValue(intelligence);
				setBonusValue(intelligenceBonus);
				setPriceForNextUpgrade(intelligencePrice);
			}
		});

		this.attributeList.put(AttributeEnum.Stamina, new Attribute() {
			{
				setType(AttributeEnum.Stamina);
				setBaseValue(stamina);
				setBonusValue(staminaBonus);
				setPriceForNextUpgrade(staminaPrice);
			}
		});

		this.attributeList.put(AttributeEnum.Luck, new Attribute() {
			{
				setType(AttributeEnum.Luck);
				setBaseValue(luck);
				setBonusValue(luckBonus);
				setPriceForNextUpgrade(luckPrice);
			}
		});
	}
}
