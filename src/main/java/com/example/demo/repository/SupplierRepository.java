package com.example.demo.repository;

import com.example.demo.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

    // Retrieve a list of X number of manufacturer(s) in a given location, with a specific nature of business, and
    // the capability to perform a specific manufacturing process.
    List<Supplier> findByLocationAndNatureOfBusinessAndManufacturingProcessesContaining(String location, String natureOfBusiness, String manufacturingProcess);

}
