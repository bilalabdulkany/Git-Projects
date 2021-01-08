package com.simpledev.kafka.controller;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simpledev.kafka.ChatMessage;
import com.simpledev.kafka.producer.Producer;

@RestController
public class Controller {

	private Producer producer;

	public Controller(Producer producer) {
		super();
		this.producer = producer;
	}
	@RequestMapping(value = "/sendMessage/complexType", method = RequestMethod.POST)

	public String publishMessageComplexType(@RequestBody ChatMessage payload) {
		payload.setTime(System.currentTimeMillis());
		producer.getMySource()
		.output()
		.send(MessageBuilder.withPayload(payload)
				.setHeader("type", "chat")
				.build());
		return "success";
	}
	// get the String message via HTTP, publish it to broker using spring cloud stream
	@RequestMapping(value = "/sendMessage/string", method = RequestMethod.POST)
	public String publishMessageString(@RequestBody String payload) {
	// send message to channel
	producer.getMySource()
	.output()
	.send(MessageBuilder.withPayload(payload)
	.setHeader("type", "string")
	.build());
	return "success";
	}
	
}
