package com.mj.aws.lambda.s3.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AwsClientConfig {

    @Bean
    @Profile("aws")
    public AmazonS3 awsS3Client(){
        return AmazonS3ClientBuilder.standard().build();
    }

    @Bean
    @Profile("local")
    public AmazonS3 awsS3ClientLocalStack(){
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration("http://localstack:4572","us-west-1"))
                .build();
    }

}
