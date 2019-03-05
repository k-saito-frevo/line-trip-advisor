package service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpEntity;	
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
	@CrossOrigin
	public class FaceRecognizeService {

		public String tryPost(@RequestBody(required=false) String imgSrc) {
			try {
				//final String uri = "https://api-us.faceplusplus.com/facepp/v3/detect?api_key=jpY1EEucLMZmcfmNKHNbHoiLoGpOEAJ7&api_secret=_cCinbAjgxewP8fQRyTARbFh66t53Y6h&image_base64=" + imgSrc +"&return_landmark=1";
				final String uri = "https://api-us.faceplusplus.com/facepp/v3/detect";
				RestTemplate restTemplate = new RestTemplate();
				System.out.println("テスト");
				System.out.print(imgSrc);
				//String param64 = imgSrc.replace("data%3Aimage%2Fpng%3Bbase64%2C","data:image/png;base64,");
				//param64 = param64.replaceAll("%2F", "/");
				//System.out.println(param64);
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

				MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
				body.add("api_key", "jpY1EEucLMZmcfmNKHNbHoiLoGpOEAJ7");
				body.add("api_secret", "_cCinbAjgxewP8fQRyTARbFh66t53Y6h");
				body.add("image_base64", imgSrc);
				body.add("return_attributes", "gender,age,smiling,emotion,headpose,facequality,eyestatus,mouthstatus,skinstatus,blur,ethnicity,beauty,eyegaze");
				
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body, headers);
				
//				HttpHeaders headers = new HttpHeaders();
//				headers.setContentType(MediaType.APPLICATION_JSON);
//				String body = "{ "
//						+ "api_key:jpY1EEucLMZmcfmNKHNbHoiLoGpOEAJ7,"
//						+ "api_secret:_cCinbAjgxewP8fQRyTARbFh66t53Y6h,"
//						+ "image_base64:" + imgSrc +","
//						+ "return_landmark:1"
//				+ "}";
//				HttpEntity<String> entity = new HttpEntity<String>(body, headers);			
				String result = restTemplate.postForObject(uri, request,String.class);
				System.out.println(result);
				return result;
			}catch(Exception ex) {
				System.out.println(ex);
				return "false";
			}
		}
	}