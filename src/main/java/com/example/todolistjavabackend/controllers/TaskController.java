package com.example.todolistjavabackend.controllers;


import com.example.todolistjavabackend.models.NewTaskRequest;
import com.example.todolistjavabackend.models.Task;
import com.example.todolistjavabackend.models.TaskDTO;
import com.example.todolistjavabackend.repositories.TaskRepository;
import com.example.todolistjavabackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/userTasks")
    public ResponseEntity<Map<String, List<TaskDTO>>> userTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            var user = userRepository.findAppUserByUsername(userDetails.getUsername());

            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }



            var taskList =taskRepository.findAllByUser(user);

            List<TaskDTO> listDTO  = new ArrayList<>();
            for (Task task : taskList) {
                var taskDTO = new TaskDTO();
                taskDTO.setId(task.getId());
                taskDTO.setTitle(task.getTitle());
                taskDTO.setDescription(task.getDescription());
                taskDTO.setPriority(task.getPriority());
                taskDTO.setDeadline(task.getDeadline());
                taskDTO.setCreated_at(task.getCreated_at());
                listDTO.add(taskDTO);
            }

            Map<String, List<TaskDTO>> response = new HashMap<>();

            response.put("tasks", listDTO);

            System.out.println(response);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/createTask")
    public ResponseEntity<Map<String, String>> createTask(@RequestBody NewTaskRequest newTaskRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            var user = userRepository.findAppUserByUsername(userDetails.getUsername());


            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Task newTask = new Task();
            newTask.setDeadline(newTaskRequest.getDeadline());
            newTask.setDescription(newTaskRequest.getDescription());
            newTask.setPriority(newTaskRequest.getPriority());
            newTask.setUser(user.orElseThrow());

            newTask.setTitle(newTaskRequest.getTitle());
            taskRepository.save(newTask);


            return ResponseEntity.ok(Map.of("message", "task created successfully"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @DeleteMapping("/deleteTask")
    public ResponseEntity<String> deleteTask(@RequestParam long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var user = userRepository.findAppUserByUsername(userDetails.getUsername());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var taskOpt = taskRepository.findById(id);
        if (taskOpt.isEmpty() || !(taskOpt.get().getUser().getUser_id()==user.get().getUser_id()) ) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized access");
        }

        taskRepository.deleteById(id);
        return ResponseEntity.ok("task deleted successfully");

        }
    }



