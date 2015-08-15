package de.binary101.core.response;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ArrayListMultimap;

import de.binary101.core.constants.enums.EventEnum;
import de.binary101.core.constants.enums.ResponseEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.utils.CryptManager;
import de.binary101.core.utils.SettingsManager;
import de.binary101.core.utils.TimeManager;

public class Response {
	private final static Logger logger = LogManager.getLogger(Response.class);
	
	protected ArrayListMultimap<String, String> parsedData;
	@Getter private Boolean hasError = false;
	@Getter private ResponseEnum errorCode = ResponseEnum.NONE;
	
	
	public Response(String data, Account account) {
		parseResponseString(data, account.getCryptKey());
		
		
		logger.debug(parsedData);
		
		if (parsedData.containsKey("login count")) {
			account.getSetting().setLoginCount(Integer.parseInt(parsedData.get("login count").get(0)));
			SettingsManager.saveSettings();
			logger.info("Habe den LoginCount erhoeht: " + parsedData.get("login count").get(0));
		}
		
		if (parsedData.containsKey("cryptoid")) {
			account.getSetting().setCryptID(parsedData.get("cryptoid").get(0));
			SettingsManager.saveSettings();
			logger.info("Habe eine neue CryptoID erhalten: " + parsedData.get("cryptoid").get(0));
		}
		
		if (parsedData.containsKey("cryptokey")) {
			account.getSetting().setCryptKey(parsedData.get("cryptokey").get(0));
			SettingsManager.saveSettings();
			logger.info("Habe einen neue CryptoKey erhalten: " + parsedData.get("cryptokey").get(0));
		}
		
		if (parsedData.containsKey("timestamp")) {
			account.setServerTime(TimeManager.UTCunixTimestampToLocalDateTime(Integer.parseInt(parsedData.get("timestamp").get(0))));
		}
		
		if (parsedData.containsKey(ResponseEnum.WAGESPERHOUR.toString())) {
			account.setWagesPerHour(Long.parseLong(parsedData.get(ResponseEnum.WAGESPERHOUR.toString()).get(0)));
		}
		
		if (parsedData.containsKey("Error")) {
			this.hasError = true;
			this.errorCode = ResponseEnum.fromString(parsedData.get("Error").get(0));
		}
		
		if (this.hasError) {
			if (this.errorCode == ResponseEnum.ERR_SERVER_DOWN) {
				try {
					logger.error("Server scheint down zu sein, warte 10 Minuten");
					Thread.sleep(60 * 10 * 1000);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			
			if (this.errorCode == ResponseEnum.ERR_SESSION_ID_EXPIRED){
				logger.error("Session ist nicht mehr gueltig");
				
				account.getSetting().setSessionID(null);
				SettingsManager.saveSettings();
				
				account.logout();
				return;
			}
			
			if (this.errorCode == ResponseEnum.ERR_CRYPTID_NOT_FOUND) {
				logger.error("CryptID stimmt nicht mehr");
				
				account.getSetting().setCryptID(null);
				account.getSetting().setCryptKey(null);
				SettingsManager.saveSettings();
				
				account.logout();
				return;
			}
		}
		
		if (parsedData.containsKey("ownplayersave")) {
			String[] stringArray = parsedData.get("ownplayersave").get(0).split("/");
			Long[] longArray = new Long[stringArray.length];
			
			for (int i = 0; i < stringArray.length; i++) {
				longArray[i] = Long.parseLong(stringArray[i]);
			}
			
			account.getOwnCharacter().updateOwnCharacter(account, longArray);
			account.getTavern().updateTavern(account, longArray);
		}
		
		if (parsedData.containsKey("tavernspecial")) {
			account.getTavern().setSpecialEvent(EventEnum.fromInt(Integer.parseInt(parsedData.get("tavernspecial").get(0))));
		}
	}
	
	private void parseResponseString(String data, String key) {
		data = CryptManager.decodeResponseString(data, key);
		
		this.parsedData = ArrayListMultimap.create();
		
		for (String dataString : data.split("&")) {
			
			if (dataString.contains(":")) {
				String mapKey = dataString.split(":")[0].trim();
				String mapValue = (dataString.split(":").length == 1 ? "" : dataString.split(":")[1].trim());
				
				if (mapKey.contains(".")) {
					mapKey = mapKey.split("\\.")[0];
				}//else do nothing
				
				if (mapKey.contains("(")) {
					mapKey = mapKey.split("(")[0];
				}//else do nothing
				
				this.parsedData.put(mapKey, mapValue);
			}
		}
	}
}
