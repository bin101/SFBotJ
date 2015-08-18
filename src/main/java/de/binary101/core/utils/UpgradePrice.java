package de.binary101.core.utils;

import lombok.Getter;

public class UpgradePrice {
	@Getter long silverPrice;
	@Getter public int mushroomPrice;
	
	public UpgradePrice(long silverPrice, int mushroomPrice) {
		this.silverPrice = silverPrice;
		this.mushroomPrice = mushroomPrice;
	}
}
