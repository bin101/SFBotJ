package de.binary101.core.data.area;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import de.binary101.core.data.account.Account;
import de.binary101.core.request.DonateGoldRequest;
import de.binary101.core.response.Response;
import de.binary101.core.utils.Helper;

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
			if ((account.getHasEnoughALUForOneQuest() || !account.getSetting().getPerformQuesten()) 
					&& account.getSetting().getDonateGoldToGuild()
					&& !account.getHasRunningAction()
					&& account.getGuild().getLastDonateTime().getDayOfYear() != DateTime.now().getDayOfYear()
					) {
				logger.info("Spenden wir mal der Gilde etwas Gold");
				Helper.threadSleepRandomBetween(1000, 2000);
				
				Long silverAmountToDonate = account.getOwnCharacter().getSilver() / 100 * account.getSetting().getDonatePercentage();
				// Nur Goldwerte spenden
				silverAmountToDonate = silverAmountToDonate - silverAmountToDonate % 100;
				// auf eine glate summe abrunden statt 5487 Gold lieber 5000 gold spenden
				silverAmountToDonate = (long) (silverAmountToDonate - silverAmountToDonate % Math.pow(10, silverAmountToDonate.toString().length() - 1));
				
				if (silverAmountToDonate > (1000000000 - account.getGuild().getSilver())) {
					//Spende nur den Betrag, der zum Gold-Cap der Gilde benötigt wird
					silverAmountToDonate = 1000000000 - account.getGuild().getSilver();
				} // else do nothing
				
				if (silverAmountToDonate > 0) {
					if (donateGold(silverAmountToDonate)) {
						logger.info(String.format("Habe gerade %s gold an die Gilde gespendet", silverAmountToDonate / 100));
						account.getGuild().setLastDonateTime(DateTime.now());
					}
				}
			}
		} else {
			return;
		}
		
	}

	private Boolean donateGold(long silverAmountToDonate) {
		Boolean result = false;
		
		String responseString = sendRequest(new DonateGoldRequest(silverAmountToDonate));
		Response response = new Response(responseString, account);
		
		if (!response.getHasError()) {
			result = true;
		} else {
			logger.error("Es ist ein Fehler beim Spenden aufgetreten: " + response.getErrorCode());
		}
		
		return result;
	}
}
