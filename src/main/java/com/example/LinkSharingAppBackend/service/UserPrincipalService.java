package com.example.LinkSharingAppBackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.LinkSharingAppBackend.dto.UserPrincipal;
import com.example.LinkSharingAppBackend.entity.UserInfo;
import com.example.LinkSharingAppBackend.repository.UserInfoRepository;

@Component
public class UserPrincipalService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserPrincipal loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByEmail(name);
        return userInfo.map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("user " + name + " not found"));

    }

    // get currentUser method 
    // use this to add the relationship to the new objects

    /*
    public User getCurrentUser(){
        Optional<User> user;
        String userName;
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails){ // UserPrincipal
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        
        if(userRepository.existsByUsername(userName)){
            user = userService.findByUsername(userName);
        } 
        return user.get();
    } 
    */
}
