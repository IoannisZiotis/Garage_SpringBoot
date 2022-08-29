package com.example.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo1.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
//@RequestMapping("/")
public class FormAppController {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public FormAppController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/{form}", method= RequestMethod.GET)
    public String vehicleForm(Model model){
        model.addAttribute("vehicle",new Vehicle());
        return "form";
    }


    @RequestMapping(value="/{form}", method= RequestMethod.POST)
    public String customerSubmit(@ModelAttribute Vehicle vehicle, Model model) {

        model.addAttribute("vehicle", vehicle);
        String info = String.format("Vehicle Submission: plates = %s, owner = %s, type = %s",
                vehicle.getPlates(), vehicle.getOwner(), vehicle.getType());
        log.info(info);
        vehicleRepository.save(vehicle);

        return "result";
    }


}