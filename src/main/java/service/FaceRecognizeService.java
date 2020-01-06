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

	public String tryPost(@RequestBody(required = false) String imgSrc) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
			body.add(Constants.API_KEY_S, System.getenv(Constants.API_KEY_L));
			body.add(Constants.API_SECRET_S, System.getenv(Constants.API_SECRET_L));
			body.add(Constants.IMAGE_BASE_64 ,imgSrc);
			body.add(Constants.RETURN_ATTRIBUTES,
					"gender,age,smiling,emotion,headpose,facequality,eyestatus,mouthstatus,skinstatus,blur,ethnicity,beauty,eyegaze");
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body,headers);
			return restTemplate.postForObject(Constants.DETECT_URL, request, String.class);
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return ex.toString();
		}
	}

	// 顔の分析を元に返却するメッセージを返す
	public String recognizeFace(Face face) {
		try {
			String res = "";
			for (int i = 0; i < face.faces.size(); i++) {
				FaceDetail fd = face.faces.get(i);
				List<String> targetArr = this.scoring(fd);
				FaceRecognizeService frs = new FaceRecognizeService();
				res = frs.generateLocation(targetArr);
			}
			return res;
		} catch (Exception ex) {
			System.out.println("エラー発生！:"+ ex);
			return "エラーが発生しました。しばらく経った後再度お送りください。";
		}
	}

	/*
	 * 返ってきた情報を基に点数をつける
	 */
	public List<String> scoring(FaceDetail fd){
		float nature = 0, treasure = 0, historical = 0, resort = 0, city = 0, age = 0;
		List<Float> rankingArr = new ArrayList<Float>();
		Map<Float, String> emotionMap = new HashMap<>();
		// 性別によって加点
		if (fd.getAttributes().getGender().getValue().toString().equals(Constants.MALE)) {
			nature += Constants.FEMALE_NATURE;
			treasure += Constants.FEMALE_TREASURE;
			historical += Constants.FEMALE_HISTORICAL;
			resort += Constants.FEMALE_RESORT;
			city += Constants.FEMALE_CITY;
		} else {
			nature += Constants.MALE_NATURE;
			treasure += Constants.MALE_TREASURE;
			historical += Constants.MALE_HISTORICAL;
			resort += Constants.MALE_RESORT;
			city += Constants.MALE_CITY;
		}
		// 年齢
		int ageInt = Integer.parseInt(fd.getAttributes().getAge().getValue().toString());
		if ( ageInt > 55) {
			nature += Constants.OLDEST_NATURE;
			treasure += Constants.OLDEST_TREASURE;
			historical += Constants.OLDEST_HISTORICAL;
			resort += Constants.OLDEST_RESORT;
			city += Constants.OLDEST_CITY;
		} else if (ageInt > 32) {
			nature += Constants.OLDER_NATURE;
			treasure += Constants.OLDER_TREASURE;
			historical += Constants.OLDER_HISTORICAL;
			resort += Constants.OLDER_RESORT;
			city += Constants.OLDER_CITY;
		} else if (ageInt > 22) {
			nature += Constants.YOUNGER_NATURE;
			treasure += Constants.YOUNGER_TREASURE;
			historical += Constants.YOUNGER_HISTORICAL;
			resort += Constants.YOUNGER_RESORT;
			city += Constants.YOUNGER_CITY;
		} else {
			nature += Constants.YOUNGEST_NATURE;
			treasure += Constants.YOUNGEST_TREASURE;
			historical += Constants.YOUNGEST_HISTORICAL;
			resort += Constants.YOUNGEST_RESORT;
			city += Constants.YOUNGEST_CITY;
		}
		rankingArr.add(fd.getAttributes().getEmotion().getSadness());
		emotionMap.put(fd.getAttributes().getEmotion().getSadness(), Constants.SADDNESS_S);
		rankingArr.add(fd.getAttributes().getEmotion().getNeutral());
		emotionMap.put(fd.getAttributes().getEmotion().getNeutral(), Constants.NEUTRAL_S);
		rankingArr.add(fd.getAttributes().getEmotion().getHappiness());
		emotionMap.put(fd.getAttributes().getEmotion().getHappiness(), Constants.HAPPINESS_S);
		rankingArr.add(fd.getAttributes().getEmotion().getAnger());
		emotionMap.put(fd.getAttributes().getEmotion().getAnger(), Constants.ANGER_S);
		rankingArr.add(fd.getAttributes().getEmotion().getDisgust());
		emotionMap.put(fd.getAttributes().getEmotion().getDisgust(), Constants.DISGUST_S);
		rankingArr.add(fd.getAttributes().getEmotion().getFear());
		emotionMap.put(fd.getAttributes().getEmotion().getFear(), Constants.FEAR_S);
		rankingArr.add(fd.getAttributes().getEmotion().getSurprise());
		emotionMap.put(fd.getAttributes().getEmotion().getSurprise(),Constants.SURPRISE_S);

		Collections.sort(rankingArr);
		//スコアの最も高い感情を基に加点
		switch (emotionMap.get(rankingArr.get(0))) {
		case Constants.SADDNESS_S:
			nature += Constants.SADNESS_NATURE;
			treasure += Constants.SADNESS_TREASURE;
			historical += Constants.SADNESS_HISTORICAL;
			resort += Constants.SADNESS_RESORT;
			city += Constants.SADNESS_CITY;
			break;
		case Constants.NEUTRAL_S:
			nature += Constants.NEUTRAL_NATURE;
			treasure += Constants.NEUTRAL_TREASURE;
			historical += Constants.NEUTRAL_HISTORICAL;
			resort += Constants.NEUTRAL_RESORT;
			city += Constants.NEUTRAL_CITY;
			break;
		case Constants.HAPPINESS_S:
			nature += Constants.HAPPINESS_NATURE;
			treasure += Constants.HAPPINESS_TREASURE;
			historical += Constants.HAPPINESS_HISTORICAL;
			resort += Constants.HAPPINESS_RESORT;
			city += Constants.HAPPINESS_CITY;
			break;
		case Constants.ANGER_S:
			nature += Constants.ANGER_NATURE;
			treasure += Constants.ANGER_TREASURE;
			historical += Constants.ANGER_HISTORICAL;
			resort += Constants.ANGER_RESORT;
			city += Constants.ANGER_CITY;
			break;
		case Constants.DISGUST_S:
			nature += Constants.DISGUST_NATURE;
			treasure += Constants.DISGUST_TREASURE;
			historical += Constants.DISGUST_HISTORICAL;
			resort += Constants.DISGUST_RESORT;
			city += Constants.DISGUST_CITY;
			break;
		case Constants.FEAR_S:
			nature += Constants.FEAR_NATURE;
			treasure += Constants.FEAR_TREASURE;
			historical += Constants.FEAR_HISTORICAL;
			resort += Constants.FEAR_RESORT;
			city += Constants.FEAR_CITY;
			break;
		case Constants.SURPRISE_S:
			nature += Constants.SURPRISING_NATURE;
			treasure += Constants.SURPRISING_TREASURE;
			historical += Constants.SURPRISING_HISTORICAL;
			resort += Constants.SURPRISING_RESORT;
			city += Constants.SURPRISING_CITY;
		}
		//自然を基点にする
		String targetName = Constants.NATURE_S;
		float targetNum = nature;
		
		List<String> targetArr = new ArrayList<String>();
		targetArr.add(targetName);
		if (treasure > targetNum) {
			targetName = Constants.TREASURE_S;
			targetNum = treasure;
			targetArr.clear();
			targetArr.add(targetName);
		} else if (treasure == targetNum) {
			targetArr.add(Constants.TREASURE_S);
		}
		if (historical > targetNum) {
			targetName = Constants.HISTORICAL_S;
			targetNum = historical;
			targetArr.clear();
			targetArr.add(targetName);
		} else if (treasure == targetNum) {
			targetArr.add(Constants.HISTORICAL_S);
		}
		if (resort > targetNum) {
			targetName = Constants.RESORT_S;
			targetNum = resort;
			targetArr.clear();
			targetArr.add(targetName);
		} else if (resort == targetNum) {
			targetArr.add(Constants.RESORT_S);
		}
		if (city > targetNum) {
			targetName = Constants.CITY_S;
			targetNum = city;
			targetArr.clear();
			targetArr.add(targetName);
		} else if (city == targetNum) {
			targetArr.add(Constants.CITY_S);
		}
		return targetArr;
	}
	
	/*
	 * おススメの旅行先を生成する
	 */
	public String generateLocation(List<String> arr) {
		String str = "あなたにオススメの旅行先はこちら!\n";
		//ランダム生成
		Random r = new Random();
		List<Integer> intArr = new ArrayList<Integer>();
		int rNum = 0;
		int counter = 0;
		if (arr.size() > 1) {
			for (int i = 1; i < 6; i++) {
				String targetStr = arr.get(r.nextInt(arr.size()));
				String[] candidateArr = null;
				switch (targetStr) {
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
				while (true) {
					counter++;
					rNum = r.nextInt(candidateArr.length);
					if (!intArr.contains(rNum) || counter > candidateArr.length) break;
					intArr.add(rNum);
				}
				str += "【第" + i + "位】" + candidateArr[rNum] + "\n";
				if(i==1)str += Constants.LINK + Constants.CODES.get(candidateArr[rNum])+ "\n";
			}
		} else {
			String targetStr = arr.get(0);
			String[] candidateArr = null;
			switch (targetStr) {
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
			for (int i = 1; i < 6; i++) {
				while (true) {
					rNum = r.nextInt(candidateArr.length);
					if (!intArr.contains(rNum))	break;
					intArr.add(rNum);
				}
				String target = candidateArr[r.nextInt(candidateArr.length)];
				str += "【第" + i + "位】" + target + "\n";
				if(i==1)str += Constants.LINK + Constants.CODES.get(target)+ "\n";
			}
		}
		return str;
	}
}