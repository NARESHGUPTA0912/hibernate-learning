package com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Passport {

    @Id
    private int passportId;

    private String passportNumber;

    private String country;


}