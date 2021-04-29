package com.example.SpringAppDemo.repository;

import com.example.SpringAppDemo.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    File findByName(String name);
}
