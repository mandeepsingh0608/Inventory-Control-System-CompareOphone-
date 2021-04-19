package com.example.Inventory.controllers;

import com.example.Inventory.entity.phones;
import com.example.Inventory.messages.message;
import com.example.Inventory.services.comparisionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class comparisionController {

 @Autowired
 private comparisionServices comparisionServices;

@GetMapping("/comparephones")
public ResponseEntity<?> comparePhones(@RequestParam("brand1") String brand1,@RequestParam("model1") String model1,
                                       @RequestParam("brand2") String brand2,@RequestParam("model2") String model2){
    try{
       return ResponseEntity.ok().body(comparisionServices.compareRequestedPhones(brand1,model1,brand2,model2));


    }catch (Exception e){
        e.printStackTrace();
        return ResponseEntity.ok().body(new message("here is comparable phones", "success"));
    }

}



}
