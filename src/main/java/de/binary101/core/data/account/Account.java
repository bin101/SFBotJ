package de.binary101.core.data.account;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import de.binary101.core.constants.StaticValues;
import de.binary101.core.constants.enums.ActionEnum;
import de.binary101.core.data.area.PollArea;
import de.binary101.core.data.character.OwnCharacter;
import de.binary101.core.data.guild.Guild;
import de.binary101.core.data.tavern.Quest;
import de.binary101.core.data.tavern.Tavern;

public class Account {
	
	@Getter @Setter private PollArea pollArea;
	@Getter private Setting setting;
	@Getter @Setter private DateTime lastActionTime;
	@Getter @Setter private DateTime serverTime;
	@Getter @Setter private Long requestCount;
	@Getter @Setter private Boolean isLoggedIn;
	public String getCryptID() {
		String result = null;
		
		result = this.setting.getCryptID() == null ? StaticValues.CryptID() : this.setting.getCryptID();
		
		return result;
	}
	
	public String getCryptKey() {
		String result = null;
		
		result = this.setting.getCryptKey() == null ? StaticValues.CryptKey() : this.setting.getCryptKey();
		
		return result;
	}
	
	public String getSessionID() {
		String result = null;
		
		result = this.setting.getSessionID() == null ? StaticValues.SessionID() : this.setting.getSessionID();
		
		return result;
	}
	
	@Getter @Setter private OwnCharacter ownCharacter;
	@Getter @Setter private Tavern tavern;
	@Getter @Setter private Guild guild;
	public Boolean getHasGuild() {
		return this.guild.getName().equals("None") ? false : true;
	}
	
	@Getter @Setter private Boolean hasRunningAction;
	@Getter @Setter private ActionEnum actionType;
	@Getter @Setter private int actionLength;
	@Getter @Setter private DateTime actionEndTime;
	
	@Getter @Setter private Boolean gotNewItem;
	
	@Getter @Setter private long wagesPerHour;
	
	public Boolean getHasEnoughALUForOneQuest () {
		Boolean result = false;
		
		int remainingALUSeconds = this.getTavern().getRemainingALUSeconds();
		Optional<Quest> possibleQuest = this.getTavern().getAvailableQuests().stream().filter(quest -> quest.getDuration() <= remainingALUSeconds).findFirst();
		
		result = possibleQuest.isPresent();
		
		return result;
	}
	
	@Getter @Setter private Boolean hasCompletedMirror;
	
	public Account(Setting setting){
		this.pollArea = new PollArea(this);
		
		this.setting = setting;
		this.lastActionTime = new DateTime();
		this.requestCount = 1l;
		this.isLoggedIn = false;
		
		//CharacterData
		
		this.ownCharacter = new OwnCharacter();
		this.tavern = new Tavern();
		this.guild = new Guild();
		
		this.hasRunningAction = false;
		this.actionType = ActionEnum.None;
		this.actionLength = 0;
		this.actionEndTime = new DateTime();
		
		this.gotNewItem = false;
		
		this.wagesPerHour = 0;
		
		this.hasCompletedMirror = false;
	}
	
	public void logout(){
		this.isLoggedIn = false;
	}
	
	@Override
	public String toString() {
		return (this.setting.getUsername() + "@" + this.setting.getServerURL().substring(7));
	}
}
