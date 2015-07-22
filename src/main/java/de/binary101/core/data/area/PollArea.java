package de.binary101.core.data.area;

import lombok.Getter;
import de.binary101.core.data.account.Account;
import de.binary101.core.request.PollRequest;
import de.binary101.core.response.PollResponse;

public class PollArea extends BaseArea {
	
	@Getter private PollResponse response;

	public PollArea(Account account) {
		super(account);
	}
	
	@Override
	public void performArea() {
		if (account.getIsLoggedIn()) {
			String pollResponseString = sendRequest(new PollRequest());
			
			response = new PollResponse(pollResponseString, account);
		}
	}
}
