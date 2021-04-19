package com.example.Inventory.repository;

import com.example.Inventory.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepository extends JpaRepository<Employees,Integer> {

@Query("from Employees e where e.email=:email")
public Employees getEmployeeByName(@Param("email") String email);



}
