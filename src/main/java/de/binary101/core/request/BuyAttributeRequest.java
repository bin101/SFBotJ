package de.binary101.core.request;

import lombok.Getter;
import de.binary101.core.constants.enums.AttributeEnum;
import de.binary101.core.constants.enums.RequestEnum;
import de.binary101.core.data.character.Attribute;

public class BuyAttributeRequest extends Request {

	@Getter
	private AttributeEnum targetAttributeType;
	@Getter
	private int targetedAttributeLevel;

	public BuyAttributeRequest(Attribute targetAttribute) {
		super(RequestEnum.BuySkill);

		this.targetAttributeType = targetAttribute.getType();
		this.targetedAttributeLevel = targetAttribute.getBaseValue() + 1;
	}

	@Override
	public String toString() {
		return super.toString() + this.targetAttributeType.getId() + "/"
				+ this.targetedAttributeLevel;
	}

}
