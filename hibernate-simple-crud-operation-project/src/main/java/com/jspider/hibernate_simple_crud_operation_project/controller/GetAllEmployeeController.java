package com.jspider.hibernate_simple_crud_operation_project.controller;

import java.util.List;

import com.jspider.hibernate_simple_crud_operation_project.dao.EmployeeDao;
import com.jspider.hibernate_simple_crud_operation_project.entity.Employee;


public class GetAllEmployeeController {

	public static void main(String[] args) {
		List<Employee> employees = new EmployeeDao().getAllEmployeeDao();
		for(Employee emp: employees) {
			System.out.println(emp);
		}

	}

}
