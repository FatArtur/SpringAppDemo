package com.example.SpringAppDemo.service.s3;


import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.SpringAppDemo.service.s3.S3Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class S3Service {

    private final S3Client s3Client;
    
    @Autowired
    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void addFile(String filename, File file){
        PutObjectRequest putObjectRequest = new PutObjectRequest(s3Client.getBucketName(), filename, file);
        PutObjectResult putObjectResult = s3Client.getS3client().putObject(putObjectRequest);
        log.info("IN addFile from S3 file with name - {}", filename);
    }

    public String getFileAddress(String name){
        return s3Client.getS3client().getUrl(s3Client.getBucketName(), name).toExternalForm();
    }

    public String getURLAddress(String name){
        return "https://s3.eu-north-1.amazonaws.com/" + s3Client.getBucketName() + "/"
                + name;
    }



    public void deleteByName(String name) {
        s3Client.getS3client().deleteObject(s3Client.getBucketName(), name);
        log.info("IN delete from S3 file with name - {}", name);
    }

    public List<Bucket> getListBucket(){
        List<Bucket> buckets = s3Client.getS3client().listBuckets();
        return buckets;
    }
    
}
