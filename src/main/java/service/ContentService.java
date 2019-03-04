package service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class ContentService {
	
	public String getContent(String messageId) {
		try {
			final String uri = "https://api.line.me/v2/bot/message/" + messageId + "/content";
			System.out.println("ここに投げるよ");
			System.out.println(uri);

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			//headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			
			headers.add("Authorization", "Bearer " + "ZWAMZw1b9/qepYdvDh38XrUjVpqL8B4lxrdibQN7lKXc4BY6/svwnG36pHFUvp422mZrjbkMQBVOAS6UFSP4GWirjF83glbh3VzuDjItXKdrgUv9YrDJemoyD6g78aGpi+/QmNOPUhf2l+t16kQtUQdB04t89/1O/w1cDnyilFU=");
			MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body, headers);
			
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, request,String.class);
			System.out.println(result);
			return "test";	
		}catch(Exception ex) {
			System.out.println(ex);
			return "error";
		}
	}
}
