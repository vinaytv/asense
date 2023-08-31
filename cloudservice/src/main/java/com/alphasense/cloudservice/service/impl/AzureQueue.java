package com.alphasense.cloudservice.service.impl;

import com.alphasense.cloudservice.dto.MessageRequest;
import com.alphasense.cloudservice.service.CloudQueueService;

import java.util.List;

public class AzureQueue implements CloudQueueService {
    @Override
    public List<String> receiveMessage() {

        return null;
    }

    @Override
    public void sendMessage(MessageRequest payLoad) {

    }
}
