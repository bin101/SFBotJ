package de.binary101.core.data.character;

import java.util.HashMap;
import java.util.Map;

import lombok.*;
import de.binary101.core.constants.enums.SkillEnum;

public class AttributeList {
	@Getter
	private Map<SkillEnum, Attribute> attributeList;

	public AttributeList(	final int strength,		final int dexterity,		final int intelligence,		final int stamina,		final int luck, 
							final int strengthBonus, 	final int dexterityBonus,	final int intelligenceBonus, 	final int staminaBonus, 	final int luckBonus,
							final int strengthPrice, 	final int dexterityPrice, final int intelligencePrice,	final int staminaPrice,	final int luckPrice) {

		this.attributeList = new HashMap<SkillEnum, Attribute>();

		this.attributeList.put(SkillEnum.Strength, new Attribute() {
			{
				setType(SkillEnum.Strength);
				setBaseValue(strength);
				setBonusValue(strengthBonus);
				setPriceForNextUpgrade(strengthPrice >= 10000000 ? 10000000 : strengthPrice);
			}
		});

		this.attributeList.put(SkillEnum.Dexterity, new Attribute() {
			{
				setType(SkillEnum.Dexterity);
				setBaseValue(dexterity);
				setBonusValue(dexterityBonus);
				setPriceForNextUpgrade(dexterityPrice >= 10000000 ? 10000000 : dexterityPrice);
			}
		});

		this.attributeList.put(SkillEnum.Intelligence, new Attribute() {
			{
				setType(SkillEnum.Intelligence);
				setBaseValue(intelligence);
				setBonusValue(intelligenceBonus);
				setPriceForNextUpgrade(intelligencePrice >= 10000000 ? 10000000 : intelligencePrice);
			}
		});

		this.attributeList.put(SkillEnum.Stamina, new Attribute() {
			{
				setType(SkillEnum.Stamina);
				setBaseValue(stamina);
				setBonusValue(staminaBonus);
				setPriceForNextUpgrade(staminaPrice >= 10000000 ? 10000000 : staminaPrice);
			}
		});

		this.attributeList.put(SkillEnum.Luck, new Attribute() {
			{
				setType(SkillEnum.Luck);
				setBaseValue(luck);
				setBonusValue(luckBonus);
				setPriceForNextUpgrade(luckPrice >= 10000000 ? 10000000 : luckPrice);
			}
		});
	}
}
