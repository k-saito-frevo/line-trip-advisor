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

//    private final ReplyMessageHandler replyMessageHandler;
//
//    public MessageHandler(ReplyMessageHandler replyMessageHandler) {
//        super();
//        this.replyMessageHandler = replyMessageHandler;
//    }

    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException {
        System.out.println("event: " + event);
        System.out.println("Sent messages: " + event.getMessage().getText());
    }
    
    @EventMapping
    public TextMessage handleImageMessageEvent(MessageEvent<ImageMessageContent>event) {
    	Object receiveImage = event.getSource();
    	
//    	BotApiResponse response = replyMessageHandler.reply(event);
    	return new TextMessage("お返ししま〜す");
    }

    @EventMapping
    public void defaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }

}