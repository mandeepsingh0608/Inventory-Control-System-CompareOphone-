package com.example.Inventory.services;

import com.example.Inventory.entity.phones;
import com.example.Inventory.repository.featureRepository;
import com.example.Inventory.repository.phoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class comparisionServices {

    @Autowired
    private phoneRepository phoneRepository;
    @Autowired
    private featureRepository featureRepository;
    List<phones> ListofPhones  = new ArrayList<>();
    List<phones> ListofPhones2  = new ArrayList<>();
    List<phones> ListofPhones3  = new ArrayList<>();

    // method of comparing two phones
    public List<phones> compareRequestedPhones(String brand1, String model1, String brand2, String model2){
      ListofPhones= List.of(this.phoneRepository.findAllPhoneByBrandANDByModel(brand1,model1));
      ListofPhones2= List.of(this.phoneRepository.findAllPhoneByBrandANDByModel(brand2,model2));
      ListofPhones3.addAll(ListofPhones);
      ListofPhones3.addAll(ListofPhones2);

      return ListofPhones3;
    }

}
