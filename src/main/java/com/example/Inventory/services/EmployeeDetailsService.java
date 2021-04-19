package com.example.Inventory.services;

import com.example.Inventory.entity.customEmp;
import com.example.Inventory.entity.Employees;
import com.example.Inventory.repository.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//This is service which help to find and match user by username against http request
@Service
public class EmployeeDetailsService implements UserDetailsService {
    @Autowired
    private employeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employees employee=this.employeeRepository.getEmployeeByName(username);
//        System.out.println(employee.getPassword());
        if(username.equals(null)){
            throw  new UsernameNotFoundException("user does not exist");
        }

        customEmp customEmp=new customEmp(employee);
        return customEmp;
    }
}
