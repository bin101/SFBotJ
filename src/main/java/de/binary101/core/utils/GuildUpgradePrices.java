package de.binary101.core.utils;

import java.util.HashMap;

public class GuildUpgradePrices {

	private GuildUpgradePrices() {
	}

	private static HashMap<Integer, UpgradePrice> staticPrices = new HashMap<Integer, UpgradePrice>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 5078627081195734562L;

		{
			put(1, new UpgradePrice(500, 0));
			put(2, new UpgradePrice(900, 0));
			put(3, new UpgradePrice(1500, 0));
			put(4, new UpgradePrice(2200, 0));
			put(5, new UpgradePrice(3200, 0));
			put(6, new UpgradePrice(4500, 0));
			put(7, new UpgradePrice(6000, 0));
			put(8, new UpgradePrice(7800, 0));
			put(9, new UpgradePrice(10100, 0));
			put(10, new UpgradePrice(12800, 0));

			put(11, new UpgradePrice(16000, 0));
			put(12, new UpgradePrice(19700, 0));
			put(13, new UpgradePrice(24000, 0));
			put(14, new UpgradePrice(29100, 0));
			put(15, new UpgradePrice(34800, 0));
			put(16, new UpgradePrice(41200, 0));
			put(17, new UpgradePrice(48700, 0));
			put(18, new UpgradePrice(57000, 0));
			put(19, new UpgradePrice(66400, 0));
			put(20, new UpgradePrice(77000, 0));

			put(21, new UpgradePrice(88800, 0));
			put(22, new UpgradePrice(101800, 0));
			put(23, new UpgradePrice(116400, 0));
			put(24, new UpgradePrice(132500, 0));
			put(25, new UpgradePrice(150200, 0));
			put(26, new UpgradePrice(169900, 5));
			put(27, new UpgradePrice(191400, 10));
			put(28, new UpgradePrice(214900, 15));
			put(29, new UpgradePrice(240800, 20));
			put(30, new UpgradePrice(269000, 25));

			put(31, new UpgradePrice(299600, 30));
			put(32, new UpgradePrice(333000, 35));
			put(33, new UpgradePrice(369200, 40));
			put(34, new UpgradePrice(408300, 45));
			put(35, new UpgradePrice(450900, 50));
			put(36, new UpgradePrice(496800, 55));
			put(37, new UpgradePrice(546100, 60));
			put(38, new UpgradePrice(599600, 65));
			put(39, new UpgradePrice(656900, 70));
			put(40, new UpgradePrice(718400, 75));

			put(41, new UpgradePrice(784700, 80));
			put(42, new UpgradePrice(855700, 85));
			put(43, new UpgradePrice(931500, 90));
			put(44, new UpgradePrice(1012900, 95));
			put(45, new UpgradePrice(1099700, 100));
			put(46, new UpgradePrice(1192200, 105));
			put(47, new UpgradePrice(1291200, 110));
			put(48, new UpgradePrice(1396500, 115));
			put(49, new UpgradePrice(1508200, 120));
			put(50, new UpgradePrice(1627700, 125));

			put(51, new UpgradePrice(9999999, 99999999));
		}
	};

	public static UpgradePrice getPrice(int level) {
		return staticPrices.get(level);
	}
}
