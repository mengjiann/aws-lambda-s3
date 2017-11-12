package com.mj.aws.lambda.s3.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsClientConfig {

    @Bean
    public AmazonS3 awsS3Client(){
        return AmazonS3ClientBuilder.standard().build();
    }

}
