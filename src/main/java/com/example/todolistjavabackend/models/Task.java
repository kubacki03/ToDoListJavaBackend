package com.example.todolistjavabackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private LocalDateTime created_at = LocalDateTime.now();
    private String  priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;


}
