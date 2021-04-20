package com.example.Inventory.services;

import com.example.Inventory.entity.Employees;
import com.example.Inventory.entity.features;
import com.example.Inventory.entity.phones;
import com.example.Inventory.repository.employeeRepository;
import com.example.Inventory.repository.featureRepository;
import com.example.Inventory.repository.phoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class phoneService {

    @Autowired
    private phoneRepository phoneRepository;
    @Autowired
    private featureRepository featureRepository;
    List<phones> ListofPhones  = new ArrayList<>();



//Saving phones to database
public void addPhone(phones phone){

    phoneRepository.save(phone);

}
//saving attributes of particular phone
public void addAttribute(features feature, Integer id){

phones phone = this.phoneRepository.getOne(id);

feature.setPhone(phone);
phone.setFeatures(feature);
this.phoneRepository.save(phone);

}


//getting list of all phones
public Page<phones> getAllPhones(Pageable pageable) {
return this.phoneRepository.findAll(pageable);

}
//getting list of phone by brands name ond By model name
public List<phones> getbyBrandAndbyModel(String brand,String model){

        ListofPhones  = List.of( this.phoneRepository.findAllPhoneByBrandANDByModel(brand,model));
        return ListofPhones;
}
//getting by brand name only
public List<phones> getbyBrand(String brand){
    ListofPhones=this.phoneRepository.findAllPhoneByBrand(brand);
    return ListofPhones;
}

//update phone's attribute
public String updatephonefeature(features newfeature, String brand, String model){
      features feature=this.phoneRepository.findAllPhoneByBrandANDByModel(brand, model).getFeatures();
      phones phone= this.phoneRepository.findAllPhoneByBrandANDByModel(brand, model);
      int oldfeatureId=feature.getFeatureId();
      newfeature.setPhone(phone);
      newfeature.setFeatureId(oldfeatureId);
      this.featureRepository.save(newfeature);

    return "successfully updated";
}


//update field
public String updatefield(phones inStockQuantity,String brand,String model){
    phones phone=this.phoneRepository.findAllPhoneByBrandANDByModel(brand, model);
    int quantity=inStockQuantity.getInstock();
    phone.setInstock(quantity);
    this.phoneRepository.save(phone);
    return "updated successfully";
}


//deleting phones
public String deletePhone(String brand,String model){

    phones phone=this.phoneRepository.findAllPhoneByBrandANDByModel(brand, model);
    this.phoneRepository.delete(phone);
    return  "successfully deleted";

}

//deleting particular attribute
public  String deletePhoneParticularfeature(int id,String brand, String model){
    phones phone=this.phoneRepository.findAllPhoneByBrandANDByModel(brand, model);

    Optional<features> feature = featureRepository.findById(id);
    //attribute.get().getAttributeId();
    if(feature.get().getFeatureId()==id){
        this.featureRepository.delete(feature.get());
    }
    return  "Attribute successfully deleted";
}




}
