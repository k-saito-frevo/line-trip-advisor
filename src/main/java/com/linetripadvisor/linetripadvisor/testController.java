package com.linetripadvisor.linetripadvisor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;

import model.Face;
import service.FaceRecognizeService;

@RestController
@CrossOrigin
public class testController {
	
	@GetMapping("test")
	public void test() throws IOException{
		final LineMessagingClient client = LineMessagingClient.builder("ZWAMZw1b9/qepYdvDh38XrUjVpqL8B4lxrdibQN7lKXc4BY6/svwnG36pHFUvp422mZrjbkMQBVOAS6UFSP4GWirjF83glbh3VzuDjItXKdrgUv9YrDJemoyD6g78aGpi+/QmNOPUhf2l+t16kQtUQdB04t89/1O/w1cDnyilFU=").build();
		final MessageContentResponse messageContentResponse;
		try {
		    messageContentResponse = client.getMessageContent("9539237466637").get();
		} catch (InterruptedException | ExecutionException e) {
		    System.out.println(e);
		    return;
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        
		String jpegTarget = "./line-test" + sdf.format(date).toString() + ".jpg";

		FileOutputStream outer = new FileOutputStream(jpegTarget);
		int data;
	     while ((data = messageContentResponse.getStream().read()) != -1) {
	       outer.write((byte) data);
	     }
		outer.flush();
		outer.close();
		FileSystem fs = FileSystems.getDefault();
		Path path = (fs.getPath(jpegTarget));
		String testString =  "data:image/jpg;base64," + Base64.getEncoder().encodeToString((Files.readAllBytes(path)));
    	FaceRecognizeService faceRecognizeService = new FaceRecognizeService();
    	String result = faceRecognizeService.tryPost(testString);
    	System.out.println(result);
		Files.delete(path);
		ObjectMapper mapper = new ObjectMapper();
		Face face = mapper.readValue(result, Face.class);
		if(face.faces.length<1) {
			//return ""
		}
		System.out.println(face);
		}
	
	@GetMapping("just/doit")
	public void doit() throws IOException {
		System.out.println("doitにはきてる");		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();			
		headers.add("Authorization", "Bearer " + "ZWAMZw1b9/qepYdvDh38XrUjVpqL8B4lxrdibQN7lKXc4BY6/svwnG36pHFUvp422mZrjbkMQBVOAS6UFSP4GWirjF83glbh3VzuDjItXKdrgUv9YrDJemoyD6g78aGpi+/QmNOPUhf2l+t16kQtUQdB04t89/1O/w1cDnyilFU=");
		MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body, headers);
		
		ResponseEntity<String> result = restTemplate.exchange("https://api.line.me/v2/bot/message/9458774991876/content", HttpMethod.GET, request,String.class);
		//バイナリーで取得
		byte[] bytes = result.getBody().getBytes();
		FileOutputStream out = new FileOutputStream("./binary.txt");
		for (int i = 0 ; i<bytes.length;i++) {
			out.write(bytes[i]);
		}
		out.flush();
		out.close();
		System.out.println("第一陣きてる");
		final LineMessagingClient client = LineMessagingClient.builder("ZWAMZw1b9/qepYdvDh38XrUjVpqL8B4lxrdibQN7lKXc4BY6/svwnG36pHFUvp422mZrjbkMQBVOAS6UFSP4GWirjF83glbh3VzuDjItXKdrgUv9YrDJemoyD6g78aGpi+/QmNOPUhf2l+t16kQtUQdB04t89/1O/w1cDnyilFU=").build();
		final MessageContentResponse messageContentResponse;
		try {
			System.out.println("第二陣きてる");
		    messageContentResponse = client.getMessageContent("9458774991876").get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("エラーきてる");
		    System.out.println(e);
		    return;
		}
		System.out.println("第３陣きてる");
		System.out.println(messageContentResponse.getStream());
		System.out.println("第二陣きてる");
		FileOutputStream outer = new FileOutputStream("./ttt.jpg");
		outer.flush();
		outer.close();
		Files.copy(messageContentResponse.getStream(),Files.createTempFile("copyTest", ".jpg"));
		//base64で取得
//		String base64String = Base64.encodeBase64(result.getBody().getBytes());
//        String[] strings = base64String.split(",");
//        String extension;
//        switch (strings[0]) {//check image's extension
//            case "data:image/jpeg;base64":
//                extension = "jpeg";
//                break;
//            case "data:image/png;base64":
//                extension = "png";
//                break;
//            default://should write cases for more images types
//                extension = "jpg";
//                break;
//        }
        //convert base64 string to binary data
//        byte[] data = result.getBody().getBytes();
//        File file = new File("./space.jpg");
//        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
//        	System.out.println("ここきてる");
//            outputStream.write(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }				
		//binary 変換テスト
		//BufferedImage originalImage = ImageIO.read(new File("./binary.txt"));

		// convert BufferedImage to byte array
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		ImageIO.write(originalImage, "jpg", baos);
//		baos.flush();
//		baos.close();
//		FileOutputStream outTest = new FileOutputStream("./test.jpg");
//		outTest.flush();
//		outTest.close();
//		byte[] imageInByte = result.getBody().getBytes();
//		InputStream in = new ByteArrayInputStream(imageInByte);
//		BufferedImage bImageFromConvert = ImageIO.read(in);
//		ImageIO.write(bImageFromConvert, "jpg", outTest);
//		FileUtils.writeByteArrayToFile(new File("pathname"), result.getBody().getBytes());
//		try (FileOutputStream fos = new FileOutputStream("./test.jpg")) {
//			   fos.write(result.getBody().getBytes());
//			   fos.close(); 
//			}		
//		FileOutputStream outTest = new FileOutputStream("./sample.jpg");
//		outTest.flush();
//		outTest.close();		
//		BufferedImage bImage = ImageIO.read(new File("./sample.jpg"));
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//	    ImageIO.write(bImage, "jpg", bos );
//	    byte [] data = result.getBody().getBytes();
//	    ByteArrayInputStream bis = new ByteArrayInputStream(data);
//	    BufferedImage bImage2 = ImageIO.read(bis);
//	    ImageIO.write(bImage2, "jpg", new File("output.jpg") );
//	    System.out.println("image created");	
	}
}
