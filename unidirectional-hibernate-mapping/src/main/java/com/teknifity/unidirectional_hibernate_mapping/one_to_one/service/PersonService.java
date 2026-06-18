package com.teknifity.unidirectional_hibernate_mapping.one_to_one.service;

import java.util.List;

import com.teknifity.unidirectional_hibernate_mapping.one_to_one.dao.PersonDao;
import com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity.Person;

public class PersonService {

    private PersonDao dao = new PersonDao();

    public void savePerson(Person person) {
        dao.savePerson(person);
        System.out.println("Person Saved Successfully");
    }

    public Person findPersonById(int personId) {
        Person person = dao.findPersonById(personId);
        if(person != null) {
            return person;
        }
        System.out.println("Person Not Found");
        return null;
    }

    public void updatePerson(Person person) {
        dao.updatePerson(person);
        System.out.println("Person Updated Successfully");
    }

    public void deletePerson(int personId) {
        Person person = dao.findPersonById(personId);
        if(person != null) {
            dao.deletePerson(person);
            System.out.println("Person Deleted Successfully");
        } else {
            System.out.println("Person Not Found");
        }
    }

    public List<Person> findAllPersons() {
        return dao.findAllPersons();
    }
}