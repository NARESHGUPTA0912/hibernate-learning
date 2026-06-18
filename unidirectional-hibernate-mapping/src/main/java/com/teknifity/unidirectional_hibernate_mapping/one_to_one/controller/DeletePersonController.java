package com.teknifity.unidirectional_hibernate_mapping.one_to_one.controller;

import com.teknifity.unidirectional_hibernate_mapping.one_to_one.service.PersonService;

public class DeletePersonController {

    public static void main(String[] args) {

        PersonService service = new PersonService();
        service.deletePerson(1);
        
    }
}