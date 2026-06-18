package com.teknifity.unidirectional_hibernate_mapping.many_to_one.entity;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int collegeId;

    private String collegeName;

    private String principalName;

    // No cascade used (first saved university then college.
    @ManyToOne //(cascade = { CascadeType.PERSIST, CascadeType.MERGE } ) 
    @JoinColumn(name = "university_id")
    private University university;
}