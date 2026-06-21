package com.teknifity.unidirectional_hibernate_mapping.many_to_many.dao;

import java.util.HashSet;
import java.util.List;

import com.teknifity.unidirectional_hibernate_mapping.many_to_many.entity.Student;
import com.teknifity.unidirectional_hibernate_mapping.many_to_many.entity.Subject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class StudentSubjectDao {

	/*
	 * 1. Save Student with Subjects
	 */
	public void saveStudent(EntityManager entityManager, Student student) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
//			entityManager.persist(student);
			entityManager.merge(student);
			transaction.commit();
			System.out.println("Student Saved");
		} catch (Exception e) {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
			e.printStackTrace();
		}
	}

	/*
	 * 2. Find Student By Id
	 */
	public Student findStudentById(EntityManager entityManager, int studentId) {
		return entityManager.find(Student.class, studentId);
	}

	/*
	 * 3. Find Subject By Id
	 */
	public Subject findSubjectById(EntityManager entityManager, int subjectId) {
		return entityManager.find(Subject.class, subjectId);
	}

	/*
	 * 4. Update Student Name
	 */
	public void updateStudentName(EntityManager entityManager, int studentId, String newName) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Student student = entityManager.find(Student.class, studentId);
			if (student != null) {
				student.setStudentName(newName);
				entityManager.merge(student);
				System.out.println("Updated");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
			e.printStackTrace();
		}
	}

	/*
	 * 5. Add Subject To Existing Student
	 */
	public void addSubjectToStudent(EntityManager entityManager, int studentId, int subjectId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Student student = entityManager.find(Student.class, studentId);
			Subject subject = entityManager.find(Subject.class, subjectId);
			if (student != null && subject != null) {
	            if (student.getSubjects() == null) {
	                student.setSubjects(new HashSet<>());
	            }
	            student.getSubjects().add(subject);
	            entityManager.merge(student);
	            System.out.println("Subject Added");
	        }
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
			e.printStackTrace();
		}
	}

	/*
	 * 6. Remove Subject From Student
	 */
	public void removeSubjectFromStudent(EntityManager entityManager, int studentId, int subjectId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Student student = entityManager.find(Student.class, studentId);
			if (student != null) {
				student.getSubjects().removeIf(subject -> subject.getSubjectId() == subjectId);
				entityManager.merge(student);
				System.out.println("Subject Removed");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
			e.printStackTrace();
		}
	}

	/*
	 * 7. Delete Student
	 */
	public void deleteStudent(EntityManager entityManager, int studentId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Student student = entityManager.find(Student.class, studentId);
			if (student != null) {
				entityManager.remove(student);
				System.out.println("Student Deleted");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
			e.printStackTrace();
		}
	}

	/*
	 * 8. Delete Subject
	 */
	public void deleteSubjectSafely(EntityManager entityManager, int subjectId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Subject subject = entityManager.find(Subject.class, subjectId);
			if (subject != null) {
				List<Student> students =
					entityManager.createQuery(
						"SELECT s FROM Student s JOIN s.subjects sub WHERE sub.subjectId = :id",
						Student.class)
					.setParameter("id", subjectId)
					.getResultList();
				for (Student student : students) {
					student.getSubjects().remove(subject);
				}
				entityManager.remove(subject);
				System.out.println("Subject Deleted Successfully");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/*
	 * 9. Display Student With Subjects
	 */
	public void displayStudentSubjects(EntityManager entityManager, int studentId) {
		Student student = entityManager.find(Student.class, studentId);
		if (student != null) {
			System.out.println("Student : " + student.getStudentName());
			for (Subject subject : student.getSubjects()) {
				System.out.println(subject.getSubjectId() + " " + subject.getSubjectName());
			}
		}
	}

	/*
	 * 10. Display All Students
	 */
	public void displayAllStudents(EntityManager entityManager) {
		List<Student> students = entityManager.createQuery("FROM Student", Student.class).getResultList();
		students.forEach(System.out::println);
	}

	/*
	 * 11. Update Subject Name
	 */
	public void updateSubjectName(EntityManager entityManager, int subjectId, String newName) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Subject subject = entityManager.find(Subject.class, subjectId);
			if (subject != null) {
				subject.setSubjectName(newName);
				entityManager.merge(subject);
				System.out.println("Subject Updated");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
			e.printStackTrace();
		}
	}

//	12
	public void addSubjectsToStudent(EntityManager entityManager, int studentId, List<Integer> subjectIds) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Student student = entityManager.find(Student.class, studentId);
			if (student != null) {
	            if (student.getSubjects() == null) {
	                student.setSubjects(new HashSet<>());
	            }
	            for (Integer id : subjectIds) {
	                Subject subject = entityManager.find(Subject.class, id);
	                if (subject != null) {
	                    student.getSubjects().add(subject);
	                }
	            }
	            entityManager.merge(student);
	        }
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
			e.printStackTrace();
		}
	}

//	13
	public void removeAllSubjectsFromStudent(EntityManager entityManager, int studentId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Student student = entityManager.find(Student.class, studentId);
			if (student != null) {
				student.getSubjects().clear();
				entityManager.merge(student);
				System.out.println("All Subjects Removed");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
			e.printStackTrace();
		}
	}

//	14
	public void countSubjectsOfStudent(EntityManager entityManager, int studentId) {
		Student student = entityManager.find(Student.class, studentId);
		if (student != null) {
			System.out.println("Total Subjects : " + student.getSubjects().size());
		}
	}

//	15
	public void findStudentByName(EntityManager entityManager, String name) {
		List<Student> students = entityManager.createQuery("FROM Student s WHERE s.studentName=:name", Student.class)
				.setParameter("name", name).getResultList();
		students.forEach(System.out::println);
	}

//	16
	public void displayAllSubjects(EntityManager entityManager) {
		List<Subject> subjects = entityManager.createQuery("FROM Subject", Subject.class).getResultList();
		subjects.forEach(System.out::println);
	}

//	17
	public void assignSubjectToStudents(EntityManager entityManager, int subjectId, List<Integer> studentIds) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Subject subject = entityManager.find(Subject.class, subjectId);
			if (subject != null) {
				for (Integer studentId : studentIds) {
					Student student = entityManager.find(Student.class, studentId);
					if (student != null) {
	                    if (student.getSubjects() == null) {
	                        student.setSubjects(new HashSet<>());
	                    }
	                    student.getSubjects().add(subject);
	                    entityManager.merge(student);
	                }
				}
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
			e.printStackTrace();
		}
	}

//	18
	public void deleteStudentSafely(EntityManager entityManager, int studentId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Student student = entityManager.find(Student.class, studentId);
			if (student != null) {
				student.getSubjects().clear();
				entityManager.merge(student);
				entityManager.remove(student);
				System.out.println("Deleted");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
			e.printStackTrace();
		}
	}

//	19
	public void refreshStudent(EntityManager entityManager, int studentId) {
	    EntityTransaction transaction = entityManager.getTransaction();
	    try {
	        transaction.begin();
	        Student student = entityManager.find(Student.class, studentId);
	        if (student != null) {
	            entityManager.refresh(student);
	            System.out.println("Refreshed");
	        }
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	}

//	20
	public void getStudentCount(EntityManager entityManager) {
		Long count = entityManager.createQuery("SELECT COUNT(s) FROM Student s", Long.class).getSingleResult();
		System.out.println("Total Students : " + count);
	}
	
//	21
	public Subject findSubjectByName(EntityManager entityManager, String subjectName) {
	    try {
	        return entityManager.createQuery(
	                "FROM Subject s WHERE s.subjectName = :name",
	                Subject.class)
	                .setParameter("name", subjectName)
	                .getSingleResult();
	    } catch (Exception e) {
	        return null;
	    }
	}
}