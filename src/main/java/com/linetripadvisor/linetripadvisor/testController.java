package com.linetripadvisor.linetripadvisor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;

import constants.Constants;
import model.Face;
import model.FaceDetail;
import service.FaceRecognizeService;

@RestController
@CrossOrigin
public class testController {
	
	@GetMapping("test")
	public void test() throws IOException{
		try {			
		final LineMessagingClient client = LineMessagingClient.builder("ZWAMZw1b9/qepYdvDh38XrUjVpqL8B4lxrdibQN7lKXc4BY6/svwnG36pHFUvp422mZrjbkMQBVOAS6UFSP4GWirjF83glbh3VzuDjItXKdrgUv9YrDJemoyD6g78aGpi+/QmNOPUhf2l+t16kQtUQdB04t89/1O/w1cDnyilFU=").build();
		final MessageContentResponse messageContentResponse;
		messageContentResponse = client.getMessageContent("9581247313457").get();
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
		System.out.println("ここがパスだよ");
		System.out.println(path.toString());
		String testString =  "data:image/jpg;base64," + Base64.getEncoder().encodeToString((Files.readAllBytes(path)));
		FaceRecognizeService faceRecognizeService = new FaceRecognizeService();
    	String res = faceRecognizeService.tryPost(testString);
		Files.delete(path);
		System.out.println(res);
//			FileSystem testFs = FileSystems.getDefault();
//			Path pathJson = testFs.getPath("./test.json");
//			List<String> result = Files.readAllLines(pathJson);
			ObjectMapper mapper = new ObjectMapper();
//			try {
//				JsonNode node = mapper.readTree(result.get(0));
//				System.out.println("ここまできてる");
//				System.out.println(node);
//				System.out.println(node.get("image_id"));
//				System.out.println(node.get("request_id"));
//				System.out.println(node.get("time_used"));
//				System.out.println("ここまできてる");
//			} catch (IOException e) {
//				System.out.println("Welcome　to Error");
//				e.printStackTrace();
//			}
			
			Face face = mapper.readValue(res, Face.class);
			List<Float> rankingArr = new ArrayList<Float>();
			Map<Float,String> emotionMap = new HashMap<>();
			
			if(face.faces.size()>0) {
				face.faces.forEach(fc -> {
					float nature = 0,treasure=0,historical=0,resort=0,city=0,age = 0;
					FaceDetail fd = fc;
					//性別
					if(fd.getAttributes().getGender().getValue().toString().equals(Constants.MALE)) {
						nature += Constants.FEMALE_NATURE;
						treasure += Constants.FEMALE_TREASURE;
						historical += Constants.FEMALE_HISTORICAL;
						resort += Constants.FEMALE_RESORT;
						city += Constants.FEMALE_CITY;						
					}else {
						nature += Constants.MALE_NATURE;
						treasure += Constants.MALE_TREASURE;
						historical += Constants.MALE_HISTORICAL;
						resort += Constants.MALE_RESORT;
						city += Constants.MALE_CITY;						
					}
					//年齢
					if( Integer.parseInt(fd.getAttributes().getAge().getValue().toString())>55) {
							nature += Constants.OLDEST_NATURE;
							treasure += Constants.OLDEST_TREASURE;
							historical += Constants.OLDEST_HISTORICAL;
							resort += Constants.OLDEST_RESORT;
							city += Constants.OLDEST_CITY;
					}else if( Integer.parseInt(fd.getAttributes().getAge().getValue().toString())>32) {
						nature += Constants.OLDER_NATURE;
						treasure += Constants.OLDER_TREASURE;
						historical += Constants.OLDER_HISTORICAL;
						resort += Constants.OLDER_RESORT;
						city += Constants.OLDER_CITY;
					}else if( Integer.parseInt(fd.getAttributes().getAge().getValue().toString())>22) {
						nature += Constants.YOUNGER_NATURE;
						treasure += Constants.YOUNGER_TREASURE;
						historical += Constants.YOUNGER_HISTORICAL;
						resort += Constants.YOUNGER_RESORT;
						city += Constants.YOUNGER_CITY;
					}else{
						nature += Constants.YOUNGEST_NATURE;
						treasure += Constants.YOUNGEST_TREASURE;
						historical += Constants.YOUNGEST_HISTORICAL;
						resort += Constants.YOUNGEST_RESORT;
						city += Constants.YOUNGEST_CITY;
					}
					rankingArr.add(fd.getAttributes().getEmotion().getSadness());
					emotionMap.put(fd.getAttributes().getEmotion().getSadness(),"saddness");
					rankingArr.add(fd.getAttributes().getEmotion().getNeutral());
					emotionMap.put(fd.getAttributes().getEmotion().getNeutral(),"neutral");
					rankingArr.add(fd.getAttributes().getEmotion().getHappiness());
					emotionMap.put(fd.getAttributes().getEmotion().getHappiness(),"happiness");
					rankingArr.add(fd.getAttributes().getEmotion().getAnger());
					emotionMap.put(fd.getAttributes().getEmotion().getAnger(),"anger");
					rankingArr.add(fd.getAttributes().getEmotion().getDisgust());
					emotionMap.put(fd.getAttributes().getEmotion().getDisgust(),"disgust");
					rankingArr.add(fd.getAttributes().getEmotion().getFear());
					emotionMap.put(fd.getAttributes().getEmotion().getFear(),"fear");
					rankingArr.add(fd.getAttributes().getEmotion().getSurprise());
					emotionMap.put(fd.getAttributes().getEmotion().getSurprise(),"surprise");
					Collections.sort(rankingArr);
					//感情
					switch(emotionMap.get(rankingArr.get(0))) {
						case "sadness":
							nature += Constants.SADNESS_NATURE;
							treasure += Constants.SADNESS_TREASURE;
							historical += Constants.SADNESS_HISTORICAL;
							resort += Constants.SADNESS_RESORT;
							city += Constants.SADNESS_CITY;
							break;
						case "neutral":
							nature += Constants.NEUTRAL_NATURE;
							treasure += Constants.NEUTRAL_TREASURE;
							historical += Constants.NEUTRAL_HISTORICAL;
							resort += Constants.NEUTRAL_RESORT;
							city += Constants.NEUTRAL_CITY;
							break;
						case "happiness":
							nature += Constants.HAPPINESS_NATURE;
							treasure += Constants.HAPPINESS_TREASURE;
							historical += Constants.HAPPINESS_HISTORICAL;
							resort += Constants.HAPPINESS_RESORT;
							city += Constants.HAPPINESS_CITY;
							break;
						case "anger":
							nature += Constants.ANGER_NATURE;
							treasure += Constants.ANGER_TREASURE;
							historical += Constants.ANGER_HISTORICAL;
							resort += Constants.ANGER_RESORT;
							city += Constants.ANGER_CITY;
							break;
						case "disgust":
							nature += Constants.DISGUST_NATURE;
							treasure += Constants.DISGUST_TREASURE;
							historical += Constants.DISGUST_HISTORICAL;
							resort += Constants.DISGUST_RESORT;
							city += Constants.DISGUST_CITY;
							break;
						case "fear":
							nature += Constants.FEAR_NATURE;
							treasure += Constants.FEAR_TREASURE;
							historical += Constants.FEAR_HISTORICAL;
							resort += Constants.FEAR_RESORT;
							city += Constants.FEAR_CITY;
							break;
						case "surprise":
							nature += Constants.SURPRISING_NATURE;
							treasure += Constants.SURPRISING_TREASURE;
							historical += Constants.SURPRISING_HISTORICAL;
							resort += Constants.SURPRISING_RESORT;
							city += Constants.SURPRISING_CITY;		
					}
					String targetName = "nature";
					float targetNum = nature;
					
					List<String> targetArr = new ArrayList<String>();
					targetArr.add(targetName);
					if(treasure>targetNum) {
						targetName = "treasure";
						targetNum = treasure;
						targetArr.clear();
						targetArr.add(targetName);
					}else if(treasure == targetNum) {
						targetArr.add("treasure");
					}
					if(historical>targetNum) {
						targetName = "historical";
						targetNum = historical;
						targetArr.clear();
						targetArr.add(targetName);
					}else if(treasure == targetNum) {
						targetArr.add("historical");
					}
					if(resort>targetNum) {
						targetName = "resort";
						targetNum = resort;
						targetArr.clear();
						targetArr.add(targetName);
					}else if(resort == targetNum) {
						targetArr.add("resort");
					}
					if(city>targetNum) {
						targetName = "city";
						targetNum = city;
						targetArr.clear();
						targetArr.add(targetName);
					}else if(city == targetNum) {
						targetArr.add("city");
					}
					//同数のものがある場合
					FaceRecognizeService frs = new FaceRecognizeService();
					System.out.println(frs.generateLocation(targetArr));
				});
			}
		}catch(Exception ex) {
			System.out.print("エラー");
			System.out.print(ex);
		}
	}
	public String generateTripSuggestion() {
		return "";
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
