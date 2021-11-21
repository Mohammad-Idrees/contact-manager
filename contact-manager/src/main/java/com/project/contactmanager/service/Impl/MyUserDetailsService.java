package com.project.contactmanager.service.Impl;

import com.project.contactmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        com.project.contactmanager.model.User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("No user found with username: " + email);
        }

        UserDetails userDetails = User.builder()
                .username(email)
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

        return userDetails;
    }


}
