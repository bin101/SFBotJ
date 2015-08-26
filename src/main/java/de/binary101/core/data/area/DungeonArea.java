package de.binary101.core.data.area;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import de.binary101.core.constants.enums.DungeonEnum;
import de.binary101.core.constants.enums.RequestEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.dungeon.DungeonType;
import de.binary101.core.request.EnterDungeonRequest;
import de.binary101.core.request.Request;
import de.binary101.core.response.PlayerFightResponse;
import de.binary101.core.response.Response;
import de.binary101.core.utils.Helper;

public class DungeonArea extends BaseArea {

	private final static Logger logger = LogManager.getLogger(DungeonArea.class);

	public DungeonArea(Account account) {
		super(account);
	}

	@Override
	public void performArea() {

		if (!account.getSetting().getPerformDungeon()
				|| account.getOwnCharacter().getDungeonTimer().isAfter(DateTime.now())
				|| account.getOwnCharacter().getBackpack().getIsFull()) {
			return;
		}

		if (!account.getHasRunningAction() || account.getHasCompletedMirror()) {
			Helper.threadSleepRandomBetween(1000, 2000);
			String responseString = sendRequest(new Request(RequestEnum.DungeonScreen));
			new Response(responseString, account);

			DungeonType nextDungeon = account.getDungeons().getNextDungeon();

			if (nextDungeon.getDungeonMonsterLvl() <= account.getOwnCharacter().getLevel()) {
				Helper.threadSleepRandomBetween(1000, 2000);

				logger.info(String.format(
						"Versuche mich im Dungeon %s auf der Ebene %d. Das Monster scheint Level %d zu sein.",
						DungeonEnum.fromInt(nextDungeon.getDungeonID() - 1), nextDungeon.getDungeonEbene(),
						nextDungeon.getDungeonMonsterLvl()));

				responseString = sendRequest(new EnterDungeonRequest(nextDungeon));
				PlayerFightResponse fightResult = new PlayerFightResponse(responseString, account);

				logger.info(fightResult.toString());
			}
		}
	}
}
