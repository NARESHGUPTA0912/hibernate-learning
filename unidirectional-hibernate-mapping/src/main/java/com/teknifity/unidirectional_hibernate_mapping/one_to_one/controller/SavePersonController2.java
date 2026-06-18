package com.teknifity.unidirectional_hibernate_mapping.one_to_one.controller;

import com.teknifity.unidirectional_hibernate_mapping.one_to_one.dao.PersonDao;
import com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity.Passport;
import com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity.Person;

public class SavePersonController2 {

	public static void main(String[] args) {
		Passport passport = new Passport();
        passport.setPassportId(102);
        passport.setPassportNumber("AUS123456");
        passport.setCountry("Australia");

        Person person = new Person();
        person.setPersonId(2);
        person.setPersonName("Emma Watson");
        person.setAge(32);
        person.setPassport(passport);

        PersonDao dao = new PersonDao();
        dao.savePerson(person, passport);

	}

}
