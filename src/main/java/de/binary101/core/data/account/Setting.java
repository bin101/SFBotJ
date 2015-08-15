package de.binary101.core.data.account;

import lombok.Getter;
import lombok.Setter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import de.binary101.core.constants.enums.MountTypeEnum;

@XStreamAlias("setting")
public class Setting {
	
	@XStreamAlias("username")
	@XStreamAsAttribute
	@Getter
	@Setter
	private String username = "YourUsername";

	@XStreamAlias("serverURL")
	@XStreamAsAttribute
	@Getter
	@Setter
	private String serverURL = "server.sfgame.tld";
	
	@XStreamAlias("password")
	@Getter
	@Setter
	private String password = "YourPassword";
	
	@XStreamAlias("dontChange_LoginCount")
	@Getter
	@Setter 
	private int loginCount = 0;
	
	@XStreamAlias("dontChange_SessionID")
	@Getter
	@Setter 
	private String sessionID;
	
	@XStreamAlias("dontChange_CryptID")
	@Getter
	@Setter
	private String cryptID;
	
	@XStreamAlias("dontChange_CryptKey")
	@Getter
	@Setter
	private String cryptKey;
	
	@XStreamAlias("performQuests")
	@Getter
	@Setter
	private Boolean performQuesten = false;

	@XStreamAlias("setQuestModeToExpOrGold")
	@Getter
	@Setter
	private String questMode = "exp";
	
	@XStreamAlias("preferSpecialQuests")
	@Getter
	@Setter
	private Boolean preferSpecialQuests = true;

	@XStreamAlias("maxBeerPerDay")
	@Getter
	@Setter
	private int maxBeerToBuy = 0;
	
	@XStreamAlias("savedMushroomsExceptMountBuy")
	@Getter
	@Setter
	private int savedMushrooms = 0;
	
	@XStreamAlias("performShop")
	@Getter
	@Setter
	private Boolean performShop = false;
	
	@XStreamAlias("performItemEquip")
	@Getter
	@Setter
	private Boolean performItemEquip = false;
	
	@XStreamAlias("performAttributeBuy")
	@Getter
	@Setter
	private Boolean performAttributeBuy = false;
	
	@XStreamAlias("strengthPercentage")
	@Getter
	@Setter
	private int strengthPercentage = -1;
	
	@XStreamAlias("dexterityPercentage")
	@Getter
	@Setter
	private int dexterityPercentage = -1;
	
	@XStreamAlias("intelligencePercentage")
	@Getter
	@Setter
	private int intelligencePercentage = -1;
	
	@XStreamAlias("staminaPercentage")
	@Getter
	@Setter
	private int staminaPercentage = -1;
	
	@XStreamAlias("luckPercentage")
	@Getter
	@Setter
	private int luckPercentage = -1;
	
	@XStreamAlias("performTownwatch")
	@Getter
	@Setter
	private Boolean performTownwatch = false;
	
	@XStreamAlias("minHourOfDayFor10HourTownwatch")
	@Getter
	@Setter
	private int minHourOfDayFor10HourTownwatch = 22;
	
	@XStreamAlias("maxHourOfDayFor10HourTownwatch")
	@Getter
	@Setter
	private int maxHourOfDayFor10HourTownwatch = 8;
	
	@XStreamAlias("performMountBuy")
	@Getter
	@Setter
	private Boolean performMountBuy = false;
	
	@XStreamAlias("maxMountToBuy")
	@Getter
	@Setter
	private MountTypeEnum maxMountToBuy = MountTypeEnum.None;
	

	public Setting() {

	}
}
