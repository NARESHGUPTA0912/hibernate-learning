package com.teknifity.unidirectional_hibernate_mapping.one_to_many.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class State {

    @Id
    private int stateId;

    private String stateName;
    private String stateCapital;
    private long statePopulation;
}