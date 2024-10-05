package com.example.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.Model.AppUser;
import com.example.springboot.Model.AppUserRepository;
//import com.example.springboot.Security.SecurityConfig.PasswordEncoder;

@RestController
public class RegistrationController {
    
    @Autowired
    private AppUserRepository myAppUserRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // Update the header of this, make sure you pass in the json
    @PostMapping(value = "/register", consumes = "application/json")
    public AppUser createUser(@RequestBody AppUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myAppUserRepository.save(user);
    }

    
    
}
