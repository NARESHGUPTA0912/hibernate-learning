package com.teknifity.unidirectional_hibernate_mapping.one_to_many.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.teknifity.unidirectional_hibernate_mapping.one_to_many.entity.Country;
import com.teknifity.unidirectional_hibernate_mapping.one_to_many.entity.State;
import com.teknifity.unidirectional_hibernate_mapping.one_to_many.service.CountryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MenuController {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate");
        EntityManager em = emf.createEntityManager();
        CountryService service = new CountryService();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {

            System.out.println("\n===== COUNTRY MANAGEMENT SYSTEM =====");
            System.out.println("1. Save Country");
            System.out.println("2. Find Country By Id");
            System.out.println("3. Find All Countries");
            System.out.println("4. Find All States By Country Id");
            System.out.println("5. Find Particular State");
            System.out.println("6. Add State To Existing Country");
            System.out.println("7. Update Country");
            System.out.println("8. Update State");
            System.out.println("9. Delete State");
            System.out.println("10. Delete Country");
            System.out.println("11. Exit");
            
            System.out.print("Enter Your Choice : ");
            int choice = sc.nextInt();

            switch (choice) {
            
                case 1: {
                    Country country = new Country();
                    System.out.println("Enter Country Id:");
                    country.setCountryId(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Enter Country Name:");
                    country.setCountryName(sc.nextLine());
                    System.out.println("Enter Country Capital:");
                    country.setCountryCapital(sc.nextLine());
                    System.out.println("How Many States?");
                    int count = sc.nextInt();
                    List<State> states = new ArrayList<>();

                    for (int i = 1; i <= count; i++) {
                        State state = new State();
                        System.out.println("\nEnter State Id:");
                        state.setStateId(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Enter State Name:");
                        state.setStateName(sc.nextLine());
                        System.out.println("Enter State Capital:");
                        state.setStateCapital(sc.nextLine());
                        System.out.println("Enter State Population:");
                        state.setStatePopulation(sc.nextLong());
                        states.add(state);
                    }
                    country.setStates(states);
                    service.saveCountry(em, country);
                    break;
                }

                case 2: {
                    System.out.println("Enter Country Id:");
                    int countryId = sc.nextInt();
                    Country country = service.findCountryById(em, countryId);
                    if (country != null) {
                        System.out.println(country);
                    } else {
                        System.out.println("Country Not Found");
                    }
                    break;
                }

                case 3: {
                    List<Country> countries = service.findAllCountries(em);
                    if (!countries.isEmpty()) {
                        countries.forEach(System.out::println);
                    } else {
                        System.out.println("No Countries Found");
                    }
                    break;
                }
                
                case 4: {
                    System.out.println("Enter Country Id:");
                    int countryId = sc.nextInt();
                    List<State> states = service.findAllStatesByCountryId(em, countryId);
                    if (states != null) {
                        states.forEach(System.out::println);
                    } else {
                        System.out.println("Country Not Found");
                    }
                    break;
                }

                case 5: {
                    System.out.println("Enter Country Id:");
                    int countryId = sc.nextInt();
                    System.out.println("Enter State Id:");
                    int stateId = sc.nextInt();
                    State state = service.findStateByCountryIdAndStateId(em, countryId, stateId);
                    if (state != null) {
                        System.out.println(state);
                    } else {
                        System.out.println("State Not Found");
                    }
                    break;
                }

                case 6: {
                    System.out.println("Enter Country Id:");
                    int countryId = sc.nextInt();
                    State state = new State();
                    System.out.println("Enter State Id:");
                    state.setStateId(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Enter State Name:");
                    state.setStateName(sc.nextLine());
                    System.out.println("Enter State Capital:");
                    state.setStateCapital(sc.nextLine());
                    System.out.println("Enter State Population:");
                    state.setStatePopulation(sc.nextLong());
                    service.addStateToCountry(em, countryId, state);
                    break;
                }

                case 7: {
                    Country country = new Country();
                    System.out.println("Enter Country Id:");
                    country.setCountryId(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Enter New Country Name:");
                    country.setCountryName(sc.nextLine());
                    System.out.println("Enter New Country Capital:");
                    country.setCountryCapital(sc.nextLine());

                    Country existingCountry = service.findCountryById(em, country.getCountryId());
                    if (existingCountry != null) {
                        country.setStates(existingCountry.getStates());
                        service.updateCountry(em, country);
                    } else {
                        System.out.println("Country Not Found");
                    }
                    break;
                }

                case 8: {
                    System.out.println("Enter Country Id:");
                    int countryId = sc.nextInt();
                    State state = new State();
                    System.out.println("Enter State Id:");
                    state.setStateId(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Enter New State Name:");
                    state.setStateName(sc.nextLine());
                    System.out.println("Enter New State Capital:");
                    state.setStateCapital(sc.nextLine());
                    System.out.println("Enter New State Population:");
                    state.setStatePopulation(sc.nextLong());
                    service.updateState(em, countryId, state);
                    break;
                }

                case 9: {
                    System.out.println("Enter Country Id:");
                    int countryId = sc.nextInt();
                    System.out.println("Enter State Id:");
                    int stateId = sc.nextInt();
                    service.deleteState(em, countryId, stateId);
                    break;
                }

                case 10: {
                    System.out.println("Enter Country Id:");
                    int countryId = sc.nextInt();
                    service.deleteCountry(em, countryId);
                    break;
                }

                case 11: {
                    flag = false;
                    System.out.println("Application Closed Successfully");
                    break;
                }

                default:
                    System.out.println("Invalid Choice");
            }
        }
        sc.close();
        em.close();
        emf.close();
    }
}