package com.example.Inventory.controllers;

import com.example.Inventory.entity.Employees;
import com.example.Inventory.messages.message;
import com.example.Inventory.repository.employeeRepository;
import com.example.Inventory.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class forgetPasswordController {
    String randompassword;
    @Autowired
    private EmployeeServices employeeServices;
    @Autowired
    private employeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //getting temporary password

    @GetMapping("/forgetpassword")
     public ResponseEntity<?> passwordFroget(@RequestParam("email") String email){
        String username=this.employeeRepository.getEmployeeByName(email).getEmail();
        Employees employee=this.employeeRepository.getEmployeeByName(email);
        if(username.equals(email)){
            randompassword= employeeServices.generatePassword().toString();
        }
           employee.setPassword(passwordEncoder.encode(randompassword));
           this.employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body("your Temporary Password = "+randompassword);
    }

//reseting own password after loging by using temporary password

    @PatchMapping("/resetpassword")
    public ResponseEntity<?> passwordReset(@RequestBody String password,@RequestParam("email") String email){
        String username=this.employeeRepository.getEmployeeByName(email).getEmail();
        Employees employee=this.employeeRepository.getEmployeeByName(email);
        if(username.equals(email)){
            System.out.println(password);
         employee.setPassword(passwordEncoder.encode(password));
        }
        this.employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body("your Password has been reset sucessfully");
    }


}
