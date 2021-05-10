package com.example.SpringAppDemo.service.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.stereotype.Component;

@Component
public class S3Client {
    private final AmazonS3 s3client;
    private final String bucketName = "${aws.s3.bucketname}";

    private final AWSCredentials credentials = new BasicAWSCredentials(
            "${aws.s3.accesskey}", "${aws.s3.secretkey}");

    public S3Client() {
        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_NORTH_1)
                .build();
    }

    public AmazonS3 getS3client() {
        return s3client;
    }

    public String getBucketName() {
        return bucketName;
    }
}
