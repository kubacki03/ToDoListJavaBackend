package com.example.todolistjavabackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewTaskRequest {
    private String title;
    private String description;
    private LocalDateTime deadline;

    private String  priority;


}
