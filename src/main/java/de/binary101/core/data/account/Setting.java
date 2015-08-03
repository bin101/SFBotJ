package de.binary101.core.data.account;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import lombok.Getter;
import lombok.Setter;

@XStreamAlias("setting")
public class Setting {
	
	@XStreamAlias("username")
	@XStreamAsAttribute
	@Getter
	@Setter
	private String username;

	@XStreamAlias("password")
	@Getter
	@Setter
	private String password;

	@XStreamAlias("serverURL")
	@XStreamAsAttribute
	@Getter
	@Setter
	private String serverURL;

	@XStreamAlias("setQuestModeToExpOrGold")
	@Getter
	@Setter
	private String questMode;

	@XStreamAlias("maxBeerPerDay")
	@Getter
	@Setter
	private int maxBeerToBuy;

	@XStreamAlias("doQuests")
	@Getter
	@Setter
	private Boolean performQuesten;
	
	@XStreamAlias("doShop")
	@Getter
	@Setter
	private Boolean performShop;

	public Setting() {

	}
	
	public Setting(String username, String password, String serverURL, String questMode, int maxBeerPerDay, Boolean performQuesten, Boolean performShop) {
		this.username = username;
		this.password = password;
		this.serverURL = serverURL;
		this.questMode = questMode;
		this.maxBeerToBuy = maxBeerPerDay;
		this.performQuesten = performQuesten;
		this.performShop = performShop;
	}
}
