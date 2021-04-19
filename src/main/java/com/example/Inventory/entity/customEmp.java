package com.example.Inventory.entity;

import com.example.Inventory.entity.Employees;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class customEmp implements UserDetails {



private Employees employee;

    public customEmp(Employees employee) {
        super();
        this.employee=employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(employee.getRole());
        System.out.println(simpleGrantedAuthority);
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getUsername() {
        return employee.getEmail();
    }
    @Override
    public String getPassword() {
        return employee.getPassword();
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
