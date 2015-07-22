package de.binary101.core.data.area;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.data.account.Account;
import de.binary101.core.response.CharacterResponse;

public class CharacterArea extends BaseArea {
	private final static Logger logger = LogManager.getLogger(CharacterArea.class);
	
	@Getter private CharacterResponse characterResponse;
	
	public CharacterArea(Account account) {
		super(account);
	}
	
	@Override
	public void performArea() {
		//TODO hier geht es um fremde Charactere
//		if (account.getIsLoggedIn()) {
//			
//			String characterResponseString = sendRequest(new CharacterRequest(account.getSetting().getUsername()));
//			
//			characterResponse = new CharacterResponse(characterResponseString, account, false);
//		}
	}
	
}
