package com.example.SpringAppDemo.service;

import com.example.SpringAppDemo.model.User;
import com.example.SpringAppDemo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("userService")
@Primary
@Slf4j
public class UserServiceImpl implements BasicService<User, Long>{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public User save(User val) {
        User result = userRepository.save(val);
        log.info("IN save user by name - {}", val.getName());
        return result;
    }

    @Override
    public User findByName(String name) {
        User result = userRepository.findByName(name);
        log.info("IN findByName - user found by name - {}", name);
        return result;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete user with id - {}", id);
    }

    @Override
    public User findByID(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null){
            log.warn("IN findByID - no user found by id - {}", id);
            return null;}
        log.info("IN findByID - user:{} found by id - {}", result, id);
        return result;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getALl - {} users founds", result.size());
        return result;
    }

    @Override
    public User update(User val) {
        User updateUser = userRepository.save(val);
        log.info("IN update user with name - {}", val.getName());
        return updateUser;
    }
}
