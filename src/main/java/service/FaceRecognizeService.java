package service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpEntity;	
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import constants.Constants;
import model.Face;
import model.FaceDetail;

@RestController
	@CrossOrigin
	public class FaceRecognizeService {
		
		//
		public String tryPost(@RequestBody(required=false) String imgSrc) {
			try {
				final String uri = "https://api-us.faceplusplus.com/facepp/v3/detect";
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

				MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
				body.add("api_key", "jpY1EEucLMZmcfmNKHNbHoiLoGpOEAJ7");
				body.add("api_secret", "_cCinbAjgxewP8fQRyTARbFh66t53Y6h");
				body.add("image_base64", imgSrc);
				body.add("return_attributes", "gender,age,smiling,emotion,headpose,facequality,eyestatus,mouthstatus,skinstatus,blur,ethnicity,beauty,eyegaze");
				
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body, headers);
				String result = restTemplate.postForObject(uri, request,String.class);
				return result;
			}catch(Exception ex) {
				System.out.println(ex.toString());
				return ex.toString();
			}
		}
		//顔の分析を元に返却するメッセージを返す
		public String recognizeFace(Face face) {
			try {
				System.out.println("認証結果");
				List<Float> rankingArr = new ArrayList<Float>();
				Map<Float,String> emotionMap = new HashMap<>();
				String res = "";
				for(int i=0;i<face.faces.size();i++) {
					float nature = 0,treasure=0,historical=0,resort=0,city=0,age = 0;
					FaceDetail fd = face.faces.get(i);
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
					FaceRecognizeService frs = new FaceRecognizeService();
					res=frs.generateLocation(targetArr);
				}
				return res;
			}catch(Exception ex){
				return "エラーが発生しました。しばらく経った後再度お送りください。";
			}
		}
		//
		public String generateLocation(List<String> arr) {
			String str = "あなたにオススメの旅行先はこちら!¥n";
			Random r = new Random();
			if(arr.size()>1) {
				for(int i =1;i<6;i++) {
					String targetStr = arr.get(r.nextInt(arr.size()));
					String[] candidateArr = null ;
	 				switch(targetStr) {
						case "nature":
							candidateArr = Constants.NATURE;
							break;
						case "treasure":
							candidateArr = Constants.TREASURE;
							break;
						case "historical":
							candidateArr = Constants.HISTORICAL;
							break;
						case "resort":
							candidateArr = Constants.RESORT;
							break;
						case "city":
							candidateArr = Constants.CITY;
							break;
					}
					str += "【第"+i+"位】¥n";
					str += candidateArr[r.nextInt(candidateArr.length)]+"¥n";
				}
			}else {
				String targetStr = arr.get(0);
				String[] candidateArr = null ;
 				switch(targetStr) {
					case "nature":
						candidateArr = Constants.NATURE;
						break;
					case "treasure":
						candidateArr = Constants.TREASURE;
						break;
					case "historical":
						candidateArr = Constants.HISTORICAL;
						break;
					case "resort":
						candidateArr = Constants.RESORT;
						break;
					case "city":
						candidateArr = Constants.CITY;
						break;
				}
				for(int i =1;i<6;i++) {
					str += "【第"+i+"位】¥n";
					str += candidateArr[r.nextInt(candidateArr.length)]+"¥n"; 										
				}	
			}
			return str;
		}
	}