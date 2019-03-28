package constants;

public class Constants {
	//旅行先
	public final static String LINK = "";
	//ネイチャー
	public final static String AUCKLAND = "オークランド";
	public final static String CAIRNS  = "ケアンズ";
	public final static String YELLOWKNIFE = "イエローナイフ";
	public final static String WHITEHORSE = "ホワイトホース";
	public final static String URANBARTOR = "ウランバートル";
	public final static String NAIROBI = "ナイロビ";
	public final static String MARAKESH = "マラケシュ";
	public final static String PAMUKKALE = "パムッカレ";
	public final static String KEIRIN = "桂林";
	public final static String[] NATURE = {AUCKLAND,CAIRNS,YELLOWKNIFE,WHITEHORSE,URANBARTOR,NAIROBI,MARAKESH,PAMUKKALE,KEIRIN};
	//トレジャー
	public final static String JOGJAKARTA = "ジョグジャカルタ";
	public final static String BAGAN = "バガン";
	public final static String ARGURA = "アーグラ";
	public final static String SHAUEN = "シャウエン";
	public final static String JERSALEM = "エルサレム";
	public final static String CAIRO = "カイロ";
	public final static String MACHUPICCHU = "マチュピチュ";
	public final static String ANMAN = "アンマン";
	public final static String[] TREASURE = {JOGJAKARTA,BAGAN,ARGURA,SHAUEN,JERSALEM,CAIRO,MACHUPICCHU,ANMAN};
	//ヒストリカル
	public final static String TALYN = "タリン";
	public final static String ST_PETERBURG = "サンクトペテルブルク";
	public final static String MOSCOW = "モスクワ";
	public final static String BARCERONA = "バルセロナ";
	public final static String HABANA = "ハバナ";
	public final static String LONDON = "ロンドン";
	public final static String PARIS = "パリス";
	public final static String PRAGUE = "プラハ";
	public final static String VIENNA = "ウィーン";
	public final static String VRADIVOSTOK = "ウラジオストク";
	public final static String[] HISTORICAL = {TALYN,ST_PETERBURG,MOSCOW,BARCERONA,HABANA,LONDON,PARIS,PRAGUE,VIENNA,VRADIVOSTOK};
	//リゾート
	public final static String HAWAII = "ハワイ";
	public final static String PHUKET = "プーケット";
	public final static String GUAAM = "グアム";
	public final static String KANKUN = "カンクン";
	public final static String MAIAMI = "マイアミ";
	public final static String MORUDIB = "モルディブ";
	public final static String DANAN = "ダナン";
	public final static String RANKAUI = "ランカウイ";
	public final static String ST_RINO = "サントリーニ";
	public final static String GOLDCOAST = "ゴールドコースト";
	public final static String CEBU = "セブ";
	public final static String BARI = "バリ";
	public final static String[] RESORT = {HAWAII,PHUKET,GUAAM,KANKUN,MAIAMI,MORUDIB,DANAN,RANKAUI,ST_RINO,GOLDCOAST,CEBU,BARI};	
	//シティ
	public final static String BANGKOK = "バンコク";
	public final static String KUALALUMPUR = "クアラルンプール";
	public final static String LA = "ロサンゼルス";
	public final static String NY = "ニューヨーク";
	public final static String VANCOUVER = "バンクーバー";
	public final static String HONGKONG = "香港";
	public final static String DUBAI = "ドバイ";
	public final static String KOSHU = "杭州";
	public final static String SINGAPORE = "シンガポール";
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

