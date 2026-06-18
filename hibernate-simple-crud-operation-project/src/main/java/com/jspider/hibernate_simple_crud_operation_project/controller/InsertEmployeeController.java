package com.jspider.hibernate_simple_crud_operation_project.controller;

import java.time.LocalDate;

import com.jspider.hibernate_simple_crud_operation_project.dao.EmployeeDao;
import com.jspider.hibernate_simple_crud_operation_project.entity.Employee;

public class InsertEmployeeController {

	public static void main(String[] args) {
		
		EmployeeDao dao = new EmployeeDao();
		Employee emp = new Employee();
		
		emp.setId(7912);
		emp.setName("Anushka Pal");
		emp.setEmail("anshupal07@gmail.com");
		emp.setPassword("i43");
		emp.setDob(LocalDate.parse("2003-02-07"));
		
	    Employee e = dao.saveEmployeeDao(emp);
	    System.out.println(e);
	    
	}

}
