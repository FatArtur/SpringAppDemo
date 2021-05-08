package com.example.SpringAppDemo.repository;

import com.example.SpringAppDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
