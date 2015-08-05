package de.binary101.core.data.area;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import de.binary101.core.constants.enums.ActionEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.request.TownwatchFinishRequest;
import de.binary101.core.request.TownwatchStartRequest;
import de.binary101.core.response.Response;
import de.binary101.core.utils.Helper;

public class TownwatchArea extends BaseArea {
	
	private final static Logger logger = LogManager.getLogger(TownwatchArea.class);

	public TownwatchArea(Account account) {
		super(account);
	}
	
	@Override
	public void performArea() {
		if (account.getHasRunningAction() && account.getActionType() == ActionEnum.Townwatch && account.getActionEndTime().isAfterNow()) {
			account.logout();
			
			Long sleepTime = account.getActionEndTime().getMillis() - DateTime.now().getMillis();
			logger.info(String.format("Bin derzeit auf Stadtwache, werde mich daher bis %s ausloggen, um weniger auffaellig zu sein", account.getActionEndTime().toString(DateTimeFormat.forPattern("HH:mm:ss"))));
			Helper.threadSleep(sleepTime.intValue(), (int)(sleepTime.intValue() * 1.15));
		}
		
		if (!account.getSetting().getPerformTownwatch() || account.getHasEnoughALUForOneQuest()) {
			return;
		}
		
		if (!account.getHasRunningAction()) {
			logger.info("Mache mich auf den Weg zur Stadtwache");
			Helper.threadSleep(1000, 2000);
			startTownwatch();
			
		} else {
			if ( account.getActionEndTime().isBeforeNow() && account.getActionType() == ActionEnum.Townwatch) {
				Helper.threadSleep(1000, 2000);
				finishTownwatch();
				logger.info("Habe meine Schicht beendet");
			}
		}
	}
	
	private void startTownwatch() {
		int hoursToWork = 0;
		
		int minHourOfDay = account.getSetting().getMinHourOfDayFor10HourTownwatch();
		int maxHourOfDay = account.getSetting().getMaxHourOfDayFor10HourTownwatch();
		
		DateTime minTime = DateTime.now().getHourOfDay() < maxHourOfDay ? DateTime.now().minusDays(1).withHourOfDay(minHourOfDay) : DateTime.now().withHourOfDay(minHourOfDay);
		DateTime maxTime = DateTime.now().getHourOfDay() < maxHourOfDay ? DateTime.now().withHourOfDay(maxHourOfDay) : DateTime.now().plusDays(1).withHourOfDay(maxHourOfDay);
		
		DateTime helper = DateTime.now();
		
		if (minTime.isAfter(helper) && maxTime.isAfter(helper)) {
			hoursToWork = 1;
		} else {
			
			while (helper.isBefore(maxTime)) {
				++hoursToWork;
				helper = helper.plusHours(1);
			}
			
			hoursToWork = Math.min(10, hoursToWork);
		}
		
		logger.info(String.format("Dann passe ich mal fuer %s Stunde/n auf", hoursToWork));
		
		String responseString = sendRequest(new TownwatchStartRequest(hoursToWork));
		Response response = new Response(responseString, account);
		
		logger.info(String.format("Sollte gegen %s fertig sein", account.getActionEndTime().toString(DateTimeFormat.forPattern("HH:mm:ss"))));
		
		account.logout();
		logger.info(String.format("Bin derzeit auf Stadtwache, werde mich daher bis %s ausloggen, um weniger auffaellig zu sein", account.getActionEndTime().toString(DateTimeFormat.forPattern("HH:mm:ss"))));
		Helper.threadSleep(1000 * 60 * 60 * hoursToWork, 1150 * 60 * 60 * hoursToWork);
	}
	
	private void finishTownwatch() {
		String responseString = sendRequest(new TownwatchFinishRequest());
		Response response = new Response(responseString, account);
	}

}
