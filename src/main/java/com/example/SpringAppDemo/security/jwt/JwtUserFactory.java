package com.example.SpringAppDemo.security.jwt;

import com.example.SpringAppDemo.model.Role;
import com.example.SpringAppDemo.model.Status;
import com.example.SpringAppDemo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public  static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getAccount(),
                user.getAccount().getAccountStatus().equals(Status.ACTIVE),
                user.getFiles(),
                user.getEvents(),
                mapToGrantedAuthority(user.getRoles()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
