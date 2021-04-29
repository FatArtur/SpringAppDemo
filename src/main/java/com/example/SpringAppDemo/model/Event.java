package com.example.SpringAppDemo.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    @Column(name = "data")
    private Date date;
    @ManyToOne (optional=false)
    @JoinColumn (name="file_id")
    private File file;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", file_id=" + file +
                "}";
    }
}

