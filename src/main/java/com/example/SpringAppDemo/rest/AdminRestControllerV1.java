package com.example.SpringAppDemo.rest;


import com.example.SpringAppDemo.dto.AdminUserDto;
import com.example.SpringAppDemo.dto.UserDto;
import com.example.SpringAppDemo.model.User;
import com.example.SpringAppDemo.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {
    private final BasicService basicService;

    @Autowired
    public AdminRestControllerV1(@Qualifier("userService") BasicService basicService) {
        this.basicService = basicService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = (User) basicService.findByID(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AdminUserDto result = AdminUserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
