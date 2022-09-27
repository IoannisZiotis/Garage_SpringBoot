package com.example.demo1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
public class Vehicle {
    @Id
    private String Plates;

    private String Owner;

    @Column(unique = true)
    private int Spot_id;
    private float Charge;
    @Column(columnDefinition = "VARCHAR(20) CHECK (Type in ('Car','Motorcycle'))")
    private String Type;

    private String Employee;
    private String Start_date;


    public Vehicle() {
    }

    public Vehicle(String plates, String owner, String type, String Employee, String start_date,int spot_id) {
        this.Plates = plates;
        this.Owner = owner;
        this.Type = type;
        this.Employee = Employee;
        this.Start_date = start_date;
        this.Spot_id = spot_id;
    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public String getStart_date() {
        return Start_date;
    }

    public void setStart_date(String start_date) {
        Start_date = start_date;
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

    public int getSpot_id() {
        return Spot_id;
    }

    public void setSpot_id(int spot_id) {
        Spot_id = spot_id;
    }

    @Override
    public String toString() {
        return String.format(
                "Vehicle[plates=%s, owner='%s', type='%s', employee='%s', employee='%s', start_date='%s',spot_id='%d']",
                Plates, Owner, Type, Employee, Start_date,Spot_id);
    }



}

