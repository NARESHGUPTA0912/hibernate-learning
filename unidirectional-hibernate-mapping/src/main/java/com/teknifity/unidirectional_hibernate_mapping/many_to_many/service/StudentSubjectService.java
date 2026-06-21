package com.teknifity.unidirectional_hibernate_mapping.many_to_many.service;

import java.util.List;

import com.teknifity.unidirectional_hibernate_mapping.many_to_many.dao.StudentSubjectDao;
import com.teknifity.unidirectional_hibernate_mapping.many_to_many.entity.Student;
import com.teknifity.unidirectional_hibernate_mapping.many_to_many.entity.Subject;
import com.teknifity.unidirectional_hibernate_mapping.many_to_many.utils.JpaUtil;

import jakarta.persistence.EntityManager;

public class StudentSubjectService {

    private final StudentSubjectDao dao = new StudentSubjectDao();

    /*
     * 1. Save Student
     */
    public void saveStudent(Student student) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.saveStudent(entityManager, student);
        entityManager.close();
    }
    /*
     * 2. Find Student By Id
     */
    public Student findStudentById(int studentId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        Student student = dao.findStudentById(entityManager, studentId);
        entityManager.close();
        return student;
    }

    /*
     * 3. Find Subject By Id
     */
    public Subject findSubjectById(int subjectId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        Subject subject = dao.findSubjectById(entityManager, subjectId);
        entityManager.close();
        return subject;
    }
    /*
     * 4. Update Student Name
     */
    public void updateStudentName(int studentId, String newName) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.updateStudentName(entityManager, studentId, newName);
        entityManager.close();
    }
    /*
     * 5. Add Subject To Student
     */
    public void addSubjectToStudent(int studentId, int subjectId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.addSubjectToStudent(entityManager, studentId, subjectId);
        entityManager.close();
    }

    /*
     * 6. Remove Subject From Student
     */
    public void removeSubjectFromStudent(int studentId, int subjectId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.removeSubjectFromStudent(entityManager, studentId, subjectId);
        entityManager.close();
    }

    /*
     * 7. Delete Student
     */
    public void deleteStudent(int studentId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.deleteStudent(entityManager, studentId);
        entityManager.close();
    }

    /*
     * 8. Delete Subject
     */
    public void deleteSubjectSafely(int subjectId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.deleteSubjectSafely(entityManager, subjectId); // it make sure remove all referenced
        entityManager.close();
    }

    /*
     * 9. Display Student Subjects
     */
    public void displayStudentSubjects(int studentId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.displayStudentSubjects(entityManager, studentId);
        entityManager.close();
    }

    /*
     * 10. Display All Students
     */
    public void displayAllStudents() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.displayAllStudents(entityManager);
        entityManager.close();
    }

    /*
     * 11. Update Subject Name
     */
    public void updateSubjectName(int subjectId, String newName) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.updateSubjectName(entityManager, subjectId, newName);
        entityManager.close();
    }

    /*
     * 12. Add Multiple Subjects To Student
     */
    public void addSubjectsToStudent(int studentId, List<Integer> subjectIds) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.addSubjectsToStudent(entityManager, studentId, subjectIds);
        entityManager.close();
    }
    /*
     * 13. Remove All Subjects From Student
     */
    public void removeAllSubjectsFromStudent(int studentId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.removeAllSubjectsFromStudent(entityManager, studentId);
        entityManager.close();
    }

    /*
     * 14. Count Subjects Of Student
     */
    public void countSubjectsOfStudent(int studentId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.countSubjectsOfStudent(entityManager, studentId);
        entityManager.close();
    }

    /*
     * 15. Find Student By Name
     */
    public void findStudentByName(String name) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.findStudentByName(entityManager, name);
        entityManager.close();
    }

    /*
     * 16. Display All Subjects
     */
    public void displayAllSubjects() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.displayAllSubjects(entityManager);
        entityManager.close();
    }

    /*
     * 17. Assign Subject To Multiple Students
     */
    public void assignSubjectToStudents(int subjectId, List<Integer> studentIds) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.assignSubjectToStudents(entityManager, subjectId, studentIds);
        entityManager.close();
    }
    /*
     * 18. Delete Student Safely
     */
    public void deleteStudentSafely(int studentId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.deleteStudentSafely(entityManager, studentId);
        entityManager.close();
    }
    /*
     * 19. Refresh Student
     */
    public void refreshStudent(int studentId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.refreshStudent(entityManager, studentId);
        entityManager.close();
    }
    /*
     * 20. Get Student Count
     */
    public void getStudentCount() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        dao.getStudentCount(entityManager);
        entityManager.close();
    }
    
//    21
    public Subject findSubjectByName(String subjectName) {
        EntityManager entityManager =
                JpaUtil.getEntityManager();
        Subject subject =
                dao.findSubjectByName(entityManager,
                        subjectName);
        entityManager.close();
        return subject;
    }
}