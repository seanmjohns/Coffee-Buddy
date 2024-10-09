package com.example.springboot.Model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{
    
    @Autowired
    private AppUserRepository repository;
    
    //finds user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //calls findByUsername, unsure where this is declared
        Optional<AppUser> user = repository.findByUsername(username);
        System.out.println("Searching for user, username: " + username);
        if (user.isPresent()) {
            var userObj = user.get();
            System.out.println("Found user!");
            //builds user object to return from found user
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .build();    
        }else{
            System.out.println("Did not find user!");
            throw new UsernameNotFoundException(username);
        }
    }
    
    
    
}