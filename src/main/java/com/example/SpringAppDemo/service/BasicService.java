package com.example.SpringAppDemo.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface BasicService<T, Long> {
    T save(T val);

    T findByName(String name);

    void deleteById(Long id);

    T findByID (Long id);

    List<T> getAll();

    T update(T val);
}
