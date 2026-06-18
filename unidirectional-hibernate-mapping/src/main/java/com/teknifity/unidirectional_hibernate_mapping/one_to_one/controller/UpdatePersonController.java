package com.teknifity.unidirectional_hibernate_mapping.one_to_one.controller;

import com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity.Person;
import com.teknifity.unidirectional_hibernate_mapping.one_to_one.service.PersonService;

public class UpdatePersonController {

    public static void main(String[] args) {

        PersonService service = new PersonService();
        Person person = service.findPersonById(1);

        if(person != null) {
            person.setPersonName("Rahul");
            person.setAge(25);
            service.updatePerson(person);
        }
    }
}