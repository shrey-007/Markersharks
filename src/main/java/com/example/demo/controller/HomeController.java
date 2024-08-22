package com.example.demo.controller;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    Logger logger= LoggerFactory.getLogger(HomeController.class);
    @Autowired
    SupplierRepository supplierRepository;

    @RequestMapping("/")
    public String showDashboard(Model model) {
        // Add attributes to the model for the dropdowns
        model.addAttribute("locations", supplierRepository.findDistinctLocations());
        model.addAttribute("businessTypes", supplierRepository.findDistinctBusinessTypes());
        model.addAttribute("manufacturingProcesses", supplierRepository.findDistinctManufacturingProcesses());

        return "dashboard";
    }

    @PostMapping("/api/supplier/query")
    public String getSuppliers(Model model,String location,String businessType,String process){
        logger.info(location);
        logger.info(businessType);
        logger.info(process);
        List<Supplier> suppliers=supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcesses(location,businessType,process);
        logger.info("the list is {}-----------------------------------------------------------------------",suppliers);
        model.addAttribute("suppliers",suppliers);
        return "dashboard";
    }

}
