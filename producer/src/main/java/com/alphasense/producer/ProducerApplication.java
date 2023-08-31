package com.alphasense.producer;

import com.alphasense.cloudservice.dto.MessageRequest;
import com.alphasense.cloudservice.service.CloudQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.alphasense.cloudservice"})
public class ProducerApplication {

	public static void main(String[] args) {
		MessageRequest messageRequest=MessageRequest.builder().name("SAMPLE_NAME").id("unique_id").amount(Long.valueOf(100)).build();
		ApplicationContext applicationContext = SpringApplication.run(ProducerApplication.class, args);
		CloudQueueService service = applicationContext.getBean(CloudQueueService.class);
		service.sendMessage(messageRequest);
		List<String> messages=service.receiveMessage();


		messages.forEach(message->{
			System.out.println("Recieved message==="+message);
		});
	}

}
