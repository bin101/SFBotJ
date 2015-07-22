package de.binary101.core.response;

import de.binary101.core.data.account.Account;

public class CharacterResponse extends Response {

	public CharacterResponse(String data, Account account, Boolean ownAccount) {
		super(data, account);
	}

}
