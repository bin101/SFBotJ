package de.binary101.core.data.area;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import de.binary101.core.constants.enums.GuildRankEnum;
import de.binary101.core.constants.enums.GuildUpgradeTypeEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.guild.GuildUpgrade;
import de.binary101.core.request.DonateGoldRequest;
import de.binary101.core.request.UpgradeGuildRequest;
import de.binary101.core.response.Response;
import de.binary101.core.utils.Helper;
import de.binary101.core.utils.SettingsManager;

public class GuildArea extends BaseArea {

	private final static Logger logger = LogManager.getLogger(GuildArea.class);

	public GuildArea(Account account) {
		super(account);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performArea() {

		if (!account.getSetting().getPerformGuild()) {
			return;
		}

		if (account.getHasGuild()) {
			if ((!account.getHasEnoughALUForOneQuest() || !account.getSetting()
					.getPerformQuesten())
					&& account.getSetting().getDonateGoldToGuild()
					&& !account.getHasRunningAction()
					&& account.getSetting().getLastDonateDate().getDayOfYear() != DateTime
							.now().getDayOfYear()) {
				logger.info("Spenden wir mal der Gilde etwas Gold");
				Helper.threadSleepRandomBetween(1000, 2000);

				Long silverAmountToDonate = account.getOwnCharacter()
						.getSilver()
						/ 100
						* account.getSetting().getDonatePercentage();
				// Nur Goldwerte spenden
				silverAmountToDonate = silverAmountToDonate
						- silverAmountToDonate % 100;
				// auf eine glate summe abrunden statt 5487 Gold lieber 5000
				// gold spenden
				silverAmountToDonate = (long) (silverAmountToDonate - silverAmountToDonate
						% Math.pow(10,
								silverAmountToDonate.toString().length() - 1));

				if (silverAmountToDonate > (1000000000 - account.getGuild()
						.getSilver())) {
					// Spende nur den Betrag, der zum Gold-Cap der Gilde
					// benötigt wird
					silverAmountToDonate = 1000000000 - account.getGuild()
							.getSilver();
				} // else do nothing

				if (silverAmountToDonate > 0) {
					if (donateGold(silverAmountToDonate)) {
						logger.info(String.format(
								"Habe gerade %s gold an die Gilde gespendet",
								silverAmountToDonate / 100));
						account.getSetting().setLastDonateDate(DateTime.now());
						SettingsManager.saveSettings();
					}
				}
			}

			Helper.threadSleepRandomBetween(1000, 2000);

			GuildRankEnum myRank = account
					.getGuild()
					.getMembers()
					.stream()
					.filter(member -> member.getName().equals(
							account.getSetting().getUsername())).findFirst()
					.get().getGuildRank();
			if (account.getSetting().getUpgradeGuild()
					&& myRank == GuildRankEnum.Leader) {

				Boolean haveBuyedGuildUpgrade = false;
				Boolean canBuyGuildUpgrade = true;
				
				while (canBuyGuildUpgrade) {
					Helper.threadSleepRandomBetween(1000, 2000);
					haveBuyedGuildUpgrade = false;

					GuildUpgrade upgradeWithLowestLevel = account
							.getGuild()
							.getGuildUpgrades()
							.stream()
							.min((upgrade1, upgrade2) -> Integer.compare(
									upgrade1.getLevel(), upgrade2.getLevel()))
							.get();

					if (upgradeWithLowestLevel.getPrices().getSilverPrice() <= account
							.getGuild().getSilver()
							&& upgradeWithLowestLevel.getPrices()
									.getMushroomPrice() <= account.getGuild()
									.getMushrooms()
							&& upgradeGuild(upgradeWithLowestLevel)) {
						
						haveBuyedGuildUpgrade = true;

						logger.info(String
								.format("Habe der Gilde ein Upgrade verpasst, %s ist nun auf Level %s",
										upgradeWithLowestLevel.getUpgradeType() == GuildUpgradeTypeEnum.Fortress ? "die Festung"
												: upgradeWithLowestLevel
														.getUpgradeType() == GuildUpgradeTypeEnum.Treasure ? "die Schatztrue"
														: "der Lehrmeister",
										upgradeWithLowestLevel.getLevel() + 1));
					}
					
					if (!haveBuyedGuildUpgrade) {
						canBuyGuildUpgrade = false;
					}
				}
			}
		} else {
			return;
		}

	}

	private Boolean donateGold(long silverAmountToDonate) {
		Boolean result = false;

		String responseString = sendRequest(new DonateGoldRequest(
				silverAmountToDonate));
		Response response = new Response(responseString, account);

		if (!response.getHasError()) {
			result = true;
		} else {
			logger.error("Es ist ein Fehler beim Spenden aufgetreten: "
					+ response.getErrorCode());
		}

		return result;
	}

	private Boolean upgradeGuild(GuildUpgrade upgradeToUpgrade) {
		Boolean result = false;

		String responseString = sendRequest(new UpgradeGuildRequest(
				upgradeToUpgrade.getUpgradeType()));
		Response response = new Response(responseString, account);

		if (!response.getHasError()) {
			result = true;
		} else {
			logger.error("Es ist ein Fehler beim Spenden aufgetreten: "
					+ response.getErrorCode());
		}

		return result;
	}
}

// public void registerForGuildFights(Account acc, bool Automatic = true)
// {
// if (DateTime.UtcNow > acc.JoinedGuild.AddDays(1.0))
// {
// bool flag1 = false;
// bool flag2 = false;
// if (this.NextFight > DateTime.Now && this.NextDefense > DateTime.Now &&
// acc.AttackStatus != GuildPlayer.AttackState.Both)
// {
// switch (acc.AttackStatus)
// {
// case GuildPlayer.AttackState.None:
// flag2 = true;
// flag1 = true;
// break;
// case GuildPlayer.AttackState.Fight:
// flag2 = true;
// break;
// case GuildPlayer.AttackState.Defense:
// flag1 = true;
// break;
// }
// }
// else if (this.NextFight > DateTime.Now && this.NextDefense < DateTime.Now &&
// acc.AttackStatus != GuildPlayer.AttackState.Fight)
// flag1 = true;
// else if (this.NextFight < DateTime.Now && this.NextDefense > DateTime.Now &&
// acc.AttackStatus != GuildPlayer.AttackState.Defense)
// flag2 = true;
// if (flag1)
// {
// acc.send(new Command(CommandType.JoinAttack, 0));
// acc.Log.writeLog(string.Format(Resources.Gildenkampfmeldung, (object)
// this.NextFight.ToString(), this.AttackedGuild != null ? (object)
// this.AttackedGuild.Name : (object) ""), LogWeight.Info, LogSource.Guild,
// (Exception) null);
// }
// if (!flag2)
// return;
// acc.send(new Command(CommandType.JoinDefense, 0));
// acc.Log.writeLog(string.Format(Resources.Gildenverteidigungmeldung, (object)
// this.NextDefense.ToString(), this.AttackingGuild != null ? (object)
// this.AttackingGuild.Name : (object) ""), LogWeight.Info, LogSource.Guild,
// (Exception) null);
// }
// else if (!Automatic)
// throw new InvalidOperationException(Resources.Erstnach24Stunden);
// }
