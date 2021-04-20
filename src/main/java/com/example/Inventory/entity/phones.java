package com.example.Inventory.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@AllArgsConstructor
@Entity
@Table(name = "phones")
public class phones {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private int id;
 @NotBlank
 @Column (unique = true)
 private String brand;
 @NotBlank
 private String model;
 private int instock;

 @JsonManagedReference
 @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "phone")
 private features features;


 public phones() {
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public int getInstock() {
  return instock;
 }

 public void setInstock(int instock) {
  this.instock = instock;
 }

 public String getBrand() {
  return brand;
 }

 public void setBrand(String brand) {
  this.brand = brand;
 }

 public String getModel() {
  return model;
 }

 public void setModel(String model) {
  this.model = model;
 }

 public com.example.Inventory.entity.features getFeatures() {
  return features;
 }

 public void setFeatures(com.example.Inventory.entity.features features) {
  this.features = features;
 }
}
