package de.binary101.core.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.io.xml.DomDriver;

import de.binary101.core.data.account.Setting;
import de.binary101.core.data.account.SettingList;

public class SettingsManager {

	private static final String FILENAME = "accounts.xml";
	private static SettingList settingsContainer = new SettingList();

	public static ArrayList<Setting> getSettings() {
		return SettingsManager.settingsContainer.getSettings();
	}

	private SettingsManager() {
	}

	public static void addSetting(Setting setting) {
		settingsContainer.getSettings().add(setting);
	}

	private static XStream openXStream() {

		XStream xstream = new XStream(new PureJavaReflectionProvider(),
				new DomDriver());
		xstream.processAnnotations(SettingList.class);
		xstream.autodetectAnnotations(true);

		return xstream;
	}

	public static void loadSettings() {
		settingsContainer.getSettings().clear();

		XStream xstream = openXStream();
		File configFile = new File(FILENAME);

		if (configFile.exists()) {
			try (FileReader fr = new FileReader(configFile)) {
				settingsContainer = (SettingList) xstream.fromXML(fr);
				SettingsManager.saveSettings();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Es existiert keine Config Datei");
		}
	}

	public synchronized static void saveSettings() {
		File configFile = new File(FILENAME);
		XStream xstream = openXStream();

		try (FileWriter fw = new FileWriter(configFile)) {
			synchronized (settingsContainer) {
				xstream.toXML(settingsContainer, fw);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
