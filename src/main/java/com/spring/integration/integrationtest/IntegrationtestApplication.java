package com.spring.integration.integrationtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;

//@SpringBootApplication
public class IntegrationtestApplication {

	public static void main(String[] args) {
//		SpringApplication.run(IntegrationtestApplication.class, args);

		/**
		 * 1、 构造 MessageChannel
		 * 2、使用MessageHandler去订阅MessageChannel， 控制台打印消息
		 * 3、构造一个消息
		 * 4、把这个消息发送到MessageChannel
		 */

		DirectChannel channel = new DirectChannel();
		channel.subscribe(new MessageHandler() {
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				System.out.println(message);
			}
		});
		// 添加一个拦截器
		channel.addInterceptor(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				System.out.println("stop message");
				return null;
			}
		});
		Message msg = MessageBuilder.withPayload("simple msg").setHeader("test", "1").build();
		channel.send(msg);

	}

}
