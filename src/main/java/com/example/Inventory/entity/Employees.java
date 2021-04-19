package com.example.Inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;



//Employees entity
@Data
@AllArgsConstructor
@Entity
@Table(name = "Employees")
public class Employees{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int Emp_id;
    @NotBlank
    private String Name;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String role;

    public Employees() {
    }


}
