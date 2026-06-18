package com.teknifity.unidirectional_hibernate_mapping.one_to_one.controller;

import com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity.Person;
import com.teknifity.unidirectional_hibernate_mapping.one_to_one.service.PersonService;

public class FindPersonController {

    public static void main(String[] args) {

        PersonService service = new PersonService();
        Person person = service.findPersonById(1);
        System.out.println(person);
        
    }
}