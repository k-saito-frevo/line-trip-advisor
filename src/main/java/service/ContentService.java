package service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class ContentService {
	
	public String getContent(String messageId) {
		try {
			final String uri = " https://api.line.me/v2/bot/message/" + messageId + "/content";
			System.out.println("ここに投げるよ");
			System.out.println(uri);

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body, headers);
			
			String result = restTemplate.getForObject(uri,String.class);
			System.out.println(result);
			return result;	
		}catch(Exception ex) {
			System.out.println(ex);
			return "error";
		}
	}
}
