package com.teknifity.unidirectional_hibernate_mapping.many_to_one.service;

import com.teknifity.unidirectional_hibernate_mapping.many_to_one.dao.UniversityCollegeDao;
import com.teknifity.unidirectional_hibernate_mapping.many_to_one.entity.College;
import com.teknifity.unidirectional_hibernate_mapping.many_to_one.entity.University;

import jakarta.persistence.EntityManager;

public class UniversityCollegeService {

	private UniversityCollegeDao dao = new UniversityCollegeDao();

	// SAVE

	public void saveUniversity(EntityManager entityManager, University university) {

		dao.saveUniversity(entityManager, university);
	}

	public void saveCollege(EntityManager entityManager, College college, int universityId) {

		dao.saveCollege(entityManager, college, universityId);
	}

	// FIND COLLEGE BY ID

	public void findCollegeById(EntityManager entityManager, int collegeId) {
		if (collegeId > 0) {
			dao.findCollegeById(entityManager, collegeId);
		} else {
			System.out.println("Invalid College Id");
		}
	}

	// FIND UNIVERSITY BY ID

	public void findUniversityById(EntityManager entityManager, int universityId) {
		if (universityId > 0) {
			dao.findUniversityById(entityManager, universityId);
		} else {
			System.out.println("Invalid University Id");
		}
	}

	// FIND UNIVERSITY BY COLLEGE ID

	public void findUniversityByCollegeId(EntityManager entityManager, int collegeId) {
		if (collegeId > 0) {
			dao.findUniversityByCollegeId(entityManager, collegeId);
		} else {
			System.out.println("Invalid College Id");
		}
	}

	// UPDATE COLLEGE NAME

	public void updateCollegeName(EntityManager entityManager, int collegeId, String newCollegeName) {

		if (collegeId > 0 && newCollegeName != null && !newCollegeName.isBlank()) {
			dao.updateCollegeName(entityManager, collegeId, newCollegeName);
		} else {
			System.out.println("Invalid Input");
		}
	}

	// UPDATE PRINCIPAL NAME

	public void updatePrincipalName(EntityManager entityManager, int collegeId, String principalName) {

		if (collegeId > 0 && principalName != null && !principalName.isBlank()) {
			dao.updatePrincipalName(entityManager, collegeId, principalName);
		} else {
			System.out.println("Invalid Input");
		}
	}

	// UPDATE UNIVERSITY NAME

	public void updateUniversityName(EntityManager entityManager, int universityId, String universityName) {

		if (universityId > 0 && universityName != null && !universityName.isBlank()) {
			dao.updateUniversityName(entityManager, universityId, universityName);
		} else {
			System.out.println("Invalid Input");
		}
	}

	// CHANGE UNIVERSITY OF COLLEGE

	public void changeUniversity(EntityManager entityManager, int collegeId, int universityId) {

		if (collegeId > 0 && universityId > 0) {
			dao.changeUniversity(entityManager, collegeId, universityId);
		} else {
			System.out.println("Invalid Id");
		}
	}

	// DELETE COLLEGE

	public void deleteCollege(EntityManager entityManager, int collegeId) {

		if (collegeId > 0) {
			dao.deleteCollege(entityManager, collegeId);
		} else {
			System.out.println("Invalid College Id");
		}
	}

	// DELETE UNIVERSITY

	public void deleteUniversity(EntityManager entityManager, int universityId) {

		if (universityId > 0) {
			dao.deleteUniversity(entityManager, universityId);
		} else {
			System.out.println("Invalid University Id");
		}
	}

	// FIND ALL COLLEGES

	public void findAllColleges(EntityManager entityManager) {
		dao.findAllColleges(entityManager);
	}

	// FIND ALL UNIVERSITIES

	public void findAllUniversities(EntityManager entityManager) {
		dao.findAllUniversities(entityManager);
	}
}