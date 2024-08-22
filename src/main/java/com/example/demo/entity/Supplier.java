package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String companyName;

    private String website;

    private String location;

    private String natureOfBusiness;

    private String manufacturingProcesses;

}
