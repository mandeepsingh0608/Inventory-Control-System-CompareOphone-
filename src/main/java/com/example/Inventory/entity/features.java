package com.example.Inventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@Entity
@Table(name = "features")
public class features {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int featureId;
    private String display;
  private String camera;
  private String Storage;
  private String battery;

  @JsonBackReference
  @OneToOne
  //@JsonIgnore
  private phones phone;

    public features() {
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getStorage() {
        return Storage;
    }

    public void setStorage(String storage) {
        Storage = storage;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public phones getPhone() {
        return phone;
    }

    public void setPhone(phones phone) {
        this.phone = phone;
    }
}
