package de.binary101.core.utils;

public final class SFStringManager {

	private SFStringManager() {
	}

	public static String GetSFDecodingString(String toDecode) {
		return toDecode.replace("$b", System.lineSeparator())
				.replace("$c", ":").replace("$P", "%").replace("$P", "%")
				.replace("$s", "/").replace("$p", "|").replace("$+", "&")
				.replace("$q", "\"").replace("$r", "#").replace("$C", ",")
				.replace("$S", ";").replace("$d", "$");
	}

	public static String GetSFEncodingString(String toEncode) {
		return toEncode.replace(System.lineSeparator(), "$b")
				.replace(":", "$c").replace("%", "$P").replace("/", "$s")
				.replace("|", "$p").replace("&", "$+").replace("\"", "$q")
				.replace("#", "$r").replace(",", "$C").replace(";", "$S")
				.replace("$", "$d");
	}
}
