package com.teknifity.unidirectional_hibernate_mapping.one_to_one.dao;

import java.util.List;

import com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity.Passport;
import com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class PersonDao {

	EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hibernate");

	public void savePerson(Person person) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(person);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
	// Without Cascade first save passport then save person
	public void savePerson(Person person, Passport passport) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(passport); // Save passport first
            em.persist(person);   // Then save person
            et.commit();
            System.out.println("Data Saved Successfully");
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
	
	public Person findPersonById(int personId) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, personId);
        em.close();
        return person;
    }

    public void updatePerson(Person person) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(person);
        et.commit();
        em.close();
    }

    public void deletePerson(Person person) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(em.merge(person));
        et.commit();
        em.close();
    }

    public List<Person> findAllPersons() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery(
                "select p from Person p",
                Person.class)
                .getResultList();
    }

}
