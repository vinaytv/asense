package com.alphasense.cloudservice.service;

import com.alphasense.cloudservice.dto.MessageRequest;

import java.util.List;


public interface CloudQueueService {

    /**
     * This method is used to receive list of messages
     * @return
     */
    List<String> receiveMessage();

    /**
     * This method is used to send message
     * @param  payLoad
     */
    void sendMessage(MessageRequest payLoad);
}
