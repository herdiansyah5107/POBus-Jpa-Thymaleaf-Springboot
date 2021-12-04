package com.busspringboot.model;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class User implements UserDetails{

    @SequenceGenerator(
        name = "juara_trapel",
        sequenceName = "juara_trapel",
        allocationSize = 1
        )
        @Id
        @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "juara_trapel"
        )
        private Long id;
        private String username;
        private String email;
        private String password;
        @Enumerated(EnumType.STRING)
        private UserRole userRole;

    public User(String username, 
                String email, 
                String password, 
                UserRole userRole) {
            this.username = username;
            this.email = email;
            this.password = password;
            this.userRole = userRole;
        }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = 
                new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
    

    
}
