package de.binary101.core.constants.enums;

public enum ActionEnum {
	None(0), 
	Townwatch(1), 
	Quest(2);
	
	private final int value;

	private ActionEnum(int value) {
		this.value = value;
	}

	public int toInt() {
		return this.value;
	}
	
	public static ActionEnum fromInt(int value) {
		ActionEnum result = null;
		
		for (ActionEnum actionType : ActionEnum.values()) {
			if (value == (actionType.value)) {
		          result = actionType;
		        }
		}
		
		return result;
	}
}
