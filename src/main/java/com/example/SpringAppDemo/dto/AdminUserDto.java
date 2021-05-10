package com.example.SpringAppDemo.dto;

import com.example.SpringAppDemo.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Account account;
    private List<File> files;
    private List<Event> events;
    private List<Role> roles;

    public static AdminUserDto fromUser(User user){
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setName(user.getName());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setPassword(user.getPassword());
        adminUserDto.setAccount(user.getAccount());
        adminUserDto.setFiles(user.getFiles());
        adminUserDto.setEvents(user.getEvents());
        adminUserDto.setRoles(user.getRoles());
        return adminUserDto;
    }


}
