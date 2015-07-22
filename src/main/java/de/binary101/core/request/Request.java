package de.binary101.core.request;

import de.binary101.core.constants.enums.*;
import lombok.*;

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
