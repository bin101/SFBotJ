package de.binary101.core.data.area;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import de.binary101.core.constants.enums.MountTypeEnum;
import de.binary101.core.data.account.Account;
import de.binary101.core.request.BuyMountRequest;
import de.binary101.core.response.Response;
import de.binary101.core.utils.Helper;

public class MountArea extends BaseArea {
	private final static Logger logger = LogManager.getLogger(MountArea.class);
	
	private static int cowSilverPrice = 100;
	private static int horseSilverPrice = 500;
	private static int tigerSilverPrice = 1000;
	private static int dragonSilverPrice = 0;

	private static int cowMushroomPrice = 0;
	private static int horseMushroomPrice = 0;
	private static int tigerMushroomPrice = 1;
	private static int dragonMushroomPrice = 25;
	
	public MountArea(Account account) {
		super(account);
	}
	
	@Override 
	public void performArea() {		
		if (!account.getSetting().getPerformMountBuy()
				|| (account.getOwnCharacter().getMountEndTime().isAfter(DateTime.now()) && account.getOwnCharacter().getMountType().getId() >= account.getSetting().getMaxMountToBuy().getId())
				|| (account.getSetting().getMaxMountToBuy() == MountTypeEnum.None)
				) {
			return;
		}
		
		if (!account.getHasRunningAction() || account.getHasCompletedMirror()) {
			
			Boolean canBuyMount = false;
			MountTypeEnum targetMount = account.getSetting().getMaxMountToBuy();
			canBuyMount = canBuyMount(targetMount);
			
			while (!canBuyMount && targetMount.getId() > 1) {
				targetMount = MountTypeEnum.fromInt(targetMount.getId() - 1);
				if (canBuyMount(targetMount)) {
					canBuyMount = true;
				}
			}
			
			if (canBuyMount && targetMount.getId() > account.getOwnCharacter().getMountType().getId()) {
				logger.info("Betrete den Stall");
				Helper.threadSleepRandomBetween(1000, 2000);
				
				logger.info("Mein neues Reittier wird dann wohl " + (targetMount == MountTypeEnum.Cow ? "eine Kuh" : targetMount == MountTypeEnum.Horse ? "ein Pferd" : targetMount == MountTypeEnum.Tiger ? "ein Tiger" : "ein Greifendrache"));
				
				String responseString = sendRequest(new BuyMountRequest(targetMount));
				new Response(responseString, account);
				
			}
			
		}
	}
	
	private Boolean canBuyMount (MountTypeEnum mountType) {
		Boolean canBuyMount = false;
		
		long currentSilver = account.getOwnCharacter().getSilver(); 
		int currentMushroom = account.getOwnCharacter().getMushrooms();
		
		switch (mountType) {
			case Cow:
				if (currentSilver >= cowSilverPrice && currentMushroom >= cowMushroomPrice) {
					canBuyMount = true;
				}
				break;
			case Horse:
				if (currentSilver >= horseSilverPrice && currentMushroom >= horseMushroomPrice) {
					canBuyMount = true;
				}
				break;
			case Tiger:
				if (currentSilver >= tigerSilverPrice && currentMushroom >= tigerMushroomPrice) {
					canBuyMount = true;
				}
				break;
			case Dragon:
				if (currentSilver >= dragonSilverPrice && currentMushroom >= dragonMushroomPrice) {
					canBuyMount = true;
				}
				break;
			default:
				canBuyMount = false;
				break;
		}

		return canBuyMount;
	}

}
