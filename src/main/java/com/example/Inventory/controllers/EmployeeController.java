package com.example.Inventory.controllers;

import com.example.Inventory.entity.Employees;
import com.example.Inventory.messages.message;
import com.example.Inventory.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Random;


//this is controller to add, delete, update employee information

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private  EmployeeServices employeeServices;


    //Adding employee to the system

    @PostMapping("/addemployee")
    public ResponseEntity<?> addEmployees(@RequestBody Employees employees){
        try {
            employeeServices.addNewemployees(employees);
            return ResponseEntity.ok(new message("employee added successfully","success"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new message("problem while saving employee to system","Error"));
        }

    }

    //Removing employees from the system

    @DeleteMapping("/removeemployee/{emp_id}")
    public ResponseEntity<?> removeEmployees(@PathVariable("emp_id") Integer emp_id){
        try {
            employeeServices.deleteEmployees(emp_id);
            return ResponseEntity.ok(new message("employee deleted successfully","success"));
        }catch (UsernameNotFoundException ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new message("No Such user Exist","Error"));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new message("problem while deleting employee from system","Error"));
        }


    }


//updating employee information

    @PutMapping("/updateemployee/{emp_id}")
    public ResponseEntity<?> updateEmployees(@RequestBody Employees employee,@PathVariable("emp_id") Integer emp_id){
        try {

           String message= employeeServices.editEmployees(employee,emp_id);
            return ResponseEntity.ok(new message(message,"success"));
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new message("No such user exist" ,"Error"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new message("unable to update" ,"Error"));
        }

    }


}
