package de.binary101.core;

import java.io.PrintStream;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import de.binary101.core.data.account.Account;
import de.binary101.core.data.area.BaseArea;
import de.binary101.core.utils.LoggingOutputStream;

public class SFBotJCore implements Runnable {

	private final static Logger logger = LogManager.getLogger(SFBotJCore.class);

	private @Getter Account account;
	private @Getter ArrayList<BaseArea> areas;
	private @Getter @Setter Boolean hasSomethingToDo;

	public SFBotJCore(Account account) {
		this.account = account;
		this.areas = new ArrayList<BaseArea>();
		this.hasSomethingToDo = true;
	}

	@Override
	public void run() {

		ThreadContext.put("logFileName", this.account.toString());

		System.setErr(new PrintStream(new LoggingOutputStream(logger,
				Level.ERROR), true));

		while (this.hasSomethingToDo) {
			areas.forEach(area -> area.performArea());
		}

		this.account.logout();
	}

}
