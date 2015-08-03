package de.binary101.core.response;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ArrayListMultimap;

import de.binary101.core.constants.enums.EventEnum;
import de.binary101.core.constants.enums.ResponseEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.utils.CryptManager;
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
			account.setLoginCount(Integer.parseInt(parsedData.get("login count").get(0)));
		}
		
		if (parsedData.containsKey("cryptoid")) {
			account.setCryptID(parsedData.get("cryptoid").get(0));
			logger.info("Habe eine neue CryptoID erhalten");
		}
		
		if (parsedData.containsKey("cryptokey")) {
			account.setCryptKey(parsedData.get("cryptokey").get(0));
			logger.info("Habe einen neue CryptoKey erhalten");
		}
		
		if (parsedData.containsKey("timestamp")) {
			account.setServerTime(TimeManager.UTCunixTimestampToLocalDateTime(Integer.parseInt(parsedData.get("timestamp").get(0))));
			
		}
		
		if (parsedData.containsKey("Error")) {
			this.hasError = true;
			this.errorCode = ResponseEnum.fromString(parsedData.get("Error").get(0));
		}
		
		if (this.hasError) {
			if (this.errorCode == ResponseEnum.ERR_SERVER_DOWN) {
				try {
					logger.error("Server scheint down zu sein, warte 30 Sekunden");
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					logger.error(e);
					e.printStackTrace();
				}
			}
			
			if (this.errorCode == ResponseEnum.ERR_SESSION_ID_EXPIRED){
				logger.error("Session ist nicht mehr gueltig");
				account.setIsLoggedIn(false);
				return;
			}
		}
		
		if (parsedData.containsKey("ownplayersave")) {
			String[] stringArray = parsedData.get("ownplayersave").get(0).split("/");
			Integer[] intArray = new Integer[stringArray.length];
			
			for (int i = 0; i < stringArray.length; i++) {
				intArray[i] = Integer.parseInt(stringArray[i]);
			}
			
			account.getOwnCharacter().updateOwnCharacter(account, intArray);
			account.getTavern().updateTavern(account, intArray);
		}
		
		if (parsedData.containsKey("tavernspecial")) {
			switch (parsedData.get("tavernspecial").get(0)) {
			case "1":
				account.setTavernSpecialEvent(EventEnum.ExperienceEvent);
				break;
			case "2":
				account.setTavernSpecialEvent(EventEnum.EpicEvent);
				break;
			case "3":
				account.setTavernSpecialEvent(EventEnum.GoldEvent);
				break;
			case "4":
				account.setTavernSpecialEvent(EventEnum.MushroomEvent);
				break;
			case "5":
				account.setTavernSpecialEvent(EventEnum.Beerfest);
				break;
			default:
				account.setTavernSpecialEvent(EventEnum.None);
				break;
			}
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
