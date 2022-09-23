package com.example.demo1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Garage {
    @Id
    @GeneratedValue
    private int id;

    private float Total_income;
    private int Num_of_spots;
    private int Num_used_spots;

    public Garage(){}

    public Garage(float total_income, int num_used_spots, int num_of_spots){
        this.Total_income = total_income;
        this.Num_used_spots = num_used_spots;
        this.Num_of_spots = num_of_spots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal_income() {
        return Total_income;
    }

    public void setTotal_income(float total_income) {
        Total_income = total_income;
    }

    public int getNum_of_spots() {
        return Num_of_spots;
    }

    public void setNum_of_spots(int num_of_spots) {
        Num_of_spots = num_of_spots;
    }

    public int getNum_used_spots() {
        return Num_used_spots;
    }

    public void setNum_used_spots(int num_used_spots) {
        Num_used_spots = num_used_spots;
    }
    @Override
    public String toString() {
        return String.format(
                "Garage[id=%d, num_of_spots='%d', total_income='%d', num_used_spots='%d']",
                id, Num_of_spots,Total_income,Num_used_spots);
    }
}
