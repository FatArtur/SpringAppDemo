package com.example.SpringAppDemo.dto;


import com.example.SpringAppDemo.model.File;
import com.example.SpringAppDemo.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileDto {
    private Long id;
    private String name;
    private Status fileStatus;


    public static FileDto fromFile(File file){
        FileDto fileDto = new FileDto();
        fileDto.setId(file.getId());
        fileDto.setName(file.getName());
        fileDto.setFileStatus(file.getFileStatus());
        return fileDto;
    }

}
