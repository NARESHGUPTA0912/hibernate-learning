package com.jspider.hibernate_simple_crud_operation_project.controller;

import com.jspider.hibernate_simple_crud_operation_project.dao.EmployeeDao;
import com.jspider.hibernate_simple_crud_operation_project.entity.Employee;

public class GetEmployeeByIdController {

	public static void main(String[] args) {
		
		Employee employee=new EmployeeDao().getEmployeeByIdDao(6239);
		
		if(employee!=null) {
			System.out.println(employee);
		}else {
			System.out.println("given id is not found!!!");
		}

	}

}
