package com.linetripadvisor.linetripadvisor;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.text.html.ImageView;

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

import com.google.common.io.Files;

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
		FileOutputStream out = new FileOutputStream("./binary.txt");
		for (int i = 0 ; i<bytes.length;i++) {
			out.write(bytes[i]);
		}
		out.flush();
		out.close();

		//base64で取得
//		byte[] encoded = Base64.encodeBase64(result.getBody().getBytes());
//		FileOutputStream out2 = new FileOutputStream("./base64.txt");
//		for (int i = 0 ; i<encoded.length;i++) {
//			out2.write(encoded[i]);
//		}
//		out2.flush();
//		out2.close();
//		
//		//FileWriter file = new FileWriter("./reuslt.jpg");
//		File file = new File("./rawString.txt");
//		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"ISO2022JP")));
//		String tetetete = result.getBody();
//		pw.println(tetetete);
//		pw.close();
		
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
		try (FileOutputStream fos = new FileOutputStream("./test.jpg")) {
			   fos.write(result.getBody().getBytes());
			   fos.close(); 
			}
		
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
		
		byte[] imageBinary = result.getBody().getBytes();
		System.out.println("kokonikiteruyo");
		Files.write(imageBinary,new File("./testConvert.jpg"));
//        try{
//        	FileInputStream in = new FileInputStream("base64string");
//        	OutputStream outer=new FileOutputStream("./testtest.png");
//        	ByteArrayOutputStream bo = new ByteArrayOutputStream();
//        	byte[] b = new byte[1024];
//        	int len;
//        	while((len = in.read(b, 0, b.length)) > 0){
//        		bo.write(b, 0, len);
//        	}
//        	BufferedImage bimage = toBufferedImage(Base64.getDecoder().decode(result.getBody().getBytes()));
//        	ImageIO.write(bimage, "png", outer);
//       	}catch(Exception e){
//     	   e.printStackTrace();
//   		}		
	}
	public static BufferedImage toBufferedImage(byte[] imageBinary) throws IOException{
		   BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBinary));
		   int width = img.getWidth();
		   int height = img.getHeight();
		   BufferedImage bufImage = new BufferedImage(img.getWidth(), height, BufferedImage.TYPE_INT_RGB);
		   for(int y = 0; y < height; y++){
		      for(int x = 0 ; x < width; x++){
		         int c = img.getRGB(x, y);
		         int r = c >> 16 & 0xff;
		         int g = c >> 8 & 0xff;
		         int b = c & 0xff;
		         int rgb = 0xff000000 | r << 16 | g << 8 | b;
		         bufImage.setRGB(x, y, rgb);
		      }
		   }
		   return bufImage;
	}
}
