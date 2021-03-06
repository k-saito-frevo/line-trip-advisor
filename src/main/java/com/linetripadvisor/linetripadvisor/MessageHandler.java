package com.linetripadvisor.linetripadvisor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import model.Face;
import service.ContentService;
import service.FaceRecognizeService;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@LineMessageHandler
public class MessageHandler {
	
	@Autowired
    private LineMessagingClient lineMessagingClient;
	@Autowired
	ResourceLoader resourceLoader;
	
	
	//テキストメッセージがきた時に呼ばれるよ
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException {
        return new TextMessage("画像送って～");
    }
    
    //イメージファイルがきた時に呼ばれるよ
    @EventMapping
    public TextMessage handleImageMessageEvent(MessageEvent<ImageMessageContent>event) throws JsonParseException, JsonMappingException, IOException {
    	try {
	    	String messageId = event.getMessage().getId();
	    	ContentService contentService = new ContentService();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");        
			String jpegTarget = "./line-test" + sdf.format(date).toString() + ".jpg";
	    	//コンテンツを取得
	    	String imgStr = contentService.getContent(messageId,jpegTarget);
	    	if(imgStr.isEmpty() || imgStr == null) return new TextMessage("エラーです");
	    	//顔認証取得
	    	FaceRecognizeService faceRecognizeService = new FaceRecognizeService();
	    	String result = faceRecognizeService.tryPost(imgStr);
			ObjectMapper mapper = new ObjectMapper();
			Face face = mapper.readValue(result, Face.class);
			
			//ファイル削除
			FileSystem fs = FileSystems.getDefault();
			Path path = (fs.getPath(jpegTarget));
			Files.delete(path);
			if(face.faces.size()<1) {
				return new TextMessage("顔が検出されません。人の顔が入った画像を送信してください。");
			}
			String str = faceRecognizeService.recognizeFace(face);
	    	return new TextMessage(str);
    	}catch(Exception ex) {
    		System.out.println("ーーー【エラー発生！！】－－－");
    		System.out.println(ex);
    		return new TextMessage("エラーが発生しました。時間をおいて再度送ってみてください。");
    	}
    }
    //それ以外
    @EventMapping
    public TextMessage defaultMessageEvent(Event event) {
        System.out.println("デフォルトメッセージ: " + event);
        return new TextMessage("画像を送ってください!");
    }
    

}