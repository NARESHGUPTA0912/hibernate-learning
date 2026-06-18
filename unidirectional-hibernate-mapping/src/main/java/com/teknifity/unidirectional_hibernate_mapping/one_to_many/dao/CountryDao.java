package com.teknifity.unidirectional_hibernate_mapping.one_to_many.dao;

import java.util.List;

import com.teknifity.unidirectional_hibernate_mapping.one_to_many.entity.Country;
import com.teknifity.unidirectional_hibernate_mapping.one_to_many.entity.State;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CountryDao {

	public void saveCountry(EntityManager entityManager, Country country) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(country);
			transaction.commit();
			System.out.println("Country Saved Successfully");
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		}
	}

	public Country findCountryById(EntityManager entityManager, int countryId) {
		return entityManager.find(Country.class, countryId);
	}

	public void deleteCountry(EntityManager entityManager, int countryId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Country country = entityManager.find(Country.class, countryId);
			if (country != null) {
				entityManager.remove(country);
				System.out.println("Country Deleted Successfully");
			} else {
				System.out.println("Country Not Found");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		}
	}

	public List<Country> findAllCountries(EntityManager entityManager) {
		String jpql = "SELECT c FROM Country c";
		return entityManager.createQuery(jpql, Country.class).getResultList();
	}

	public List<State> findAllStatesByCountryId(EntityManager entityManager, int countryId) {
		Country country = entityManager.find(Country.class, countryId);
		if (country != null) 
			return country.getStates();
		return null;
	}

	public State findStateByCountryIdAndStateId(EntityManager entityManager, int countryId, int stateId) {
		Country country = entityManager.find(Country.class, countryId);
		if (country != null)
			for (State state : country.getStates())
				if (state.getStateId() == stateId) 
					return state;
		return null;
	}

	public void updateCountry(EntityManager entityManager, Country country) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(country);
			transaction.commit();
			System.out.println("Country Updated Successfully");
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		}
	}

	public void updateState(EntityManager entityManager, int countryId, State updatedState) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();

			Country country = entityManager.find(Country.class, countryId);
			if (country != null) {
				List<State> states = country.getStates();
				for (int i = 0; i < states.size(); i++) {
					if (states.get(i).getStateId() == updatedState.getStateId()) {
						states.set(i, updatedState);
						break;
					}
				}
				entityManager.merge(country);
				System.out.println("State Updated Successfully");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		}
	}

	public void deleteState(EntityManager entityManager, int countryId, int stateId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Country country = entityManager.find(Country.class, countryId);
			if (country != null) {
				country.getStates().removeIf(state -> state.getStateId() == stateId);
				entityManager.merge(country);
				System.out.println("State Deleted Successfully");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		}
	}

	public void addStateToCountry(EntityManager entityManager, int countryId, State state) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Country country = entityManager.find(Country.class, countryId);
			if (country != null) {
				country.getStates().add(state);
				entityManager.merge(country);
				System.out.println("State Added Successfully");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		}
	}
}