package com.example.todolistjavabackend.repositories;
import com.example.todolistjavabackend.models.AppUser;
import com.example.todolistjavabackend.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUser(Optional<AppUser> user);



}
