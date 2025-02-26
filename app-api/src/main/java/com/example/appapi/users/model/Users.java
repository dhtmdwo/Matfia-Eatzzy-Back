package com.example.appapi.users.model;

import com.example.appapi.store.model.AllowedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false, length=14, unique = true)
    private String userId;

    @Column(nullable = false, length=254)
    private String password;

    @Column(nullable = false, length=8)
    private String birthDate;

    @Column(nullable = false, length=10)
    private String name;

    @Column(nullable = false, length=254, unique = true)
    private String email;

    @Column(nullable = false, length=254)
    private String address;

    @Column(nullable = false, length=254)
    private String addressDetail;

    @Column(nullable = false, length=13)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType; // ENUM ('ADMIN', 'SELLER', 'CUSTOMER')

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(userType.name());

        authorities.add(authority);
        return authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
