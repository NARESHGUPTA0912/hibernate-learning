package com.teknifity.unidirectional_hibernate_mapping.many_to_one.controller;

import java.util.Scanner;

import com.teknifity.unidirectional_hibernate_mapping.many_to_one.entity.College;
import com.teknifity.unidirectional_hibernate_mapping.many_to_one.entity.University;
import com.teknifity.unidirectional_hibernate_mapping.many_to_one.service.UniversityCollegeService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MenuController {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hibernate");

        EntityManager entityManager = emf.createEntityManager();

        UniversityCollegeService service = new UniversityCollegeService();

        Scanner sc = new Scanner(System.in);

        boolean flag = true;

        while (flag) {

            System.out.println("\n===== UNIVERSITY COLLEGE MENU =====");
            System.out.println("1. Save University");
            System.out.println("2. Save College");
            System.out.println("3. Find College By Id");
            System.out.println("4. Find University By Id");
            System.out.println("5. Find University By College Id");
            System.out.println("6. Update College Name");
            System.out.println("7. Update Principal Name");
            System.out.println("8. Update University Name");
            System.out.println("9. Change University");
            System.out.println("10. Delete College");
            System.out.println("11. Delete University");
            System.out.println("12. Find All Colleges");
            System.out.println("13. Find All Universities");
            System.out.println("14. Exit");

            System.out.print("\nEnter Your Choice : ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1: {

                University university = new University();

                System.out.print("Enter University Name : ");
                university.setUniversityName(sc.nextLine());

                System.out.print("Enter University Location : ");
                university.setLocation(sc.nextLine());

                service.saveUniversity(entityManager, university);

                break;
            }

            case 2: {

                College college = new College();

                System.out.print("Enter College Name : ");
                college.setCollegeName(sc.nextLine());

                System.out.print("Enter Principal Name : ");
                college.setPrincipalName(sc.nextLine());

                System.out.print("Enter University Id : ");
                int universityId = sc.nextInt();

                service.saveCollege(entityManager, college, universityId);

                break;
            }

            case 3: {

                System.out.print("Enter College Id : ");
                int collegeId = sc.nextInt();

                service.findCollegeById(entityManager, collegeId);

                break;
            }

            case 4: {

                System.out.print("Enter University Id : ");
                int universityId = sc.nextInt();

                service.findUniversityById(entityManager, universityId);

                break;
            }

            case 5: {

                System.out.print("Enter College Id : ");
                int collegeId = sc.nextInt();

                service.findUniversityByCollegeId(entityManager, collegeId);

                break;
            }

            case 6: {

                System.out.print("Enter College Id : ");
                int collegeId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter New College Name : ");
                String collegeName = sc.nextLine();

                service.updateCollegeName(
                        entityManager,
                        collegeId,
                        collegeName);

                break;
            }

            case 7: {

                System.out.print("Enter College Id : ");
                int collegeId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter New Principal Name : ");
                String principalName = sc.nextLine();

                service.updatePrincipalName(
                        entityManager,
                        collegeId,
                        principalName);

                break;
            }

            case 8: {

                System.out.print("Enter University Id : ");
                int universityId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter New University Name : ");
                String universityName = sc.nextLine();

                service.updateUniversityName(
                        entityManager,
                        universityId,
                        universityName);

                break;
            }

            case 9: {

                System.out.print("Enter College Id : ");
                int collegeId = sc.nextInt();

                System.out.print("Enter New University Id : ");
                int universityId = sc.nextInt();

                service.changeUniversity(
                        entityManager,
                        collegeId,
                        universityId);

                break;
            }

            case 10: {

                System.out.print("Enter College Id : ");
                int collegeId = sc.nextInt();

                service.deleteCollege(entityManager, collegeId);

                break;
            }

            case 11: {

                System.out.print("Enter University Id : ");
                int universityId = sc.nextInt();

                service.deleteUniversity(entityManager, universityId);

                break;
            }

            case 12: {

                service.findAllColleges(entityManager);

                break;
            }

            case 13: {

                service.findAllUniversities(entityManager);

                break;
            }

            case 14: {

                flag = false;

                System.out.println("Thank You");

                break;
            }

            default:
                System.out.println("Invalid Choice");
            }
        }

        sc.close();
        entityManager.close();
        emf.close();
    }
}