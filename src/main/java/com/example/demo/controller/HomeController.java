package com.example.demo.controller;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    SupplierRepository supplierRepository;
    @PostMapping("/api/supplier/query")
    public String getSuppliers(Model model,String location,String business,String process){
        List<Supplier> suppliers=supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcessesContaining(location,business,process);
        model.addAttribute("suppliers",suppliers);
        return "dashboard";
    }

}
