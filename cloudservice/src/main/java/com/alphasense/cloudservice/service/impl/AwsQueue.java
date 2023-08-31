package com.alphasense.cloudservice.service.impl;

import com.alphasense.cloudservice.dto.MessageRequest;
import com.alphasense.cloudservice.exceptions.CloudQueueServiceException;
import com.alphasense.cloudservice.service.CloudQueueService;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.InvalidMessageContentsException;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service

public class AwsQueue implements CloudQueueService {


    @Autowired
    @Qualifier(value="aws-bean")
    private  AmazonSQS amazonSqs;


    @Value("${queue.name}")
    private String queueName;


    @Override
    public List<String> receiveMessage() {
        ReceiveMessageResult receiveMessageResult=amazonSqs.receiveMessage(queueName);
        List<String> list=new ArrayList<>();
        receiveMessageResult.getMessages().forEach(message -> {
            log.info("request body {} "+message.getBody());
            list.add(message.toString());
        });
        return list;
    }

    @Override
    public void sendMessage(MessageRequest messageRequest) {

        try {
            GetQueueUrlResult queueUrl = amazonSqs.getQueueUrl(queueName);
            log.info("URL {}", queueUrl.getQueueUrl());
            amazonSqs.sendMessage(queueUrl.getQueueUrl(), messageRequest.toString());
        } catch (QueueDoesNotExistException | InvalidMessageContentsException e) {
            log.error("Queue does not exist {}", e.getMessage());
            throw new CloudQueueServiceException(e.getMessage());
        }

    }




}
