package com.example.BatDongSan.security;

import com.example.BatDongSan.entity.User;
import com.example.BatDongSan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BDSUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if(user != null)
        {
            return new BDSUserDetails(user);
        }
        throw new UsernameNotFoundException("Could not find user with email : " + email);
    }
}
