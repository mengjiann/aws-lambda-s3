package com.mj.aws.lambda.s3;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class AwsLambdaS3FunctionHandler extends SpringBootRequestHandler<S3Event,S3Event> {
}
