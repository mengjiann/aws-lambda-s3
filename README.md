# aws-lambda-s3
A sample Java AWS Lambda function to listen to AWS S3 event and access the object from AWS using SDK.


# Guide
- Build the project using: mvn clean install
- Create and configure using the aws lambda requirement below.
- Upload the jar file with suffix "-aws" to AWS lambda.
- Update the handler field with: com.mj.aws.lambda.s3.AwsLambdaS3FunctionHandler
- Configure AWS S3 trigger for the lambda function.
-- Choose Object Created for Event type
-- Update the prefix field if you want only a specific folder.
- Or you can trigger using a template S3 Event.
- Trigger and test your lambda function!

# AWS Lambda Requirements:
- 256 MB of Memory. (Can try with lower one)
- Timeout 1 min.
- Execution role
-- Access to S3 objects Policy
-- Access to Cloud watch for logging Policy


# Libraries
- Spring Cloud Function: https://spring.io/blog/2017/07/05/introducing-spring-cloud-function
- AWS Java SDK
- Lombok
- Log4j
- Jackson Object Mapper
- Maven

# Credits
Based on https://dzone.com/articles/run-code-with-spring-cloud-function-on-aws-lambda
