package com.teknifity.unidirectional_hibernate_mapping.one_to_many.service;

import java.util.List;

import com.teknifity.unidirectional_hibernate_mapping.one_to_many.dao.CountryDao;
import com.teknifity.unidirectional_hibernate_mapping.one_to_many.entity.Country;
import com.teknifity.unidirectional_hibernate_mapping.one_to_many.entity.State;

import jakarta.persistence.EntityManager;

public class CountryService {

    private CountryDao dao = new CountryDao();

    public void saveCountry(EntityManager entityManager, Country country) {
        if (country != null) {
            dao.saveCountry(entityManager, country);
        } else {
            System.out.println("Country Object Cannot Be Null");
        }
    }

    public Country findCountryById(EntityManager entityManager, int countryId) {
        return dao.findCountryById(entityManager, countryId);
    }

    public List<Country> findAllCountries(EntityManager entityManager) {
        return dao.findAllCountries(entityManager);
    }

    public List<State> findAllStatesByCountryId(EntityManager entityManager, int countryId) {
        return dao.findAllStatesByCountryId(entityManager, countryId);
    }

    public State findStateByCountryIdAndStateId(EntityManager entityManager, int countryId, int stateId) {
        return dao.findStateByCountryIdAndStateId(entityManager, countryId, stateId);
    }

    public void addStateToCountry(EntityManager entityManager, int countryId, State state) {
        if (state != null) {
            dao.addStateToCountry(entityManager, countryId, state);
        } else {
            System.out.println("State Object Cannot Be Null");
        }
    }

    public void updateCountry(EntityManager entityManager, Country country) {
        if (country != null) {
            dao.updateCountry(entityManager, country);
        } else {
            System.out.println("Country Object Cannot Be Null");
        }
    }

    public void updateState(EntityManager entityManager, int countryId, State state) {
        if (state != null) {
            dao.updateState(entityManager, countryId, state);
        } else {
            System.out.println("State Object Cannot Be Null");
        }
    }

    public void deleteState(EntityManager entityManager, int countryId, int stateId) {
        dao.deleteState(entityManager, countryId, stateId);
    }

    public void deleteCountry(EntityManager entityManager, int countryId) {
        dao.deleteCountry(entityManager, countryId);
    }
}