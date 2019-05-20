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

# Guide to local deployment using sam-local and localstack
- Install Docker on your machine: https://www.docker.com/get-docker
- Install aws-sam-local following the guide on the github readme.
- Clone the localstack repo to your machine. Then, navigate to the local branch folder to spin up the Localstack using docker-compose: TMPDIR=/private$TMPDIR docker-compose up
  - You should seet "localstack_1 | Ready " when the localstack container is spinned up.
  - Then, you should check the network created for localstack container. The default is: localstack_default. 
    - Or you run the command: docker inspect <CONTAINER_ID> -f "{{json .NetworkSettings.Networks }}".
- Now, you will need to create bucket and send file to bucket:
  - Create bucket: 
    - aws --endpoint-url=http://localhost:4572 s3 mb s3://test_bucket
  - Copy file to bucket:
    - aws --endpoint-url=http://localhost:4572 s3 cp test.txt s3://test_bucket
- Finally, navigate to the folder where the SAM template resides and run:
  - sam local invoke AwsLambdaS3Local --log-file ./output.log -e event.json --docker-network <LOCALSTACK NETWORK>
    - Since the aws lambda function is executed in a docker container, it cant access the localstack deployed on the host machine. That is the reason you will need to deploy the container containing the lambda function to the same network as the localstack.
    - You can check on the output.log for debuging purpose.
    - There is also another way to pass trigger event to the lambda. You can read more from the sam-local github page.

# Guide to deploy as SAR
- Upload the sam template `template-sar.yaml` to the SAR console.
- Remember to update the `CodeUri` to point to the jar in your bucket.
- For the S3 bucket serving the `CodeUri`, the following is required for the bucket policy.
````
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Principal": {
                "Service":  "serverlessrepo.amazonaws.com"
            },
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::<your-bucket-name>/*"
        }
    ]
}
````
- SAM Template does not allow referencing of S3 Bucket.

# Reference:
- CLI tool for local development and testing of AWS lambda: https://github.com/awslabs/aws-sam-local
- For setting up local AWS cloud stack: https://github.com/localstack/localstack
- Reference for command: https://lobster1234.github.io/2017/04/05/working-with-localstack-command-line/
- http://bluesock.org/~willkg/blog/dev/using_localstack_for_s3.html
- http://www.frommknecht.net/spring-cloud-aws-messaging/
- https://docs.aws.amazon.com/serverlessrepo/latest/devguide/serverlessrepo-how-to-publish.html
- https://github.com/awslabs/serverless-application-model/blob/master/versions/2016-10-31.md
- https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-ref.html
- https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-api-permissions-reference.html

