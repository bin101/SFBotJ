package de.binary101.app;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import de.binary101.core.SFBotJCore;
import de.binary101.core.data.account.Account;
import de.binary101.core.data.area.CharacterScreenArea;
import de.binary101.core.data.area.LoginArea;
import de.binary101.core.data.area.ShopArea;
import de.binary101.core.data.area.TavernArea;
import de.binary101.core.data.area.TownwatchArea;
import de.binary101.core.utils.Helper;
import de.binary101.core.utils.SettingsManager;
import de.binary101.core.utils.UpdateChecker;

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
				core.getAreas().add(new CharacterScreenArea(account));
				core.getAreas().add(new ShopArea(account));
//				core.getAreas().add(new MountArea(account));
				core.getAreas().add(new TavernArea(account));
//				core.getAreas().add(new ToiletArea(account));
//				core.getAreas().add(new ArenaArea(account));
//				core.getAreas().add(new GuildArea(account));
//				core.getAreas().add(new DungeonArea(account));
				core.getAreas().add(new TownwatchArea(account));
				
				//Erzeuge den Thread
				Thread thread = new Thread(botThreads, core, (core.getAccount().toString()));
				thread.start();
			}
			
			UpdateChecker updateChecker = new UpdateChecker();
			
			Boolean isUpToDate = true;
			while (isUpToDate) {
				isUpToDate = updateChecker.checkCurrentVersionIsUptoDate();
				
				if (isUpToDate) {
					try {
						Thread.sleep(1000 * 60 * 10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("******************************");
					System.out.println("******************************");
					System.out.println("********Starte Update*********");
					System.out.println("******************************");
					System.out.println("******************************");
					System.exit(0);
				}
			}
			
		}//else do nothing
	}
}
