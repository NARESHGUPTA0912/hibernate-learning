package com.jspider.hibernate_simple_crud_operation_project.controller;

import java.time.LocalDate;

import com.jspider.hibernate_simple_crud_operation_project.dao.EmployeeDao;
import com.jspider.hibernate_simple_crud_operation_project.entity.Employee;

public class UpdateEmployeeById {

	public static void main(String[] args) {
		EmployeeDao dao = new EmployeeDao();
		Employee emp = new Employee();
		
		emp.setName("Anushka Gupta");
		emp.setEmail("anshipal07@gmail.com");
		emp.setDob(LocalDate.parse("2002-02-07"));
		
	    Boolean res = dao.updateEmployeeByIdDao(7912, emp);
	    String r = res ? "Update Successfully" : "Something went wrong" ;
	    System.out.println(r);

	}

}
