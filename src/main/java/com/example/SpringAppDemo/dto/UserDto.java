package com.example.SpringAppDemo.dto;

import com.example.SpringAppDemo.model.Event;
import com.example.SpringAppDemo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String userName;
    private List<FileDto> files;

    public static UserDto fromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getName());
        userDto.setFiles(user.getFiles().stream().map(FileDto::fromFile).collect(Collectors.toList()));
        return userDto;
    }


}
