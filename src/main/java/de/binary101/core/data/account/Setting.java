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
	
	@XStreamAlias("preferSpecialQuests")
	@Getter
	@Setter
	private Boolean preferSpecialQuests;

	@XStreamAlias("maxBeerPerDay")
	@Getter
	@Setter
	private int maxBeerToBuy;

	@XStreamAlias("performQuests")
	@Getter
	@Setter
	private Boolean performQuesten;
	
	@XStreamAlias("performShop")
	@Getter
	@Setter
	private Boolean performShop;
	
	@XStreamAlias("performAttributeBuy")
	@Getter
	@Setter
	private Boolean performAttributeBuy;
	
	@XStreamAlias("strengthPercentage")
	@Getter
	@Setter
	private int strengthPercentage;
	
	@XStreamAlias("dexterityPercentage")
	@Getter
	@Setter
	private int dexterityPercentage;
	
	@XStreamAlias("intelligencePercentage")
	@Getter
	@Setter
	private int intelligencePercentage;
	
	@XStreamAlias("staminaPercentage")
	@Getter
	@Setter
	private int staminaPercentage;
	
	@XStreamAlias("luckPercentage")
	@Getter
	@Setter
	private int luckPercentage;
	
	@XStreamAlias("performTownwatch")
	@Getter
	@Setter
	private Boolean performTownwatch;
	
	@XStreamAlias("minHourOfDayFor10HourTownwatch")
	@Getter
	@Setter
	private int minHourOfDayFor10HourTownwatch;
	
	@XStreamAlias("maxHourOfDayFor10HourTownwatch")
	@Getter
	@Setter
	private int maxHourOfDayFor10HourTownwatch;
	

	public Setting() {

	}
	
	public Setting(String username, String password, String serverURL, String questMode, Boolean preferSpecialQuests,
				   int maxBeerPerDay, Boolean performQuesten, Boolean performShop, Boolean performAttributeBuy,
				   int strengthPercentage, int dexterityPercentage, int intelligencePercentage, int staminaPercentage,
				   int luckPercentage,
				   Boolean performTownwatch, int minHourOfDayFor10HourTownwatch, int maxHourOfDayFor10HourTownwatch) {
		this.username = username;
		this.password = password;
		this.serverURL = serverURL;
		this.questMode = questMode;
		this.preferSpecialQuests = preferSpecialQuests;
		this.maxBeerToBuy = maxBeerPerDay;
		this.performQuesten = performQuesten;
		this.performShop = performShop;
		this.performAttributeBuy = performAttributeBuy;
		this.strengthPercentage = strengthPercentage;
		this.dexterityPercentage = dexterityPercentage;
		this.intelligencePercentage = intelligencePercentage;
		this.staminaPercentage = staminaPercentage;
		this.luckPercentage =  luckPercentage;
		this.performTownwatch = performTownwatch;
		this.minHourOfDayFor10HourTownwatch = minHourOfDayFor10HourTownwatch;
		this.maxHourOfDayFor10HourTownwatch = maxHourOfDayFor10HourTownwatch;
	}
}
