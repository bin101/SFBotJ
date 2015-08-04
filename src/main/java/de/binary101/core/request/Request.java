package de.binary101.core.request;

import lombok.Getter;
import lombok.Setter;
import de.binary101.core.constants.enums.RequestEnum;

public class Request {
	@Getter private RequestEnum type;
	@Getter @Setter private Long requestCount;

	public Request(RequestEnum type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type.toString() + ':';
	}
}
