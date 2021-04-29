package com.example.SpringAppDemo.security.jwt;

import com.example.SpringAppDemo.model.Account;
import com.example.SpringAppDemo.model.Event;
import com.example.SpringAppDemo.model.File;
import com.example.SpringAppDemo.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {

    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final Account account;
    private final boolean enabled;
    private final List<File> files;
    private final List<Event> events;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id, String name, String email, String password, Account account, boolean enabled,
                   List<File> files, List<Event> events, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.account = account;
        this.enabled = enabled;
        this.files = files;
        this.events = events;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<Event> getEvents() {
        return events;
    }
}
