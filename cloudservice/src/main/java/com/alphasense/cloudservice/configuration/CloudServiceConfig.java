package com.alphasense.cloudservice.configuration;

import com.alphasense.cloudservice.exceptions.CloudQueueServiceException;
import com.alphasense.cloudservice.service.CloudQueueService;
import com.alphasense.cloudservice.service.impl.AwsQueue;
import com.alphasense.cloudservice.service.impl.AzureQueue;
import com.alphasense.cloudservice.service.impl.GoogleQueue;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudServiceConfig {


    @Value("${aws.access_key:#{null}}")
    private String awsAccessKey;

    @Value("${aws.secret_key:#{null}}")
    private String awsSecretKey;

    @Bean
    @ConditionalOnProperty(prefix = "cloud", name = "service", havingValue = "AZURE")
    public CloudQueueService awsQueue() {
        return new AwsQueue();
    }


    @Bean(name = "aws-bean")
    @ConditionalOnProperty(prefix = "cloud", name = "service", havingValue = "AWS")
    public AmazonSQS amazonSQSClient() {
        if(awsAccessKey==null || awsSecretKey==null){
            throw new CloudQueueServiceException("AWS credentials are required for this profile.");
        }
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKey, awsAccessKey);
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
