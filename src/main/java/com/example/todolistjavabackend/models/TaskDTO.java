package com.example.todolistjavabackend.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private int id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private LocalDateTime created_at = LocalDateTime.now();
    private String  priority;


}
