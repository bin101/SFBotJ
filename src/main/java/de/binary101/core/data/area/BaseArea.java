package de.binary101.core.data.area;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.data.account.Account;
import de.binary101.core.request.Request;
import de.binary101.core.utils.CryptManager;
import de.binary101.core.utils.Helper;

public class BaseArea {
	private final static Logger logger = LogManager.getLogger(BaseArea.class);
	
	protected Account account;	
	
	public BaseArea(Account account) {
		this.account = account;
	}
	
	public void performArea() {
		if (account.getIsLoggedIn()) {
			return;
		}
	}
	
	private CloseableHttpClient createHttpClient() {
		
		List<Header> header = new ArrayList<Header>();
		
		header.add(new BasicHeader("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.125 Safari/537.36"));
		header.add(new BasicHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
		header.add(new BasicHeader("accept-encoding", "gzip, deflate, sdch"));
		header.add(new BasicHeader("accept-language", "de,en-US;q=0.8,en;q=0.6,de-DE;q=0.4"));
		header.add(new BasicHeader("connection", "keep-alive"));
		header.add(new BasicHeader("dnt", "1"));
		header.add(new BasicHeader("x-requested-with", "ShockwaveFlash/18.0.0.160"));
		header.add(new BasicHeader("host", account.getSetting().getServerURL().substring(7)));
		header.add(new BasicHeader("referer", account.getSetting().getServerURL() + '/'));
		
		return HttpClientBuilder.create().setDefaultHeaders(header).build();
	}
	
	protected String sendRequest(Request request) {
		String result = null;
		request.setRequestCount(this.account.getRequestCount());
		
		
		Helper.threadSleep(1000, 2000);
		
		if (checkServerConnection()) {
			HttpGet req = new HttpGet(buildRequestString(request));
			try (CloseableHttpClient refClient = createHttpClient()) {
				HttpResponse response = refClient.execute(req);
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "UTF-8");
				this.account.setRequestCount(this.account.getRequestCount() + 1);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			logger.warn("Server ist nicht erreichbar");
		}
		
		return result;
	}
	
	private String buildRequestString(Request request) {
		StringBuilder requestStringBuilder = new StringBuilder();
		
		requestStringBuilder.append(this.account.getSetting().getServerURL());
		requestStringBuilder.append("/req.php?req=");
		requestStringBuilder.append(this.account.getCryptID());
		
		String sessionCommandAndSeperator = this.account.getSessionID() + '|' + request.toString();
		
		logger.debug("Session + Command in plaintext:" + sessionCommandAndSeperator);
		
		while (sessionCommandAndSeperator.length() % 16 > 0) {
			sessionCommandAndSeperator += '|';
		}
		
		requestStringBuilder.append(CryptManager.encode(sessionCommandAndSeperator, this.account.getCryptKey()));
		requestStringBuilder.append("&rnd=" + Math.random());
		requestStringBuilder.append("&c=" + request.getRequestCount());

		logger.debug("full request string: " + requestStringBuilder.toString());
		
		return requestStringBuilder.toString();
	}
	
	
	private Boolean checkServerConnection() {
		Boolean result = false;
		
		try (CloseableHttpClient refClient = HttpClients.createDefault()) {
			HttpGet request = new HttpGet(this.account.getSetting().getServerURL());
			HttpResponse response = refClient.execute(request);
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = true;
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return result;
	}
}
