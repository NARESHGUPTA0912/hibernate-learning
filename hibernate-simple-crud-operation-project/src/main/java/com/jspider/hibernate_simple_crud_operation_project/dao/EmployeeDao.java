package com.jspider.hibernate_simple_crud_operation_project.dao;

import java.util.List;

import com.jspider.hibernate_simple_crud_operation_project.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class EmployeeDao {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Employee saveEmployeeDao(Employee emp) {
		et.begin();
		em.persist(emp);
		et.commit();
		return emp;
	}
	
	public Employee getEmployeeByIdDao(int empId) {
		Employee emp = em.find(Employee.class, empId);
		return emp;
	}
	
	public boolean deleteEmployeeByIdDao(int empId) {
		Employee emp = em.find(Employee.class, empId);
		if(emp!=null) {
			et.begin();
			em.remove(emp);
			et.commit();
			return true;
		}
		return false;
	}
	
	public boolean updateEmployeeByIdDao(int empId, Employee update) {
		Employee emp = em.find(Employee.class, empId);
		if(emp!=null) {
			et.begin();
			emp.setEmail(update.getEmail());
			emp.setDob(update.getDob());
			emp.setName(update.getName());
			em.merge(emp);
			et.commit();
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployeeDao(){
		// first way
//		String displayQuery = "from Employee";
//		Query query = em.createQuery(displayQuery);
//		return query.getResultList();
		
		//second way
		return em.createQuery("from Employee").getResultList();
	}
	
	public Employee getEmployeeByEmailDao(String email) {
		String displayEmpByEmailQuery = "SELECT e FROM Employee e where e.email=?1";
		
		Query query = em.createQuery(displayEmpByEmailQuery);
		query.setParameter(1, email);
		
		try {
			Employee emp = (Employee) query.getSingleResult();
			return emp;
		} catch (NoResultException | NonUniqueResultException | IllegalStateException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeByNameDao(String name){
		
		String displayEmpByNameQuery = "select * from employee where name = ?1";
		Query query = em.createNativeQuery(displayEmpByNameQuery, Employee.class);
		query.setParameter(1,name);
		return query.getResultList();
	}
	
	public boolean deleteEmployeeByNameDao(String name){
		
		String deleteEmpByNameQuery = "delete from employee where name = ?1";
		Query query = em.createNativeQuery(deleteEmpByNameQuery, Employee.class);
		query.setParameter(1,name);
		et.begin();
		int a = query.executeUpdate();
		et.commit();
		return a!=0?true:false;
	}
}
