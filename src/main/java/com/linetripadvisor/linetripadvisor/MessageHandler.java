package com.linetripadvisor.linetripadvisor;

import java.io.IOException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@LineMessageHandler
public class MessageHandler {

	//テキストメッセージがきた時に呼ばれるよ
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException {
        System.out.println("event: " + event);
        System.out.println("送られたメッセージ: " + event.getMessage().getText());
        return new TextMessage("");
    }
    
    //イメージファイルがきた時に呼ばれるよ
    @EventMapping
    public TextMessage handleImageMessageEvent(MessageEvent<ImageMessageContent>event) {
    	Object receiveImage = event.getSource();
    	System.out.println("ここだよ");
    	System.out.println(event);
//    	BotApiResponse response = replyMessageHandler.reply(event);
    	return new TextMessage("お返ししま〜す");
    }
    //それ以外
    @EventMapping
    public TextMessage defaultMessageEvent(Event event) {
        System.out.println("デフォルトメッセージ: " + event);
        return new TextMessage("");
    }

}