package com.example.SpringAppDemo.security;

import com.example.SpringAppDemo.model.User;
import com.example.SpringAppDemo.security.jwt.JwtUser;
import com.example.SpringAppDemo.security.jwt.JwtUserFactory;
import com.example.SpringAppDemo.service.BasicService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private final BasicService basicService;

    public JwtUserDetailService(BasicService basicService) {
        this.basicService = basicService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) basicService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("In loadUserByUsername - user with username: {} successfully loaded  ", username);
        return jwtUser;
    }
}
