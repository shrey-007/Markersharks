package com.example.demo.controller;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

//    @GetMapping("/showQuestions/{page}")
//    public String showQuestions(@PathVariable("page") Integer page, Model model, HttpSession session){
//        //get the user
//        User user=(User) model.getAttribute("user");
//
//        //this pageable contains information of current page and no. of questions per page
//        Pageable pageable = PageRequest.of(page,3);
//        Page<Question> questionList=questionRepository.findQuestionsByUser(user.getId(),pageable);
//        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//        System.out.println(questionList);
//        model.addAttribute("questions",questionList);
//        model.addAttribute("currentPage",page);
//        model.addAttribute("totalPages",questionList.getTotalPages());
//        return "showQuestions";
//    }
    @PostMapping("/api/supplier/query/{page}")
    public String getSuppliers(@PathVariable("page") Integer page,Model model,String location,String businessType,String process){
        logger.info(location);
        logger.info(businessType);
        logger.info(process);

        Pageable pageable = PageRequest.of(page,5);
        Page<Supplier> suppliers=supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcesses(location,businessType,process,pageable);

        logger.info("the list is {}-----------------------------------------------------------------------",suppliers);
        model.addAttribute("suppliers",suppliers);
        model.addAttribute("currentPage",page);
        model.addAttribute("selectedLocation", location);
        model.addAttribute("selectedBusinessType", businessType);
        model.addAttribute("selectedProcess", process);
        model.addAttribute("totalPages",suppliers.getTotalPages());

        return "dashboard2";
    }

}
