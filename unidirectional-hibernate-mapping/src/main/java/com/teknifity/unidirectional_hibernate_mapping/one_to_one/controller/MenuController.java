package com.teknifity.unidirectional_hibernate_mapping.one_to_one.controller;

import java.util.List;
import java.util.Scanner;

import com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity.Passport;
import com.teknifity.unidirectional_hibernate_mapping.one_to_one.entity.Person;
import com.teknifity.unidirectional_hibernate_mapping.one_to_one.service.PersonService;

public class MenuController {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PersonService service = new PersonService();

        while (true) {
            System.out.println("\n===== PERSON MANAGEMENT SYSTEM =====");
            System.out.println("1. Save Person");
            System.out.println("2. Find Person By Id");
            System.out.println("3. Update Person");
            System.out.println("4. Delete Person");
            System.out.println("5. Find All Persons");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            switch (choice) {

                case 1: {
                    Person person = new Person();
                    Passport passport = new Passport();
                    System.out.print("Enter Person Id: ");
                    person.setPersonId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Enter Person Name: ");
                    person.setPersonName(sc.nextLine());
                    System.out.print("Enter Age: ");
                    person.setAge(sc.nextInt());
                    System.out.print("Enter Passport Id: ");
                    passport.setPassportId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Enter Passport Number: ");
                    passport.setPassportNumber(sc.nextLine());
                    System.out.print("Enter Country: ");
                    passport.setCountry(sc.nextLine());
                    person.setPassport(passport);
                    service.savePerson(person);
                    break;
                }

                case 2: {
                    System.out.print("Enter Person Id: ");
                    int id = sc.nextInt();
                    Person person = service.findPersonById(id);
                    if (person != null) {
                        System.out.println(person);
                    }
                    break;
                }

                case 3: {
                    System.out.print("Enter Person Id To Update: ");
                    int id = sc.nextInt();
                    Person person = service.findPersonById(id);
                    if (person != null) {
                        sc.nextLine();
                        System.out.print("Enter New Name: ");
                        person.setPersonName(sc.nextLine());
                        System.out.print("Enter New Age: ");
                        person.setAge(sc.nextInt());
                        service.updatePerson(person);
                    }
                    break;
                }

                case 4: {
                    System.out.print("Enter Person Id To Delete: ");
                    int id = sc.nextInt();
                    service.deletePerson(id);
                    break;
                }

                case 5: {
                    List<Person> persons = service.findAllPersons();
                    if (persons.isEmpty()) {
                        System.out.println("No Records Found");
                    } else {
                        persons.forEach(System.out::println);
                    }
                    break;
                }

                case 6: {
                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);
                }

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}