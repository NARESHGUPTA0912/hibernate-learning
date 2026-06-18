package com.teknifity.unidirectional_hibernate_mapping.many_to_one.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int universityId;

    private String universityName;

    private String location;
}