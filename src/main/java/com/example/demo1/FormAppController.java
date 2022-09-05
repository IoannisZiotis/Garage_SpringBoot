package com.example.demo1;

import com.example.demo1.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo1.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
//@RequestMapping("/")
public class FormAppController {

    private VehicleService vehicleService;
    private final VehicleRepository vehicleRepository;

//    @Autowired
//    public FormAppController(VehicleRepository vehiclerepository) {
//        this.vehiclerepository = vehiclerepository;
//    }

    public FormAppController(VehicleService vehicleService,VehicleRepository vehicleRepository) {
        this.vehicleService = vehicleService;
        this.vehicleRepository = vehicleRepository;
    }
    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/main/vehicles")
    public String listVehicles(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "vehicles";
    }

    @GetMapping("/vehicles/{plates}")
    public String deleteVehicle(@PathVariable String plates) {
        vehicleService.deleteVehicleById(plates);
        return "redirect:/main/vehicles";
    }

    @GetMapping("/vehicles/new")
    public String createVehicleForm(Model model) {

        // create student object to hold student form data
        Vehicle vehicle = new Vehicle();
        model.addAttribute("vehicle", vehicle);
        return "create_vehicle";

    }

    @RequestMapping(value="/{main}", method= RequestMethod.GET)
    public String mainpage(Model model){
        return "main";
    }

//    @RequestMapping(value="main/{form}", method= RequestMethod.GET)
//    public String vehicleForm(Model model){
//        model.addAttribute("vehicle",new Vehicle());
//        return "form";
//    }
//
//    @RequestMapping(value="/redirectToForm", method= RequestMethod.GET)
//    public String redirectToForm() {
//        return "redirect:main/form";
//    }
//
//    @RequestMapping(value="main/{form}", method= RequestMethod.POST)
//    public String customerSubmit(@ModelAttribute Vehicle vehicle, Model model) {
//
//        model.addAttribute("vehicle", vehicle);
//        String info = String.format("Vehicle Submission: plates = %s, owner = %s, type = %s",
//                vehicle.getPlates(), vehicle.getOwner(), vehicle.getType());
//        log.info(info);
//        vehicleRepository.save(vehicle);
//
//        return "result";
//    }


}