package com.teknifity.unidirectional_hibernate_mapping.many_to_many.controller;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.teknifity.unidirectional_hibernate_mapping.many_to_many.entity.Student;
import com.teknifity.unidirectional_hibernate_mapping.many_to_many.entity.Subject;
import com.teknifity.unidirectional_hibernate_mapping.many_to_many.service.StudentSubjectService;
import com.teknifity.unidirectional_hibernate_mapping.many_to_many.utils.JpaUtil;

public class SimpleMenuController {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		StudentSubjectService service = new StudentSubjectService();
		boolean flag = true;

		while (flag) {

			System.out.println("\n===== STUDENT SUBJECT MENU =====");
			System.out.println("1. Save Student With Subjects");
			System.out.println("2. Find Student By Id");
			System.out.println("3. Find Subject By Id");
			System.out.println("4. Add Subject To Existing Student");
			System.out.println("5. Remove Subject From Student");
			System.out.println("6. Display Student Subjects");
			System.out.println("7. Display All Students");
			System.out.println("8. Display All Subjects");
			System.out.println("9. Delete Student Safely");
			System.out.println("10. Delete Subject Safely");
			System.out.println("11. Exit");

			System.out.print("\nEnter Choice : ");
			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {

			case 1: {
				System.out.print("Enter Student Name : ");
				String studentName = scanner.nextLine();
				System.out.print("How Many Subjects ? : ");
				int count = Integer.parseInt(scanner.nextLine());
				Set<Subject> subjects = new HashSet<>();
				for (int i = 1; i <= count; i++) {
					System.out.print("Enter Subject Name : ");
					String subjectName = scanner.nextLine();
					Subject existingSubject =
							service.findSubjectByName(subjectName);
					if (existingSubject != null) {
						subjects.add(existingSubject);
					} else {
						Subject subject = new Subject();
						subject.setSubjectName(subjectName);
						subjects.add(subject);
					}
				}
				Student student = new Student();
				student.setStudentName(studentName);
				student.setSubjects(subjects);
				service.saveStudent(student);
				break;
			}

			case 2: {
				System.out.print("Enter Student Id : ");
				int studentId =
						Integer.parseInt(scanner.nextLine());
				System.out.println(
						service.findStudentById(studentId));
				break;
			}

			case 3: {
				System.out.print("Enter Subject Id : ");
				int subjectId =
						Integer.parseInt(scanner.nextLine());
				System.out.println(
						service.findSubjectById(subjectId));
				break;
			}

			case 4: {
				System.out.print("Enter Student Id : ");
				int studentId =
						Integer.parseInt(scanner.nextLine());
				System.out.print("Enter Subject Id : ");
				int subjectId =
						Integer.parseInt(scanner.nextLine());
				service.addSubjectToStudent(
						studentId, subjectId);
				break;
			}

			case 5: {
				System.out.print("Enter Student Id : ");
				int studentId =
						Integer.parseInt(scanner.nextLine());
				System.out.print("Enter Subject Id : ");
				int subjectId =
						Integer.parseInt(scanner.nextLine());
				service.removeSubjectFromStudent(
						studentId, subjectId);
				break;
			}

			case 6: {
				System.out.print("Enter Student Id : ");
				int studentId =
						Integer.parseInt(scanner.nextLine());
				service.displayStudentSubjects(studentId);
				break;
			}

			case 7: {
				service.displayAllStudents();
				break;
			}

			case 8: {
				service.displayAllSubjects();
				break;
			}

			case 9: {
				System.out.print("Enter Student Id : ");
				int studentId =
						Integer.parseInt(scanner.nextLine());
				service.deleteStudentSafely(studentId);
				break;
			}

			case 10: {
				System.out.print("Enter Subject Id : ");
				int subjectId =
						Integer.parseInt(scanner.nextLine());
				service.deleteSubjectSafely(subjectId);
				break;
			}

			case 11: {
				JpaUtil.closeFactory();
				scanner.close();
				flag = false;
				System.out.println("Application Closed");
				break;
			}

			default:
				System.out.println("Invalid Choice");
			}
		}
	}
}