package com.example.SpringAppDemo.rest;


import com.example.SpringAppDemo.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {
    private final BasicService basicService;

    @Autowired
    public AdminRestControllerV1(BasicService basicService) {
        this.basicService = basicService;
    }

}
