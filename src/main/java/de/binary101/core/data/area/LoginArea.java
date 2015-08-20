package de.binary101.core.data.area;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.data.account.Account;
import de.binary101.core.request.LoginRequest;
import de.binary101.core.response.LoginResponse;

public class LoginArea extends BaseArea {

	private final static Logger logger = LogManager.getLogger(LoginArea.class);

	@Getter private LoginResponse loginResponse;

	public LoginArea(Account account) {
		super(account);
	}

	@Override
	public void performArea() {
		if (!account.getIsLoggedIn()) {
			account.logout(); // falls doch noch Altdaten vorhanden sind

			do {
				logger.info("Starte Loginvorgang");
				String loginResponseString = sendRequest(new LoginRequest(account.getSetting().getUsername(), account
						.getSetting().getPassword(), account.getSetting().getLoginCount()));

				this.loginResponse = new LoginResponse(loginResponseString, account);
			} while (!account.getIsLoggedIn());

			logger.info("Login war erfolgreich");
		}
	}
}
