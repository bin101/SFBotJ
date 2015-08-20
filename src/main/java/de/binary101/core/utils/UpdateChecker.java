package de.binary101.core.utils;

import java.io.InputStream;
import java.net.URL;

public class UpdateChecker {

	public Boolean checkCurrentVersionIsUptoDate() {
		Boolean result = true;

		String urlString = "https://dl.dropboxusercontent.com/u/75698529/SFBotJ/dontCopy/latestVersionInfo";
		try {
			String currentlyExecutedVersion = getClass().getPackage()
					.getImplementationVersion();
			String remoteVersion = getLatestVersion(urlString);

			result = (currentlyExecutedVersion == null || currentlyExecutedVersion
					.equals(remoteVersion));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public String getLatestVersion(String url) throws Exception {
		String data = getData(url);
		return data.substring(data.indexOf("[version]") + 9,
				data.indexOf("[/version]"));
	}

	private String getData(String address) throws Exception {
		URL url = new URL(address);

		InputStream html = null;

		html = url.openStream();

		int c = 0;
		StringBuffer buffer = new StringBuffer("");

		while (c != -1) {
			c = html.read();

			buffer.append((char) c);
		}
		return buffer.toString();
	}

}
