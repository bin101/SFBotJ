package de.binary101.core.data.item;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.ItemTypeEnum;

public class MirrorpieceOrKey extends Item {

	private final static Logger logger = LogManager
			.getLogger(MirrorpieceOrKey.class);

	@Getter
	private int piece;

	public MirrorpieceOrKey(Long[] itemData, int backpackIndex) {
		super(itemData, backpackIndex);

		if (this.getPicNumber() >= 1 && this.getPicNumber() <= 10) {
			this.setType(ItemTypeEnum.DungeonKey);
		} else if (this.getPicNumber() == 20) {
			this.setType(ItemTypeEnum.Toiletkey);
		} else {
			this.piece = this.getPicNumber() - 29;
			this.setType(ItemTypeEnum.MirrorPiece);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Type:" + this.getType());

		switch (this.getType()) {
		case DungeonKey:
			builder.append(" KeyNr:" + this.getPicNumber());
			break;
		case MirrorPiece:
			builder.append(" PieceNr:" + this.piece);
			break;
		case Toiletkey:
			// nichts zu appenden
			break;
		default:
			logger.error("Unbekanntes Special Item");
			break;
		}

		return builder.toString();
	}
}
