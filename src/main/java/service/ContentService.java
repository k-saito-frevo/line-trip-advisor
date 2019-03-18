package service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

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
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();			
			headers.add("Authorization", "Bearer " + "ZWAMZw1b9/qepYdvDh38XrUjVpqL8B4lxrdibQN7lKXc4BY6/svwnG36pHFUvp422mZrjbkMQBVOAS6UFSP4GWirjF83glbh3VzuDjItXKdrgUv9YrDJemoyD6g78aGpi+/QmNOPUhf2l+t16kQtUQdB04t89/1O/w1cDnyilFU=");
			MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body, headers);
			
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, request,String.class);
			//System.out.println(result.getBody());
			return result.getBody();	
		}catch(Exception ex) {
			System.out.println(ex);
			return "error";
		}
	}
	 public void writeOutputStream(byte[] byteArray) throws IOException {
			OutputStream os = new FileOutputStream("./test.jpg");

	        ByteArrayInputStream imageInput = new ByteArrayInputStream(byteArray);
	        BufferedImage buffer = ImageIO.read(imageInput);

	        ImageWriter writer = null;
	        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");
	        if (iter.hasNext()) {
	            writer = iter.next();
	        }

	        ImageWriteParam param = writer.getDefaultWriteParam();
	        if (param.canWriteCompressed()) {
	            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	            param.setCompressionQuality(0.5f);
	        }

	        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
	        writer.setOutput(ios);
	        IIOImage iioImage = new IIOImage(buffer, null, null);
	        writer.write(null, iioImage, param);
	    }
	 }
