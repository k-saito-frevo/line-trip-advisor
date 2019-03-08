package com.linetripadvisor.linetripadvisor;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.text.html.ImageView;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import service.ContentService;

@RestController
@CrossOrigin
public class testController {

	@GetMapping("doit")
	public void doIt() throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();			
		headers.add("Authorization", "Bearer " + "ZWAMZw1b9/qepYdvDh38XrUjVpqL8B4lxrdibQN7lKXc4BY6/svwnG36pHFUvp422mZrjbkMQBVOAS6UFSP4GWirjF83glbh3VzuDjItXKdrgUv9YrDJemoyD6g78aGpi+/QmNOPUhf2l+t16kQtUQdB04t89/1O/w1cDnyilFU=");
		MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body, headers);
		
		ResponseEntity<String> result = restTemplate.exchange("https://api.line.me/v2/bot/message/9458774991876/content", HttpMethod.GET, request,String.class);
		//バイナリーで取得
		byte[] bytes = result.getBody().getBytes();
		FileOutputStream out = new FileOutputStream("./binary.jpg");
		for (int i = 0 ; i<bytes.length;i++) {
			out.write(bytes[i]);
		}
		out.flush();
		out.close();

		//base64で取得
		byte[] encoded = Base64.encodeBase64(result.getBody().getBytes());
		FileOutputStream out2 = new FileOutputStream("./base64.jpg");
		for (int i = 0 ; i<encoded.length;i++) {
			out2.write(encoded[i]);
		}
		out2.flush();
		out2.close();
		
		//FileWriter file = new FileWriter("./reuslt.jpg");
		File file = new File("./result.jpg");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"ISO2022JP")));
		String tetetete = result.getBody();
		pw.println(tetetete);
		pw.close();
				
	}
}
