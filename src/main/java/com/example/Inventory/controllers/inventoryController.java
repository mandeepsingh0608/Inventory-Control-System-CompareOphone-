package com.example.Inventory.controllers;

import com.example.Inventory.entity.Employees;
import com.example.Inventory.entity.features;
import com.example.Inventory.entity.phones;
import com.example.Inventory.messages.message;
import com.example.Inventory.repository.employeeRepository;
import com.example.Inventory.services.phoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/access")
public class inventoryController {
    @Autowired
    private phoneService phoneService;
    @Autowired
    private com.example.Inventory.repository.employeeRepository employeeRepository;

//Post request method to add phones to database
    @PostMapping("/phone/addPhones")
    public ResponseEntity<message> savePhonesItem(@RequestBody phones phone){
     try{
         phoneService.addPhone(phone);
         return ResponseEntity.ok().body(new message("Phone saved successfully","success"));
     }catch (Exception e){
         e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new message("Error occur while saving", "Error"));
     }
    }

//post request method to add attributes of phones to the database.
//one phone could have more than on attributes, tracked based on id.
    @PostMapping("/phone/addPhones/{phoneId}/feature")
    public ResponseEntity<message> savePhonesAttributes(@RequestBody features feature, @PathVariable("phoneId") Integer phoneId ){
        try{
            phoneService.addAttribute(feature,phoneId);
            return ResponseEntity.ok().body(new message("Phone feature saved successfully","success"));
        }catch (NoSuchElementException elementException){
            elementException.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new message("no such element exist","error"));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new message("Error occur while saving", "Error"));
        }

    }


//getting list of all phones
    @GetMapping("/phone/phonelist")
    public ResponseEntity<List<phones>> retrievePhones(){
     try{
        return ResponseEntity.ok().body(phoneService.getAllPhones());

     }catch (Exception e){
         e.printStackTrace();
         return ResponseEntity.of(null);
     }
    }

//getting a list of phones of specific brands and by model
    @GetMapping("/phone")
    public ResponseEntity<?> retrievePhonesByBrandOrByModel(@RequestParam("brand") String brand,@RequestParam("model") String model){
        try{
            return ResponseEntity.ok().body(phoneService.getbyBrandAndbyModel(brand,model));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new message("no such element exist","error"));
        }
    }

    //getting a list of phones of specific brands
    @GetMapping("/phone/brand")
    public ResponseEntity<?> retrievePhonesByBrand(@RequestParam("brand") String brand){
        try{
            return ResponseEntity.ok().body(phoneService.getbyBrand(brand));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new message("no such element exist","error"));
        }
    }


   //update or replace particular feature record related specific Phone
    @PutMapping("/modify/updatephonefeature")
     public ResponseEntity<?> updatePhoneRecord(@RequestBody features feature, @RequestParam("brand") String brand, @RequestParam("model") String model,Principal principal){
        String username=principal.getName();

        Employees employee=this.employeeRepository.getEmployeeByName(username);
        if(employee.getRole().equals("ROLE_MANAGER") || employee.getRole().equals("ROLE_ADMIN")){
            try{
                String message=phoneService. updatephonefeature(feature,brand,model);
                return ResponseEntity.ok().body(new message(message,"success"));

            }catch (NoSuchElementException noSuchElementException){
                noSuchElementException.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new message("no such element exist","Error"));
            }
            catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new message("error while updation","Error"));
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new message("You don't have permissions","Unauthorized user"));
        }
 }

 //patch
    @PatchMapping("/modify/updatefield")
    public ResponseEntity<?> partialUpdate(@RequestBody phones inStockQuantity,@RequestParam("brand") String brand,@RequestParam("model") String model,Principal principal){
        String username=principal.getName();
        Employees employee=this.employeeRepository.getEmployeeByName(username);
        if(employee.getRole().equals("ROLE_MANAGER") || employee.getRole().equals("ROLE_ADMIN")){
            try{
                String message=phoneService.updatefield(inStockQuantity,brand,model);
                return ResponseEntity.ok().body(new message(message,"success"));
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new message("error while updation","Error"));
            }
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new message("You don't have permissions","Unauthorized user"));
        }


    }





//deleting Phone
 @DeleteMapping("/modify/delete")
public ResponseEntity<?> removePhone(@RequestParam("brand") String brand, @RequestParam("model") String model, Principal principal){
        String username=principal.getName();
     Employees employee=this.employeeRepository.getEmployeeByName(username);
     if(employee.getRole().equals("ROLE_MANAGER") || employee.getRole().equals("ROLE_ADMIN")){
         System.out.println(employee);
         try{
             String message=phoneService.deletePhone(brand,model);
             return ResponseEntity.ok().body(new message(message,"success"));

         }catch (Exception e){
             e.printStackTrace();
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new message("error while deletion","Error"));
         }
     }else{
         return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new message("You don't have permissions","Unauthorized user"));
     }

 }

 // deleteing particular attribute
    @DeleteMapping("/modify/deleteAtrribute/{feature_id}")
 public ResponseEntity<?> removePhoneAttribute(@PathVariable("feature_id") Integer feature_id,@RequestParam("brand") String brand,@RequestParam("model") String model,Principal principal){
        String username=principal.getName();
        Employees employee=this.employeeRepository.getEmployeeByName(username);
        if(employee.getRole().equals("ROLE_MANAGER") || employee.getRole().equals("ROLE_ADMIN")) {
            try{
                String message=phoneService.deletePhoneParticularfeature(feature_id,brand,model);
                return ResponseEntity.ok().body(new message(message,"success"));

            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new message("error while deletion","Error"));
            }
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new message("You don't have permissions","Unauthorized user"));
        }

 }



}







