package com.example.springboot.Model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
    
    Optional<AppUser> findByUsername(String username); // This will be responsible for interfacing with the database
    
}
