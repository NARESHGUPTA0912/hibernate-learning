package com.jspider.hibernate_simple_crud_operation_project.controller;

import com.jspider.hibernate_simple_crud_operation_project.dao.EmployeeDao;

public class DeleteEmployeeByNameDao {

	public static void main(String[] args) {
		
		boolean b = new EmployeeDao().deleteEmployeeByNameDao("Keshav Maharaj");
		String msg = b?"Data Deleted Successfully":"Something went wrong";
		System.out.println(msg);
	}

}
