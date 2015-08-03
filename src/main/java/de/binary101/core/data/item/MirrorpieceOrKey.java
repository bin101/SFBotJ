package de.binary101.core.data.item;

import de.binary101.core.constants.enums.ItemTypeEnum;
import lombok.Getter;

public class MirrorpieceOrKey extends Item {

	@Getter	private int piece;

	public MirrorpieceOrKey(Integer[] itemData) {
		super(itemData);

		if (this.getPicNumber() >= 1 && this.getPicNumber() <= 10) {
			this.setType(ItemTypeEnum.DungeonKey);
		} else if (this.getPicNumber() == 20) {
			this.setType(ItemTypeEnum.Toiletkey);
		} else {
			this.piece = this.getPicNumber() - 29;
			this.setType(ItemTypeEnum.MirrorPiece);
		}
	}

	@SuppressWarnings("incomplete-switch")
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
		}

		return builder.toString();
	}
}
