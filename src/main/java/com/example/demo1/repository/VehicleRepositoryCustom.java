package com.example.demo1.repository;

import com.example.demo1.Garage;
import com.example.demo1.Vehicle;

import java.util.List;

public interface VehicleRepositoryCustom {
    public void DeliverVehicle(Vehicle vehicle);
    public Vehicle SaveVehicle(Vehicle vehicle);

    public Garage getGarage();
}
