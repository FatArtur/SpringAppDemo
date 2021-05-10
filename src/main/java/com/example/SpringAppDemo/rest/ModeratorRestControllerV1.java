package com.example.SpringAppDemo.rest;


import com.example.SpringAppDemo.service.BasicService;
import com.example.SpringAppDemo.service.s3.S3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/files/")
public class ModeratorRestControllerV1 {
    private final BasicService basicService;
    private final S3Client s3Client;

    @Autowired
    public ModeratorRestControllerV1(BasicService basicService, S3Client s3Client) {
        this.basicService = basicService;
        this.s3Client = s3Client;
    }


}
