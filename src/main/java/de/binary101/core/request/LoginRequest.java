package de.binary101.core.request;

import de.binary101.core.constants.StaticValues;
import de.binary101.core.constants.enums.RequestEnum;
import de.binary101.core.utils.CryptManager;

public class LoginRequest extends Request {
	
	private String username;
	private String password;
	private int loginCount;

	public LoginRequest(String username, String password, int loginCount) {
		super(RequestEnum.Login);
		
		this.username = username;
		this.password = password;
		this.loginCount = loginCount;
	}
	
	@Override
	public String toString() {
		return super.toString() + this.username + '/' + CryptManager.getSHA1HashData(CryptManager.getSHA1HashData(this.password + StaticValues.SHA1HashConstant()) + this.loginCount) + '/' + this.loginCount; 
	}
}
