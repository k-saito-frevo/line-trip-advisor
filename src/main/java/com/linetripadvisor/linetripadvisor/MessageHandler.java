package com.linetripadvisor.linetripadvisor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	final String COUNTRY_INFO = "./static/country.json";
	//テキストメッセージがきた時に呼ばれるよ
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException {
        System.out.println("event: " + event);
        System.out.println("送られたメッセージ: " + event.getMessage().getText());
        Resource resource = resourceLoader.getResource(COUNTRY_INFO);
        System.out.println("ここここ");
        System.out.println(resource);
        return new TextMessage("");
    }
    
    //イメージファイルがきた時に呼ばれるよ
    @EventMapping
    public TextMessage handleImageMessageEvent(MessageEvent<ImageMessageContent>event) {
    	String messageId = event.getMessage().getId();
    	System.out.println("ここだよ");
    	ContentService contentService = new ContentService();
    	String imgSrc = contentService.getContent(messageId);
        //エンコード前にバイト配列に置き換える際のCharset
        Charset charset = StandardCharsets.UTF_8;
        String encodeResult = Base64.getEncoder().encodeToString(imgSrc.getBytes(charset)); 
    	System.out.println(encodeResult);
    	FaceRecognizeService faceRecognizeService = new FaceRecognizeService();
    	String result = faceRecognizeService.tryPost(encodeResult);
    	System.out.println("かえってきた！");
    	System.out.println(result);
//    	InputStream responseInputStream = event.getStream();
//    	InputStream is = getContentStream(event.getMessage());
//    	MessageContentResponse messageContentResponse = lineMessagingClient.getMessageContent(messageId).get();
//    	handleContent(
//                event.getMessage().getId(),
//                messageContentResponse -> replyMessage(messageContentResponse, event.getReplyToken()));
//    	BotApiResponse response = replyMessageHandler.reply(event);
    	return new TextMessage("お返ししま〜す");
    }
    //それ以外
    @EventMapping
    public TextMessage defaultMessageEvent(Event event) {
        System.out.println("デフォルトメッセージ: " + event);
        return new TextMessage("画像を送って!¥r¥n");
    }
    @GetMapping("test")
    public void testEvent() {
    	System.out.println("ここにきてるよー");
    }
    
    /**
     * メッセージコンテンツを取得する.
     * @param messageId メッセージID
     * @param messageConsumer メッセージコンシューマ
     */
//    private void handleContent(String messageId, Consumer<MessageContentResponse> messageConsumer) {
//        final MessageContentResponse messageContentResponse;
//        try {
//            messageContentResponse = lineMessagingClient.getMessageContent(messageId).get();
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//
//        messageConsumer.accept(messageContentResponse);
//    }

}