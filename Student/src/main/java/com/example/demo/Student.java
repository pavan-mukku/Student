package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "students")
public class Student {
    public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotBlank
    private String name;

    @Min(5)
    @Max(100)
    private int age;

    @NotBlank
    private String className;

    @Email
    @NotBlank
    private String email;

    private String address;

    // Getters and Setters
}

