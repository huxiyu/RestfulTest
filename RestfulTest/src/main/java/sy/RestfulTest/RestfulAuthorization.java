package sy.RestfulTest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class RestfulAuthorization {

	public RestfulAuthorization () {
	}

	public RestfulAuthorization(String sslTrustStore, String sslTrustStorePassword) {
		System.setProperty("javax.net.ssl.trustStore", sslTrustStore);
		System.setProperty("javax.net.ssl.trustStorePassword", sslTrustStorePassword);
	}

	public void greetingWithAuthorized(String url,  String accessToken) throws Exception {
		HttpResponse<JsonNode> response = 
				Unirest.post(url)
				.header("authorization", accessToken)
				.asJson();
		System.out.println(response.getBody());
	}

	public String getAccessToken(String url, String username, String password) throws Exception {
		HttpResponse<JsonNode> response = 
				Unirest.post(url)
				.basicAuth("clientapp", "123456")
				.header("accept", "application/json")
				.header("content-type", "application/x-www-form-urlencoded")
				.body("username="+username+"&password="+password+"&grant_type=password&scope=read%20write")
				.asJson();
		System.out.println(response.getBody());
		return response.getBody().getObject().get("token_type").toString() 
				+" "+ response.getBody().getObject().get("access_token").toString();
	}
}
