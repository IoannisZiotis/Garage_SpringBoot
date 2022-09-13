package com.example.demo1.service.impl;

import com.example.demo1.Vehicle;
import com.example.demo1.service.VehicleService;
import org.springframework.stereotype.Service;
import com.example.demo1.repository.VehicleRepository;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository Vehiclerepository;

    public VehicleServiceImpl(VehicleRepository vehiclerepository) {
        Vehiclerepository = vehiclerepository;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return Vehiclerepository.findAll();
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return Vehiclerepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(String plates) {
        return Vehiclerepository.findById(plates).get();
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return Vehiclerepository.save(vehicle);
    }

    @Override
    public void DeliverVehicle(String plates) {
        Vehiclerepository.DeliverVehicle(plates);
    }
}

