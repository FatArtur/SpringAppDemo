package com.example.SpringAppDemo.service;


import com.example.SpringAppDemo.model.File;
import com.example.SpringAppDemo.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Qualifier
@Slf4j
public class FileServiceImpl implements BasicService<File, Long>{

    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File save(File val) {
        File result = fileRepository.save(val);
        log.info("IN save - file: {} successfully saved", val.getName());
        return result;
    }

    @Override
    public File findByName(String name) {
        File result = fileRepository.findByName(name);
        log.info("IN findByName - file found by name - {}", name);
        return result;
    }

    @Override
    public void deleteById(Long id) {
        fileRepository.deleteById(id);
        log.info("IN delete file with id - {}", id);
    }

    @Override
    public File findByID(Long id) {
        File result = fileRepository.findById(id).orElse(null);
        if (result == null){
            log.warn("IN findByID - no file found by id - {}", id);
            return null;}
        log.info("IN findByID - file:{} found by id - {}", result, id);
        return result;
    }

    @Override
    public List<File> getAll() {
        List<File> result = fileRepository.findAll();
        log.info("IN getALl - {} files founds", result.size());
        return result;
    }

    @Override
    public File update(File val) {
        File updateFile = fileRepository.save(val);
        log.info("IN update file with name - {}", val.getName());
        return updateFile;
    }
}
