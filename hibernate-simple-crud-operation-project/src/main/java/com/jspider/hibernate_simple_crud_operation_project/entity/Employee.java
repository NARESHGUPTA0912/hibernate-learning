package com.jspider.hibernate_simple_crud_operation_project.entity;

import java.time.LocalDate;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;

@Getter
@Entity
@Table(name = "my-employee")
public class Employee {
	
	@Id
	private int id;
	private String name;
	@Column(length = 20, unique = true, nullable = false)
	private String email;
	@Transient
	private String password;
	@Column(name = "dateofbirth")
	private LocalDate dob;
	@UpdateTimestamp
	@Column(name="Registration Date")
	private LocalDate doj;
	public int getId() { 
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", doj=" + doj + "]";
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
