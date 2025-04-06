package com.example.todolistjavabackend.repositories;

import com.example.todolistjavabackend.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findAppUserByUsername(String username);

}
