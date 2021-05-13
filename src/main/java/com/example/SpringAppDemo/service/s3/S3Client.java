package com.example.SpringAppDemo.service.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class S3Client {
    private AmazonS3 s3client;
    @Value("${aws.s3.bucketname}")
    private String bucketName;
    @Value("${aws.s3.accesskey}")
    private String accesskey;
    @Value("${aws.s3.secretkey}")
    private String secretkey;


    public AmazonS3 getS3client() {
        if (s3client == null) {
            s3client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                            accesskey, secretkey)))
                    .withRegion(Regions.EU_NORTH_1)
                    .build();
        }
        return s3client;
    }

    public String getBucketName() {
        return bucketName;
    }
}
