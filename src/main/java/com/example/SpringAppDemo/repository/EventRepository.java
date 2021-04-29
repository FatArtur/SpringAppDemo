package com.example.SpringAppDemo.repository;

import com.example.SpringAppDemo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
