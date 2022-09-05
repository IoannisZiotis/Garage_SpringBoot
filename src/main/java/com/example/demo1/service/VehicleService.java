package com.example.demo1.service;

import com.example.demo1.Vehicle;

import java.util.List;

public interface VehicleService {
	List<Vehicle> getAllVehicles();


	Vehicle saveVehicle(Vehicle vehicle);

	Vehicle getVehicleById(String plates);

	Vehicle updateVehicle(Vehicle vehicle);
	
	void deleteVehicleById(String plates);
}