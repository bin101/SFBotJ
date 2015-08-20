package de.binary101.core.data.character;

import lombok.Getter;
import lombok.Setter;
import de.binary101.core.constants.enums.AttributeEnum;

public class Attribute {
	@Getter @Setter private AttributeEnum type;
	@Getter @Setter private int baseValue;
	@Getter @Setter private int bonusValue;
	@Getter @Setter private long priceForNextUpgrade;
}
