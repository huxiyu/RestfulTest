package sy.RestfulTest;

import java.net.URL;

import org.junit.Test;

public class RestFulTest {

	@Test
	public void greetingWithAuthorized() throws Exception{
		URL url = ClassLoader.getSystemResource("keystore.jks");
		
		// get access_token
		String sslTrustStore = url.getPath();
		String sslTrustStorePassword = "password";
		RestfulAuthorization ra = new RestfulAuthorization(sslTrustStore, sslTrustStorePassword);
		String accessToken = ra.getAccessToken("https://localhost:8443/oauth/token", "roy", "spring");
		
		// greeting
		System.out.println(accessToken);
		ra.greetingWithAuthorized("https://localhost:8443/greeting", accessToken);

	}


}
