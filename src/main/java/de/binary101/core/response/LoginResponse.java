package de.binary101.core.response;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.enums.ResponseEnum;
import de.binary101.core.data.account.Account;

public class LoginResponse extends Response {
	
	@Getter private String sessionID;
	@Getter private int serverVersion;
	
	private final static Logger logger = LogManager.getLogger(LoginResponse.class);
	
	public LoginResponse(String rawData, Account account) {
		super(rawData, account);
		
		if (parsedData.containsKey("sessionid")) {
			this.sessionID = parsedData.get("sessionid").get(0);
			logger.info("Aktualisiere die Session-ID: )" + this.sessionID);
			account.setSessionID(sessionID);
		}
		
		if (parsedData.containsKey("serverversion")) {
			this.serverVersion = Integer.parseInt(parsedData.get("serverversion").get(0));
		}
		
		if (this.getHasError()) {
			if (this.getErrorCode() == ResponseEnum.ERR_LOGIN_COUNT_TOO_LOW 
					|| this.getErrorCode() == ResponseEnum.ERR_LOGIN_FAILED) {
				logger.warn("Login war nicht erfolgreich");
				account.setIsLoggedIn(false);
			}
		} else {
			account.setIsLoggedIn(true);
			account.setPollThread(new Thread(account, Thread.currentThread().getName() +"+PollThread"));
			account.getPollThread().start();
		}
	}
}
