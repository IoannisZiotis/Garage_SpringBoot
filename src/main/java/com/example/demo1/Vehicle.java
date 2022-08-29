package com.example.demo1;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {
    @Id
    private String Plates;

    private String Owner;

    private float Charge;

    private String Type;

    public Vehicle() {
    }

    public Vehicle(String plates, String owner, float charge, String type) {
        this.Plates = plates;
        this.Owner = owner;
        this.Charge = 0;
        this.Type = type;
    }

    public String getPlates() {
        return Plates;
    }

    public void setPlates(String plates) {
        Plates = plates;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public float getCharge() {
        return Charge;
    }

    public void setCharge(float charge) {
        Charge = charge;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return String.format(
                "Vehicle[plates=%s, owner='%s', type='%s']",
                Plates, Owner, Type);
    }

}

