package service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

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

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;

public class ContentService {
	
	public  String getContent(String messageId,String jpegTarget) throws FileNotFoundException {
		try {
			final LineMessagingClient client = LineMessagingClient.builder(System.getenv("API_SECRET")).build();
			MessageContentResponse messageContentResponse = client.getMessageContent(messageId).get();        
			FileOutputStream outer = new FileOutputStream(jpegTarget);
			int data;
			//ファイル書き込み
			while (( data = messageContentResponse.getStream().read()) != -1) {
				outer.write((byte) data);
			}
			outer.flush();
			outer.close();
			FileSystem fs = FileSystems.getDefault();
			Path path = (fs.getPath(jpegTarget));
			System.out.println("ゲットコンテントできてる"+ path.toString());
			return "data:image/jpg;base64," + Base64.getEncoder().encodeToString((Files.readAllBytes(path)));
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public Path delJpeg(Path input) {
		return null;
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
