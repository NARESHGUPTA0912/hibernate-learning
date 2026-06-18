package com.teknifity.unidirectional_hibernate_mapping.many_to_one.dao;

import java.util.List;

import com.teknifity.unidirectional_hibernate_mapping.many_to_one.entity.College;
import com.teknifity.unidirectional_hibernate_mapping.many_to_one.entity.University;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UniversityCollegeDao {

	// SAVE

	public void saveUniversity(EntityManager entityManager, University university) {

		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();

			entityManager.persist(university);

			transaction.commit();

			System.out.println("University Saved Successfully");

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	public void saveCollege(EntityManager entityManager, College college, int universityId) {

		EntityTransaction transaction = entityManager.getTransaction();

		try {

			transaction.begin();

			University university = entityManager.find(University.class, universityId);

			if (university != null) {

				college.setUniversity(university);

				entityManager.persist(college);

				System.out.println("College Saved Successfully");

			} else {

				System.out.println("University Not Found");
			}

			transaction.commit();

		} catch (Exception e) {

			transaction.rollback();
			e.printStackTrace();
		}
	}

	// FIND COLLEGE BY ID

	public void findCollegeById(EntityManager entityManager, int collegeId) {

		College college = entityManager.find(College.class, collegeId);

		if (college != null) {
			System.out.println(college);
		} else {
			System.out.println("College Not Found");
		}
	}

	// FIND UNIVERSITY BY ID

	public void findUniversityById(EntityManager entityManager, int universityId) {

		University university = entityManager.find(University.class, universityId);

		if (university != null) {
			System.out.println(university);
		} else {
			System.out.println("University Not Found");
		}
	}

	// FIND UNIVERSITY USING COLLEGE ID

	public void findUniversityByCollegeId(EntityManager entityManager, int collegeId) {

		College college = entityManager.find(College.class, collegeId);

		if (college != null) {
			System.out.println(college.getUniversity());
		} else {
			System.out.println("College Not Found");
		}
	}

	// UPDATE COLLEGE NAME

	public void updateCollegeName(EntityManager entityManager, int collegeId, String newCollegeName) {

		EntityTransaction transaction = entityManager.getTransaction();

		try {

			transaction.begin();

			College college = entityManager.find(College.class, collegeId);

			if (college != null) {

				college.setCollegeName(newCollegeName);

				System.out.println("College Updated Successfully");

			} else {
				System.out.println("College Not Found");
			}

			transaction.commit();

		} catch (Exception e) {

			transaction.rollback();
			e.printStackTrace();
		}
	}

	// UPDATE PRINCIPAL NAME

	public void updatePrincipalName(EntityManager entityManager, int collegeId, String principalName) {

		EntityTransaction transaction = entityManager.getTransaction();

		try {

			transaction.begin();

			College college = entityManager.find(College.class, collegeId);

			if (college != null) {

				college.setPrincipalName(principalName);

				System.out.println("Principal Updated Successfully");

			} else {
				System.out.println("College Not Found");
			}

			transaction.commit();

		} catch (Exception e) {

			transaction.rollback();
			e.printStackTrace();
		}
	}

	// UPDATE UNIVERSITY NAME

	public void updateUniversityName(EntityManager entityManager, int universityId, String universityName) {

		EntityTransaction transaction = entityManager.getTransaction();

		try {

			transaction.begin();

			University university = entityManager.find(University.class, universityId);

			if (university != null) {

				university.setUniversityName(universityName);

				System.out.println("University Updated Successfully");

			} else {
				System.out.println("University Not Found");
			}

			transaction.commit();

		} catch (Exception e) {

			transaction.rollback();
			e.printStackTrace();
		}
	}

	// CHANGE UNIVERSITY OF COLLEGE

	public void changeUniversity(EntityManager entityManager, int collegeId, int universityId) {

		EntityTransaction transaction = entityManager.getTransaction();

		try {

			transaction.begin();

			College college = entityManager.find(College.class, collegeId);
			University university = entityManager.find(University.class, universityId);

			if (college != null && university != null) {

				college.setUniversity(university);

				System.out.println("University Changed Successfully");

			} else {
				System.out.println("College or University Not Found");
			}

			transaction.commit();

		} catch (Exception e) {

			transaction.rollback();
			e.printStackTrace();
		}
	}

	// DELETE COLLEGE

	public void deleteCollege(EntityManager entityManager, int collegeId) {

		EntityTransaction transaction = entityManager.getTransaction();

		try {

			transaction.begin();

			College college = entityManager.find(College.class, collegeId);

			if (college != null) {

				entityManager.remove(college);

				System.out.println("College Deleted Successfully");

			} else {
				System.out.println("College Not Found");
			}

			transaction.commit();

		} catch (Exception e) {

			transaction.rollback();
			e.printStackTrace();
		}
	}

	// DELETE UNIVERSITY

	public void deleteUniversity(EntityManager entityManager, int universityId) {

		EntityTransaction transaction = entityManager.getTransaction();

		try {

			transaction.begin();

			University university = entityManager.find(University.class, universityId);

			if (university != null) {

				entityManager.remove(university);

				System.out.println("University Deleted Successfully");

			} else {
				System.out.println("University Not Found");
			}

			transaction.commit();

		} catch (Exception e) {

			transaction.rollback();
			e.printStackTrace();

			System.out.println("Delete failed because some colleges may still reference this university.");
		}
	}

	// FIND ALL COLLEGES

	public void findAllColleges(EntityManager entityManager) {

		List<College> colleges = entityManager.createQuery("FROM College", College.class).getResultList();

		colleges.forEach(System.out::println);
	}

	// FIND ALL UNIVERSITIES

	public void findAllUniversities(EntityManager entityManager) {

		List<University> universities = entityManager.createQuery("FROM University", University.class).getResultList();

		universities.forEach(System.out::println);
	}
}