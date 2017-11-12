package com.mj.aws.lambda.s3;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Component("awsLambdaS3Function")
public class AwsLambdaS3Function implements Function<S3Event,S3Event> {

    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public S3Event apply(S3Event s3Event) {

        log.info("S3 Event processing starts with record: {}", s3Event.toJson());

        // For each record.
        for (S3EventNotification.S3EventNotificationRecord record : s3Event.getRecords()) {

            String s3Key = record.getS3().getObject().getKey();
            String s3Bucket = record.getS3().getBucket().getName();

            log.info("Received record with bucket: {}  and key:  {}", s3Bucket ,s3Key);

            S3Object object = amazonS3.getObject(new GetObjectRequest(s3Bucket, s3Key));

            log.info("Retrieved s3 object: {} ", object);

        }

        return s3Event;
    }
}
