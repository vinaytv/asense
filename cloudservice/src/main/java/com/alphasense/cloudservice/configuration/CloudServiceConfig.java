package com.alphasense.cloudservice.configuration;

import com.alphasense.cloudservice.service.CloudQueueService;
import com.alphasense.cloudservice.service.impl.AwsQueue;
import com.alphasense.cloudservice.service.impl.AzureQueue;
import com.alphasense.cloudservice.service.impl.GoogleQueue;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudServiceConfig {

    @Bean
    @ConditionalOnProperty(prefix = "cloud", name = "service", havingValue = "AZURE")
    public CloudQueueService awsQueue() {
        return new AwsQueue();
    }


    @Bean(name = "aws-bean")
    @ConditionalOnProperty(prefix = "cloud", name = "service", havingValue = "AWS")
    public AmazonSQS amazonSQSClient() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials("", "");
        return AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion("ap-south-1")
                .build();
    }
    @Bean
    @ConditionalOnProperty(prefix = "cloud", name = "service", havingValue = "AZURE")
    public CloudQueueService azureQueue() {
        return new AzureQueue();
    }

    @Bean
    @ConditionalOnProperty(prefix = "cloud", name = "service", havingValue = "GCP")
    public CloudQueueService googleQueue() {
        return new GoogleQueue();
    }
}
