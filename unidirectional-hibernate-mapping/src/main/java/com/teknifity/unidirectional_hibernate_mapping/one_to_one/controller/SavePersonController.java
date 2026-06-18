package com.teknifity.unidirectional_hibernate_mapping.one_to_one.controller;

import com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity.Passport;
import com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity.Person;
import com.teknifity.unidirectional_hibernate_mapping.one_to_one.service.PersonService;

public class SavePersonController {

	public static void main(String[] args) {

        Passport passport = new Passport();
        passport.setPassportId(101);
        passport.setPassportNumber("IND123456");
        passport.setCountry("India");

        Person person = new Person();
        person.setPersonId(1);
        person.setPersonName("Naresh");
        person.setAge(22);
        person.setPassport(passport);

        PersonService service = new PersonService();
        service.savePerson(person);
        
        // PersonDao dao = new PersonDao();
        // dao.savePerson(person);
    }
}