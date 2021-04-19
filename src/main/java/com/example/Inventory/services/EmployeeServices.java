package com.example.Inventory.services;

import com.example.Inventory.entity.Employees;
import com.example.Inventory.repository.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;


// this service class to perform operation on the Employee Entity


@Service
public class EmployeeServices {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private employeeRepository  employeeRepository;

    StringBuilder randomPassword=new StringBuilder();




  //generating a random password
  /* this method call happen when first time employee added to the system
  and when request for forget password */

    public StringBuilder generatePassword(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";


        for (int i = 0; i < 6; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            randomPassword.append(AlphaNumericString
                    .charAt(index));
        }
        return randomPassword;
    }



    // method to adding employees
    public void addNewemployees(Employees employees){


         String password=generatePassword().toString();
//        employees.setPassword(password);
        System.out.println("password = "+password);


        employees.setPassword(passwordEncoder.encode(password));
        this.employeeRepository.save(employees);

    }


    // method to deleting employees
    public void deleteEmployees(Integer emp_id){

        this.employeeRepository.deleteById(emp_id);
    }


  //method to update/edit employee infromation
    public String editEmployees(Employees employee,Integer id){
        Employees emp=this.employeeRepository.findById(id).get();
        int emp_id=emp.getEmp_id();
        if(emp_id==id){
            emp.setName(employee.getName());
            emp.setEmail(employee.getEmail());
            emp.setPassword(passwordEncoder.encode(employee.getPassword()));
            emp.setRole(employee.getRole());
            this.employeeRepository.save(emp);

        }

        return "updated sucessfully";


    }


}
