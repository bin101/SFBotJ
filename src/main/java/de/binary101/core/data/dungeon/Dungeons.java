package de.binary101.core.data.dungeon;

import java.util.ArrayList;
import java.util.stream.Collectors;

import lombok.Getter;
import de.binary101.core.data.account.Account;

public class Dungeons {

	int d1Lvl, d2Lvl, d3Lvl, d4Lvl, d5Lvl, d6Lvl, d7Lvl, d8Lvl, d9Lvl, d10Lvl, d11Lvl, d12Lvl, d13Lvl;

	@Getter DungeonType nextDungeon;

	public synchronized void updateDungeons(Account account, Long[] ownPlayerSaveLong) {

		this.d1Lvl = ownPlayerSaveLong[480].intValue() - 1;
		this.d2Lvl = ownPlayerSaveLong[481].intValue() - 1;
		this.d3Lvl = ownPlayerSaveLong[482].intValue() - 1;
		this.d4Lvl = ownPlayerSaveLong[483].intValue() - 1;
		this.d5Lvl = ownPlayerSaveLong[484].intValue() - 1;
		this.d6Lvl = ownPlayerSaveLong[485].intValue() - 1;
		this.d7Lvl = ownPlayerSaveLong[486].intValue() - 1;
		this.d8Lvl = ownPlayerSaveLong[487].intValue() - 1;
		this.d9Lvl = ownPlayerSaveLong[488].intValue() - 1;
		this.d10Lvl = ownPlayerSaveLong[489].intValue() - 1;

		this.d11Lvl = ownPlayerSaveLong[441].intValue() - 1;
		this.d12Lvl = ownPlayerSaveLong[442].intValue() - 1;

		this.d13Lvl = ownPlayerSaveLong[490].intValue() - 121;

		this.nextDungeon = FilterNextDungeon(d1Lvl, d2Lvl, d3Lvl, d4Lvl, d5Lvl, d6Lvl, d7Lvl, d8Lvl, d9Lvl, d10Lvl,
				d11Lvl, d12Lvl, d13Lvl);
	}

	private static DungeonType FilterNextDungeon(int d1Lvl, int d2Lvl, int d3Lvl, int d4Lvl, int d5Lvl, int d6Lvl,
			int d7Lvl, int d8Lvl, int d9Lvl, int d10Lvl, int d11Lvl, int d12Lvl, int d13Lvl) {
		DungeonType result = null;
		ArrayList<DungeonType> nextDungeons = new ArrayList<DungeonType>();

		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d1Lvl <= 10 && d.dungeonID == 1 && d.dungeonEbene == d1Lvl && d1Lvl > 0))
				.collect(Collectors.toList()));
		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d2Lvl <= 10 && d.dungeonID == 2 && d.dungeonEbene == d2Lvl && d2Lvl > 0))
				.collect(Collectors.toList()));
		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d3Lvl <= 10 && d.dungeonID == 3 && d.dungeonEbene == d3Lvl && d3Lvl > 0))
				.collect(Collectors.toList()));
		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d4Lvl <= 10 && d.dungeonID == 4 && d.dungeonEbene == d4Lvl && d4Lvl > 0))
				.collect(Collectors.toList()));
		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d5Lvl <= 10 && d.dungeonID == 5 && d.dungeonEbene == d5Lvl && d5Lvl > 0))
				.collect(Collectors.toList()));
		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d6Lvl <= 10 && d.dungeonID == 6 && d.dungeonEbene == d6Lvl && d6Lvl > 0))
				.collect(Collectors.toList()));
		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d7Lvl <= 10 && d.dungeonID == 7 && d.dungeonEbene == d7Lvl && d7Lvl > 0))
				.collect(Collectors.toList()));
		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d8Lvl <= 10 && d.dungeonID == 8 && d.dungeonEbene == d8Lvl && d8Lvl > 0))
				.collect(Collectors.toList()));
		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d9Lvl <= 10 && d.dungeonID == 9 && d.dungeonEbene == d9Lvl && d9Lvl > 0))
				.collect(Collectors.toList()));
		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d10Lvl <= 10 && d.dungeonID == 10 && d.dungeonEbene == d10Lvl && d10Lvl > 0))
				.collect(Collectors.toList()));
		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d11Lvl <= 10 && d.dungeonID == 11 && d.dungeonEbene == d11Lvl && d11Lvl > 0))
				.collect(Collectors.toList()));
		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d12Lvl <= 10 && d.dungeonID == 12 && d.dungeonEbene == d12Lvl && d12Lvl > 0))
				.collect(Collectors.toList()));
		nextDungeons.addAll(dungeons.stream()
				.filter(d -> (d13Lvl <= 10 && d.dungeonID == 13 && d.dungeonEbene == d13Lvl && d13Lvl > 0))
				.collect(Collectors.toList()));

		if (nextDungeons.size() != 0) {
			result = nextDungeons.stream().min((d1, d2) -> Integer.compare(d1.dungeonMonsterLvl, d2.dungeonMonsterLvl))
					.get();
		}

		return result;
	}

	private static ArrayList<DungeonType> dungeons = new ArrayList<DungeonType>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			this.add(new DungeonType(1, 1, 10));
			this.add(new DungeonType(1, 2, 12));
			this.add(new DungeonType(1, 3, 14));
			this.add(new DungeonType(1, 4, 16));
			this.add(new DungeonType(1, 5, 18));
			this.add(new DungeonType(1, 6, 22));
			this.add(new DungeonType(1, 7, 26));
			this.add(new DungeonType(1, 8, 30));
			this.add(new DungeonType(1, 9, 40));
			this.add(new DungeonType(1, 10, 50));

			this.add(new DungeonType(2, 1, 20));
			this.add(new DungeonType(2, 2, 24));
			this.add(new DungeonType(2, 3, 28));
			this.add(new DungeonType(2, 4, 34));
			this.add(new DungeonType(2, 5, 38));
			this.add(new DungeonType(2, 6, 44));
			this.add(new DungeonType(2, 7, 48));
			this.add(new DungeonType(2, 8, 56));
			this.add(new DungeonType(2, 9, 66));
			this.add(new DungeonType(2, 10, 77));

			this.add(new DungeonType(3, 1, 32));
			this.add(new DungeonType(3, 2, 36));
			this.add(new DungeonType(3, 3, 42));
			this.add(new DungeonType(3, 4, 46));
			this.add(new DungeonType(3, 5, 54));
			this.add(new DungeonType(3, 6, 60));
			this.add(new DungeonType(3, 7, 64));
			this.add(new DungeonType(3, 8, 76));
			this.add(new DungeonType(3, 9, 86));
			this.add(new DungeonType(3, 10, 90));

			this.add(new DungeonType(4, 1, 52));
			this.add(new DungeonType(4, 2, 58));
			this.add(new DungeonType(4, 3, 62));
			this.add(new DungeonType(4, 4, 68));
			this.add(new DungeonType(4, 5, 74));
			this.add(new DungeonType(4, 6, 82));
			this.add(new DungeonType(4, 7, 84));
			this.add(new DungeonType(4, 8, 96));
			this.add(new DungeonType(4, 9, 102));
			this.add(new DungeonType(4, 10, 110));

			this.add(new DungeonType(5, 1, 72));
			this.add(new DungeonType(5, 2, 78));
			this.add(new DungeonType(5, 3, 80));
			this.add(new DungeonType(5, 4, 88));
			this.add(new DungeonType(5, 5, 94));
			this.add(new DungeonType(5, 6, 100));
			this.add(new DungeonType(5, 7, 108));
			this.add(new DungeonType(5, 8, 114));
			this.add(new DungeonType(5, 9, 122));
			this.add(new DungeonType(5, 10, 130));

			this.add(new DungeonType(6, 1, 92));
			this.add(new DungeonType(6, 2, 98));
			this.add(new DungeonType(6, 3, 104));
			this.add(new DungeonType(6, 4, 106));
			this.add(new DungeonType(6, 5, 118));
			this.add(new DungeonType(6, 6, 124));
			this.add(new DungeonType(6, 7, 128));
			this.add(new DungeonType(6, 8, 136));
			this.add(new DungeonType(6, 9, 144));
			this.add(new DungeonType(6, 10, 150));

			this.add(new DungeonType(7, 1, 112));
			this.add(new DungeonType(7, 2, 116));
			this.add(new DungeonType(7, 3, 120));
			this.add(new DungeonType(7, 4, 126));
			this.add(new DungeonType(7, 5, 134));
			this.add(new DungeonType(7, 6, 138));
			this.add(new DungeonType(7, 7, 142));
			this.add(new DungeonType(7, 8, 146));
			this.add(new DungeonType(7, 9, 148));
			this.add(new DungeonType(7, 10, 170));

			this.add(new DungeonType(8, 1, 132));
			this.add(new DungeonType(8, 2, 140));
			this.add(new DungeonType(8, 3, 154));
			this.add(new DungeonType(8, 4, 158));
			this.add(new DungeonType(8, 5, 164));
			this.add(new DungeonType(8, 6, 168));
			this.add(new DungeonType(8, 7, 172));
			this.add(new DungeonType(8, 8, 180));
			this.add(new DungeonType(8, 9, 185));
			this.add(new DungeonType(8, 10, 200));

			this.add(new DungeonType(9, 1, 152));
			this.add(new DungeonType(9, 2, 156));
			this.add(new DungeonType(9, 3, 160));
			this.add(new DungeonType(9, 4, 162));
			this.add(new DungeonType(9, 5, 166));
			this.add(new DungeonType(9, 6, 174));
			this.add(new DungeonType(9, 7, 176));
			this.add(new DungeonType(9, 8, 178));
			this.add(new DungeonType(9, 9, 190));
			this.add(new DungeonType(9, 10, 201)); // ich weiß das lvl hier ist
													// immer genau dein char
													// lvl, aber so lässt es
													// sich am besten berechnen
													// das es der schwerste
													// dungeonlvl ist (zumindest
													// in den Standard dungeons
													// 1-9)

			this.add(new DungeonType(10, 1, 205));
			this.add(new DungeonType(10, 2, 210));
			this.add(new DungeonType(10, 3, 215));
			this.add(new DungeonType(10, 4, 220));
			this.add(new DungeonType(10, 5, 225));
			this.add(new DungeonType(10, 6, 230));
			this.add(new DungeonType(10, 7, 235));
			this.add(new DungeonType(10, 8, 240));
			this.add(new DungeonType(10, 9, 245));
			this.add(new DungeonType(10, 10, 250));

			this.add(new DungeonType(11, 1, 255));
			this.add(new DungeonType(11, 2, 260));
			this.add(new DungeonType(11, 3, 265));
			this.add(new DungeonType(11, 4, 270));
			this.add(new DungeonType(11, 5, 275));
			this.add(new DungeonType(11, 6, 280));
			this.add(new DungeonType(11, 7, 285));
			this.add(new DungeonType(11, 8, 290));
			this.add(new DungeonType(11, 9, 295));
			this.add(new DungeonType(11, 10, 300));

			this.add(new DungeonType(12, 1, 305));
			this.add(new DungeonType(12, 2, 310));
			this.add(new DungeonType(12, 3, 315));
			this.add(new DungeonType(12, 4, 320));
			this.add(new DungeonType(12, 5, 325));
			this.add(new DungeonType(12, 6, 330));
			this.add(new DungeonType(12, 7, 335));
			this.add(new DungeonType(12, 8, 340));
			this.add(new DungeonType(12, 9, 345));
			this.add(new DungeonType(12, 10, 350));

			this.add(new DungeonType(13, 1, 355));
			this.add(new DungeonType(13, 2, 360));
			this.add(new DungeonType(13, 3, 365));
			this.add(new DungeonType(13, 4, 370));
			this.add(new DungeonType(13, 5, 375));
			this.add(new DungeonType(13, 6, 380));
			this.add(new DungeonType(13, 7, 385));
			this.add(new DungeonType(13, 8, 390));
			this.add(new DungeonType(13, 9, 395));
			this.add(new DungeonType(13, 10, 400));
		};
	};
}
