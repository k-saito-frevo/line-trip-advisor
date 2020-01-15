package constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	Constants(){
		
	}
	//文字列
	public final static String API_KEY_L = "API_KEY";
	public final static String API_KEY_S = "api_key";
	public final static String API_SECRET_L = "API_SECRET";
	public final static String API_SECRET_S = "api_secret";
	public final static String IMAGE_BASE_64 = "image_base64"; 
	public final static String RETURN_ATTRIBUTES = "return_attributes";
	public final static String SADDNESS_S ="sadness";
	public final static String NEUTRAL_S = "neutral";
	public final static String HAPPINESS_S = "happiness";
	public final static String ANGER_S	= "anger";
	public final static String DISGUST_S = "disgust";
	public final static String FEAR_S = "fear";
	public final static String SURPRISE_S = "surprise";
	public final static String TREASURE_S = "treasuse";
	public final static String HISTORICAL_S = "historical";
	public final static String RESORT_S = "resort";
	public final static String CITY_S = "city";
	public final static String NATURE_S = "nature";

	
	//顔認証API
	public final static String DETECT_URL = "https://api-us.faceplusplus.com/facepp/v3/detect";
	
	//旅行先
	public final static String LINK = "https://www.tripadvisor.jp/";
	public final static Map<String,String> CODES = new HashMap<String,String>(){
		{
			put(AUCKLAND,AUCKLAND_CODE);
			put(CAIRNS,CAIRNS_CODE);
			put(YELLOWKNIFE,YELLOWKNIFE_CODE);
			put(WHITEHORSE,WHITEHORSE_CODE);
			put(URANBARTOR,URANBARTOR_CODE);
			put(NAIROBI,NAIROBI_CODE);
			put(MARAKESH,MARAKESH_CODE);
			put(PAMUKKALE,PAMUKKALE_CODE);
			put(KEIRIN,KEIRIN_CODE);
			put(BANGKOK,BANGKOK_CODE);
			put(PHUKET,PHUKET_CODE);
			put(KUALALUMPUR,KUALALUMPUR_CODE);
			put(LA,LA_CODE);
			put(NY,NY_CODE);
			put(VANCOUVER,VANCOUVER_CODE);
			put(HONGKONG,HONGKONG_CODE);
			put(DUBAI,DUBAI_CODE);
			put(KOSHU,KOSHU_CODE);
			put(SINGAPORE,SINGAPORE_CODE);
			put(JOGJAKARTA,JOGJAKARTA_CODE);
			put(BAGAN,BAGAN_CODE);
			put(ARGURA,ARGURA_CODE);
			put(SHAUEN,SHAUEN_CODE);
			put(JERSALEM,JERSALEM_CODE);
			put(CAIRO,CAIRO_CODE);
			put(MACHUPICCHU,MACHUPICCHU_CODE);
			put(ANMAN,ANMAN_CODE);
			put(TALYN,TALYN_CODE);
			put(ST_PETERBURG,ST_PETERBURG_CODE);
			put(MOSCOW,MOSCOW_CODE);
			put(BARCERONA,BARCERONA_CODE);
			put(HABANA,HABANA_CODE);
			put(LONDON,LONDON_CODE);
			put(PARIS,PARIS_CODE);
			put(PRAGUE,PRAGUE_CODE);
			put(VIENNA,VIENNA_CODE);
			put(VRADIVOSTOK,VRADIVOSTOK_CODE);
			put(HAWAII,HAWAII_CODE);
			put(PHUKET,PHUKET_CODE);
			put(GUAAM,GUAAM_CODE);
			put(KANKUN,KANKUN_CODE);
			put(MAIAMI,MAIAMI_CODE);
			put(MORUDIB,MORUDIB_CODE);
			put(DANAN,DANAN_CODE);
			put(RANKAUI,RANKAUI_CODE);
			put(ST_RINO,ST_RINO_CODE);
			put(GOLDCOAST,GOLDCOAST_CODE);
			put(CEBU,CEBU_CODE);
			put(BARI,BARI_CODE);			
		}
	}; 	
	//ネイチャー
	public final static String AUCKLAND = "オークランド";
	public final static String AUCKLAND_CODE = "Home-g1811027";
	public final static String CAIRNS  = "ケアンズ";
	public final static String CAIRNS_CODE = "Home-g255069";
	public final static String YELLOWKNIFE = "イエローナイフ";
	public final static String YELLOWKNIFE_CODE = "Home-g154966";	
	public final static String WHITEHORSE = "ホワイトホース";
	public final static String WHITEHORSE_CODE = "Home-g155047";
	public final static String URANBARTOR = "ウランバートル";
	public final static String URANBARTOR_CODE = "Home-g293956";
	public final static String NAIROBI = "ナイロビ";
	public final static String NAIROBI_CODE = "Home-g294207";
	public final static String MARAKESH = "マラケシュ";
	public final static String MARAKESH_CODE = "Home-g293734";
	public final static String PAMUKKALE = "パムッカレ";
	public final static String PAMUKKALE_CODE = "Home-g297992";
	public final static String KEIRIN = "桂林";
	public final static String KEIRIN_CODE = "Home-g298556";
	public final static String[] NATURE = {AUCKLAND,CAIRNS,YELLOWKNIFE,WHITEHORSE,URANBARTOR,NAIROBI,MARAKESH,PAMUKKALE,KEIRIN};

	//トレジャー
	public final static String JOGJAKARTA = "ジョグジャカルタ";
	public final static String JOGJAKARTA_CODE = "Home-g294230";
	public final static String BAGAN = "バガン";
	public final static String BAGAN_CODE = "Home-g317112";
	public final static String ARGURA = "アーグラ";
	public final static String ARGURA_CODE = "Home-g297683";
	public final static String SHAUEN = "シャウエン";
	public final static String SHAUEN_CODE = "Home-g304013";
	public final static String JERSALEM = "エルサレム";
	public final static String JERSALEM_CODE = "Home-g293983";
	public final static String CAIRO = "カイロ";
	public final static String CAIRO_CODE = "Home-g294201";
	public final static String MACHUPICCHU = "マチュピチュ";
	public final static String MACHUPICCHU_CODE = "Home-g294318";
	public final static String ANMAN = "アンマン";
	public final static String ANMAN_CODE = "Home-g293986";
	public final static String[] TREASURE = {JOGJAKARTA,BAGAN,ARGURA,SHAUEN,JERSALEM,CAIRO,MACHUPICCHU,ANMAN};

	//ヒストリカル
	public final static String TALYN = "タリン";
	public final static String TALYN_CODE = "Home-g274958";
	public final static String ST_PETERBURG = "サンクトペテルブルク";
	public final static String ST_PETERBURG_CODE = "Home-g298507";
	public final static String MOSCOW = "モスクワ";
	public final static String MOSCOW_CODE = "Home-g298484";
	public final static String BARCERONA = "バルセロナ";
	public final static String BARCERONA_CODE = "Home-g187497";
	public final static String HABANA = "ハバナ";
	public final static String HABANA_CODE = "Home-g147271";
	public final static String LONDON = "ロンドン";
	public final static String LONDON_CODE = "Home-g186338";
	public final static String PARIS = "パリ";
	public final static String PARIS_CODE = "Home-g187147";
	public final static String PRAGUE = "プラハ";
	public final static String PRAGUE_CODE = "Home-g274707";
	public final static String VIENNA = "ウィーン";
	public final static String VIENNA_CODE = "Home-g190454";
	public final static String VRADIVOSTOK = "ウラジオストク";
	public final static String VRADIVOSTOK_CODE = "Home-g298496";
	public final static String[] HISTORICAL = {TALYN,ST_PETERBURG,MOSCOW,BARCERONA,HABANA,LONDON,PARIS,PRAGUE,VIENNA,VRADIVOSTOK};
	
	//リゾート
	public final static String HAWAII = "ハワイ";
	public final static String HAWAII_CODE = "Home-g28932";
	public final static String PHUKET = "プーケット";
	public final static String PHUKET_CODE = "Home-g293920";
	public final static String GUAAM = "グアム";
	public final static String GUAAM_CODE = "Home-g60668";
	public final static String KANKUN = "カンクン";
	public final static String KANKUN_CODE = "Home-g150807";
	public final static String MAIAMI = "マイアミ";
	public final static String MAIAMI_CODE = "Home-g34439";
	public final static String MORUDIB = "モルディブ";
	public final static String MORUDIB_CODE = "Home-g293953";
	public final static String DANAN = "ダナン";
	public final static String DANAN_CODE = "Home-g298085";
	public final static String RANKAUI = "ランカウイ";
	public final static String RANKAUI_CODE = "Home-g298283";
	public final static String ST_RINO = "サントリーニ";
	public final static String ST_RINO_CODE = "Home-g189433";
	public final static String GOLDCOAST = "ゴールドコースト";
	public final static String GOLDCOAST_CODE = "Home-g255337";
	public final static String CEBU = "セブ";
	public final static String CEBU_CODE = "Home-g294261";
	public final static String BARI = "バリ";
	public final static String BARI_CODE = "Home-g294226";
	public final static String[] RESORT = {HAWAII,PHUKET,GUAAM,KANKUN,MAIAMI,MORUDIB,DANAN,RANKAUI,ST_RINO,GOLDCOAST,CEBU,BARI};
	
	//シティ
	public final static String BANGKOK = "バンコク";
	public final static String BANGKOK_CODE = "Home-g293916";
	public final static String KUALALUMPUR = "クアラルンプール";
	public final static String KUALALUMPUR_CODE = "Home-g298570";
	public final static String LA = "ロサンゼルス";
	public final static String LA_CODE = "Home-g32655";
	public final static String NY = "ニューヨーク";
	public final static String NY_CODE = "Home-g60763";
	public final static String VANCOUVER = "バンクーバー";
	public final static String VANCOUVER_CODE = "Home-g154943";
	public final static String HONGKONG = "香港";
	public final static String HONGKONG_CODE = "Home-g294217";
	public final static String DUBAI = "ドバイ";
	public final static String DUBAI_CODE = "Home-g295424";
	public final static String KOSHU = "杭州";
	public final static String KOSHU_CODE = "Home-g298559";
	public final static String SINGAPORE = "シンガポール";
	public final static String SINGAPORE_CODE = "Home-g294265";
	public final static String[] CITY = {BANGKOK,KUALALUMPUR,LA,NY,VANCOUVER,HONGKONG,DUBAI,KOSHU,SINGAPORE};
	
	//性別
	public final static String MALE = "Male";
	public final static String FEMALE = "Female";
	//係数
	public final static int MALE_NATURE = 10;
	public final static int MALE_TREASURE = 15;
	public final static int MALE_HISTORICAL = 15;
	public final static int MALE_RESORT = 10;
	public final static int MALE_CITY = 10;
	public final static int FEMALE_NATURE = 5;
	public final static int FEMALE_TREASURE = 5;
	public final static int FEMALE_HISTORICAL = 10;
	public final static int FEMALE_RESORT = 20;
	public final static int FEMALE_CITY = 15;
	public final static int YOUNGEST_NATURE = 10;
	public final static int YOUNGER_NATURE = 10;
	public final static int OLDER_NATURE = 5;
	public final static int OLDEST_NATURE = 15;
	public final static int YOUNGEST_TREASURE = 15;
	public final static int YOUNGER_TREASURE = 15;
	public final static int OLDER_TREASURE = 10;
	public final static int OLDEST_TREASURE = 5;
	public final static int YOUNGEST_HISTORICAL = 5;
	public final static int YOUNGER_HISTORICAL = 10;
	public final static int OLDER_HISTORICAL = 15;
	public final static int OLDEST_HISTORICAL = 15;
	public final static int YOUNGEST_RESORT = 5;
	public final static int YOUNGER_RESORT = 20;
	public final static int OLDER_RESORT = 15;
	public final static int OLDEST_RESORT = 15;
	public final static int YOUNGEST_CITY = 5;
	public final static int YOUNGER_CITY = 15;
	public final static int OLDER_CITY = 10;
	public final static int OLDEST_CITY = 15;
	public final static int SADNESS_NATURE = 5;
	public final static int NEUTRAL_NATURE =  10; 
	public final static int ANGER_NATURE = 15;
	public final static int DISGUST_NATURE = 10;
	public final static int FEAR_NATURE = 10;
	public final static int SURPRISING_NATURE = 5;
	public final static int HAPPINESS_NATURE = 10;
	public final static int SADNESS_TREASURE = 5;
	public final static int NEUTRAL_TREASURE =  15; 
	public final static int ANGER_TREASURE = 10;
	public final static int DISGUST_TREASURE =10; 
	public final static int FEAR_TREASURE = 5;
	public final static int SURPRISING_TREASURE = 10;
	public final static int HAPPINESS_TREASURE = 15;
	public final static int SADNESS_HISTORICAL = 10;
	public final static int NEUTRAL_HISTORICAL = 10;  
	public final static int ANGER_HISTORICAL = 5;
	public final static int DISGUST_HISTORICAL = 10;
	public final static int FEAR_HISTORICAL = 15;
	public final static int SURPRISING_HISTORICAL = 15;
	public final static int HAPPINESS_HISTORICAL = 10;
	public final static int SADNESS_RESORT = 15;
	public final static int NEUTRAL_RESORT =  10; 
	public final static int ANGER_RESORT = 10;
	public final static int DISGUST_RESORT = 10;
	public final static int FEAR_RESORT = 10;
	public final static int SURPRISING_RESORT = 10;
	public final static int HAPPINESS_RESORT =5; 
	public final static int SADNESS_CITY = 10;
	public final static int NEUTRAL_CITY = 5;
	public final static int ANGER_CITY = 10;
	public final static int DISGUST_CITY = 15;
	public final static int FEAR_CITY = 10;
	public final static int SURPRISING_CITY = 10;
	public final static int HAPPINESS_CITY = 10;
	
}

