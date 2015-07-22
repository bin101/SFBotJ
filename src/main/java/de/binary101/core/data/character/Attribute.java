package de.binary101.core.data.character;

import lombok.*;
import de.binary101.core.constants.enums.SkillEnum;

public class Attribute {
	@Getter @Setter private SkillEnum type;
	@Getter @Setter private int baseValue;
	@Getter @Setter private int bonusValue;
	@Getter @Setter private double priceForNextUpgrade;
}
