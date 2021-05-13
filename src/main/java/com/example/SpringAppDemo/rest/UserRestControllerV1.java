package com.example.SpringAppDemo.rest;


import com.example.SpringAppDemo.dto.UserDto;
import com.example.SpringAppDemo.model.File;
import com.example.SpringAppDemo.model.Status;
import com.example.SpringAppDemo.model.User;
import com.example.SpringAppDemo.security.jwt.JwtTokenProvider;
import com.example.SpringAppDemo.service.BasicService;
import com.example.SpringAppDemo.service.s3.S3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final BasicService basicService;
    private final S3Client s3Client;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserRestControllerV1(@Qualifier("userService") BasicService basicService, S3Client s3Client, JwtTokenProvider jwtTokenProvider) {
        this.basicService = basicService;
        this.s3Client = s3Client;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping()
    public ResponseEntity<UserDto> getUserInfo(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        String token = bearerToken.substring(7, bearerToken.length());
        String username = jwtTokenProvider.getUsername(token);
        User user = (User) basicService.findByName(username);
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "files/{id}")
    public ResponseEntity getUserFile(@PathVariable(name = "id") Long id, HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        String token = bearerToken.substring(7, bearerToken.length());
        String username = jwtTokenProvider.getUsername(token);
        User user = (User) basicService.findByName(username);
        File file = user.getFiles().stream().filter(s -> s.getId() == id).findFirst().get();

        if (!file.getFileStatus().equals(Status.ACTIVE)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        String result = file.getAddress();
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
