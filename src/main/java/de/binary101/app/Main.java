package de.binary101.app;

import java.util.ArrayList;

import de.binary101.core.SFBotJCore;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.area.CharacterScreenArea;
import de.binary101.core.data.area.LoginArea;
import de.binary101.core.data.area.ShopArea;
import de.binary101.core.data.area.TavernArea;
import de.binary101.core.utils.SettingsManager;

public class Main {

	public static void main(String[] args) {
		
		//Ueberpruefe die Aufrufargumente
		if (args.length > 0 ) {
			for (String argument : args) {
				switch (argument) {
				case "-console":
					//Starte App als Console App
					break;
				default:
					//TODO Starte App als GUI App
					break;
				}
			}
		}
		
		SettingsManager.loadSettings();
		
		ArrayList<Account> accounts = new ArrayList<Account>();
		ThreadGroup botThreads = new ThreadGroup("Bots");
		
		if (SettingsManager.getSettings().size() != 0) {
			SettingsManager.getSettings().forEach(setting -> accounts.add(new Account(setting)));
			
			for (Account account : accounts) {
				SFBotJCore core = new SFBotJCore(account);
				
				//Initialisiere die Areas
				core.getAreas().add(new LoginArea(account));
//				core.getAreas().add(new CharacterScreenArea(account));
//				core.getAreas().add(new ShopArea(account));
//				core.getAreas().add(new MountArea(account));
//				core.getAreas().add(new TavernArea(account));
//				core.getAreas().add(new ToiletArea(account));
//				core.getAreas().add(new ArenaArea(account));
//				core.getAreas().add(new GuildArea(account));
//				core.getAreas().add(new DungeonArea(account));
//				core.getAreas().add(new TownwatchArea(account));
				
				//Erzeuge den Thread
				Thread thread = new Thread(botThreads, core, (core.getAccount().toString()));
				thread.start();
			}
		}//else do nothing
	}
}
