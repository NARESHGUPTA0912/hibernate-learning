package com.teknifity.unidirectional_hibernate_mapping.many_to_many.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.teknifity.unidirectional_hibernate_mapping.many_to_many.entity.Student;
import com.teknifity.unidirectional_hibernate_mapping.many_to_many.entity.Subject;
import com.teknifity.unidirectional_hibernate_mapping.many_to_many.service.StudentSubjectService;
import com.teknifity.unidirectional_hibernate_mapping.many_to_many.utils.JpaUtil;

public class MenuController {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		StudentSubjectService service = new StudentSubjectService();
		boolean flag = true;

		while (flag) {

			System.out.println("\n===== STUDENT SUBJECT MENU =====");
			System.out.println("1. Save Student With Subjects");
			System.out.println("2. Find Student By Id");
			System.out.println("3. Find Subject By Id");
			System.out.println("4. Update Student Name");
			System.out.println("5. Add Subject To Student");
			System.out.println("6. Remove Subject From Student");
			System.out.println("7. Delete Student");
			System.out.println("8. Delete Subject");
			System.out.println("9. Display Student Subjects");
			System.out.println("10. Display All Students");
			System.out.println("11. Update Subject Name");
			System.out.println("12. Add Multiple Subjects To Student");
			System.out.println("13. Remove All Subjects From Student");
			System.out.println("14. Count Subjects Of Student");
			System.out.println("15. Find Student By Name");
			System.out.println("16. Display All Subjects");
			System.out.println("17. Assign Subject To Multiple Students");
			System.out.println("18. Delete Student Safely");
			System.out.println("19. Refresh Student");
			System.out.println("20. Get Student Count");
			System.out.println("21. Exit");

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
				int studentId = Integer.parseInt(scanner.nextLine());
				System.out.println(service.findStudentById(studentId));
				break;
			}

			case 3: {
				System.out.print("Enter Subject Id : ");
				int subjectId = Integer.parseInt(scanner.nextLine());
				System.out.println(service.findSubjectById(subjectId));
				break;
			}

			case 4: {
				System.out.print("Enter Student Id : ");
				int studentId = Integer.parseInt(scanner.nextLine());
				System.out.print("Enter New Name : ");
				String name = scanner.nextLine();
				service.updateStudentName(studentId, name);
				break;
			}

			case 5: {
				System.out.print("Enter Student Id : ");
				int studentId = Integer.parseInt(scanner.nextLine());
				System.out.print("Enter Subject Id : ");
				int subjectId = Integer.parseInt(scanner.nextLine());
				service.addSubjectToStudent(studentId, subjectId);
				break;
			}

			case 6: {
				System.out.print("Enter Student Id : ");
				int studentId = Integer.parseInt(scanner.nextLine());
				System.out.print("Enter Subject Id : ");
				int subjectId = Integer.parseInt(scanner.nextLine());
				service.removeSubjectFromStudent(studentId, subjectId);
				break;
			}

			case 7: {
				System.out.print("Enter Student Id : ");
				int studentId = Integer.parseInt(scanner.nextLine());
				service.deleteStudent(studentId);
				break;
			}

			case 8: {
				System.out.print("Enter Subject Id : ");
				int subjectId = Integer.parseInt(scanner.nextLine());
				service.deleteSubjectSafely(subjectId);
				break;
			}

			case 9: {
				System.out.print("Enter Student Id : ");
				int studentId = Integer.parseInt(scanner.nextLine());
				service.displayStudentSubjects(studentId);
				break;
			}

			case 10: {
				service.displayAllStudents();
				break;
			}

			case 11: {
				System.out.print("Enter Subject Id : ");
				int subjectId = Integer.parseInt(scanner.nextLine());
				System.out.print("Enter New Subject Name : ");
				String name = scanner.nextLine();
				service.updateSubjectName(subjectId, name);
				break;
			}

			case 12: {
				System.out.print("Enter Student Id : ");
				int studentId = Integer.parseInt(scanner.nextLine());
				System.out.print("How Many Subject Ids ? : ");
				int size = Integer.parseInt(scanner.nextLine());
				List<Integer> subjectIds = new ArrayList<>();
				for (int i = 1; i <= size; i++) {
					System.out.print("Enter Subject Id : ");
					subjectIds.add(Integer.parseInt(scanner.nextLine()));
				}
				service.addSubjectsToStudent(studentId, subjectIds);
				break;
			}

			case 13: {
				System.out.print("Enter Student Id : ");
				int studentId = Integer.parseInt(scanner.nextLine());
				service.removeAllSubjectsFromStudent(studentId);
				break;
			}

			case 14: {
				System.out.print("Enter Student Id : ");
				int studentId = Integer.parseInt(scanner.nextLine());
				service.countSubjectsOfStudent(studentId);
				break;
			}

			case 15: {
				System.out.print("Enter Student Name : ");
				String name = scanner.nextLine();
				service.findStudentByName(name);
				break;
			}

			case 16: {
				service.displayAllSubjects();
				break;
			}

			case 17: {
				System.out.print("Enter Subject Id : ");
				int subjectId = Integer.parseInt(scanner.nextLine());
				System.out.print("How Many Student Ids ? : ");
				int size = Integer.parseInt(scanner.nextLine());
				List<Integer> studentIds = new ArrayList<>();
				for (int i = 1; i <= size; i++) {
					System.out.print("Enter Student Id : ");
					studentIds.add(Integer.parseInt(scanner.nextLine()));
				}
				service.assignSubjectToStudents(subjectId, studentIds);
				break;
			}

			case 18: {
				System.out.print("Enter Student Id : ");
				int studentId = Integer.parseInt(scanner.nextLine());
				service.deleteStudentSafely(studentId);
				break;
			}

			case 19: {
				System.out.print("Enter Student Id : ");
				int studentId = Integer.parseInt(scanner.nextLine());
				service.refreshStudent(studentId);
				break;
			}

			case 20: {
				service.getStudentCount();
				break;
			}

			case 21: {
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