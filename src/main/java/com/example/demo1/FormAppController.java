package com.example.demo1;

import com.example.demo1.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo1.repository.VehicleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
//@RequestMapping("/")
public class FormAppController {

    private VehicleService vehicleService;
    private final VehicleRepository vehicleRepository;

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");


    public FormAppController(VehicleService vehicleService,VehicleRepository vehicleRepository) {
        this.vehicleService = vehicleService;
        this.vehicleRepository = vehicleRepository;
    }
    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/main/vehicles")
    public String listVehicles(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        Garage garage = vehicleService.GetGarage();
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("Garage", garage);
        return "vehicles";
    }

    @GetMapping("/vehicles/{plates}")
    public String deliverVehicle(Model model,@PathVariable String plates) {
        Vehicle vehicle = vehicleService.getVehicleById(plates);
        vehicleService.DeliverVehicle(vehicle);
//        model.addAttribute("garage", garage);
//        return "garage";
        return "redirect:/main/vehicles";
    }

    @GetMapping("/vehicles/new")
    public String createVehicleForm(Model model) {

        Vehicle vehicle = new Vehicle();
        model.addAttribute("vehicle", vehicle);
        return "create_vehicle";
    }

//    @GetMapping("/vehicles/newveh")
//    public String Addvehicle(Model model){
//        return "redirect:/main/vehicles";
//    }

//    @RequestMapping(value="/{main}", method= RequestMethod.GET)
//    public String mainpage(Model model){
//        return "main";
//    }

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
    @RequestMapping(value="main/{form}", method= RequestMethod.POST)
    public String customerSubmit(@ModelAttribute Vehicle vehicle, Model model) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        vehicle.setStart_date(sdf1.format(timestamp));
        model.addAttribute("vehicle", vehicle);
        String info = String.format("Vehicle Submission: plates = %s, owner = %s, type = %s, employee = %s, start_date='%s'",
                vehicle.getPlates(), vehicle.getOwner(), vehicle.getType(), vehicle.getEmployee(), vehicle.getStart_date());
        log.info(info);
        vehicleRepository.SaveVehicle(vehicle);

        return "result";
    }



}