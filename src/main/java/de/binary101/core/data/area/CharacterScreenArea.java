package de.binary101.core.data.area;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.data.account.Account;
import de.binary101.core.response.CharacterResponse;

public class CharacterScreenArea extends BaseArea {
	private final static Logger logger = LogManager.getLogger(CharacterScreenArea.class);
	
	@Getter private CharacterResponse characterResponse;
	
	public CharacterScreenArea(Account account) {
		super(account);
	}
	
	@Override
	public void performArea() {

	}
	
}
