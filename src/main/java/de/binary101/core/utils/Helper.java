package de.binary101.core.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Helper {
	private Helper() {}
	
	private final static Logger logger = LogManager.getLogger(Helper.class);
	
	public static void ThreadSleep(int minMilliSec, int maxMilliSec) {
		try {
			Thread.sleep((long)((Math.random() * (maxMilliSec - minMilliSec)) + minMilliSec));
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}
	
	public static int normalizeResponseInt(int value) {
		int result = 0;
		
		result = (int)((double)value - Math.round((double)value / 65536.0f) * 65536.0f);
		
		return result;
	}
}
