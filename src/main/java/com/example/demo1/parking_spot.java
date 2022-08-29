package com.example.demo1;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
public class parking_spot{
    boolean used;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String start_date;
    String employee;
    @OneToOne
    @JoinColumn(name = "vehicle_parked_plates")
    Vehicle vehicle_parked;
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    public parking_spot() {
    }

    public Vehicle getVehicle_parked() {
        return vehicle_parked;
    }

    public void setVehicle_parked(Vehicle vehicle_parked) {
        this.vehicle_parked = vehicle_parked;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public parking_spot(int id, boolean used, String employee){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.id = id;
        this.used = used;
        this.employee = employee;
        this.used = false;
//        this.vehicle_parked = NULL;
        this.start_date = sdf1.format(timestamp);

    }
}
