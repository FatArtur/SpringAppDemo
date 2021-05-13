package com.example.SpringAppDemo.rest;


import com.example.SpringAppDemo.model.File;
import com.example.SpringAppDemo.model.Status;
import com.example.SpringAppDemo.service.BasicService;
import com.example.SpringAppDemo.service.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/moderator/")
public class ModeratorRestControllerV1 {
    private final BasicService basicService;
    private final S3Service s3Service;

    @Autowired
    public ModeratorRestControllerV1(@Qualifier("fileService") BasicService basicService, S3Service s3Service) {
        this.basicService = basicService;
        this.s3Service = s3Service;
    }


    @GetMapping(value = "files")
    public ResponseEntity<List<File>> getFiles() {
        List<File> result = basicService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "files/{id}")
    public ResponseEntity<File> getFileId(@PathVariable(value = "id") Long id) {
        File result = (File) basicService.findByID(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "filelocation/{name}")
    public ResponseEntity getLocation(@PathVariable(value = "name") String name) {
        String result = s3Service.getFileAddress(name);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping(value = "files")
    public ResponseEntity<File> addFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String filename = request.getHeader("filename");
        if (file == null | filename == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        java.io.File scratchFile = null;
        try {
            scratchFile = java.io.File.createTempFile("data", null);
            file.transferTo(scratchFile);
            s3Service.addFile(filename, scratchFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scratchFile.exists()) {
                scratchFile.delete();
            }
        }
        File result = new File();
        result.setName(filename);
        result.setAddress(s3Service.getURLAddress(filename));
        result.setFileStatus(Status.ACTIVE);
        basicService.save(result);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = "files/{id}")
    public ResponseEntity deleteFile(@PathVariable(value = "id") Long id) {
        File file = (File) basicService.findByID(id);
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        s3Service.deleteByName(file.getName());
        basicService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
