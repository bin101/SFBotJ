package de.binary101.core.data.account;

import java.util.ArrayList;

import lombok.Getter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("settings")
public class SettingList{

	@Getter
	@XStreamImplicit
	private ArrayList<Setting> settings = new ArrayList<Setting>();

}
