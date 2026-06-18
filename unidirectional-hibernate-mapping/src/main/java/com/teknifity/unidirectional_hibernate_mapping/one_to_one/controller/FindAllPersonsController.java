package com.teknifity.unidirectional_hibernate_mapping.one_to_one.controller;

import java.util.List;

import com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity.Person;
import com.teknifity.unidirectional_hibernate_mapping.one_to_one.service.PersonService;

public class FindAllPersonsController {

    public static void main(String[] args) {

        PersonService service = new PersonService();
        List<Person> persons = service.findAllPersons();
        persons.forEach(System.out::println);
        
    }
}