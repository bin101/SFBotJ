package de.binary101.core.response;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.ResponseEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.utils.SettingsManager;

public class LoginResponse extends Response {

	@Getter
	private String sessionID;
	@Getter
	private int serverVersion;

	private final static Logger logger = LogManager
			.getLogger(LoginResponse.class);

	public LoginResponse(String rawData, Account account) {
		super(rawData, account);

		if (parsedData.containsKey("sessionid")) {
			this.sessionID = parsedData.get("sessionid").get(0);
			logger.info("Aktualisiere die Session-ID: )" + this.sessionID);
			account.getSetting().setSessionID(sessionID);
			SettingsManager.saveSettings();
		}

		if (parsedData.containsKey("serverversion")) {
			this.serverVersion = Integer.parseInt(parsedData.get(
					"serverversion").get(0));
		}

		if (this.getHasError()) {
			if (this.getErrorCode() == ResponseEnum.ERR_LOGIN_COUNT_TOO_LOW
					|| this.getErrorCode() == ResponseEnum.ERR_LOGIN_FAILED) {
				logger.warn("Login war nicht erfolgreich: "
						+ this.getErrorCode());
				account.setIsLoggedIn(false);
			}
		} else {
			account.setIsLoggedIn(true);
			account.getPollArea().setPollThread(
					new Thread(account.getPollArea(), Thread.currentThread()
							.getName() + "+PollThread"));
			account.getPollArea().getPollThread().start();
		}
	}
}
