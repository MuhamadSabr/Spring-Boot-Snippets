package com.mmd.thymeleafdemo.model;

import java.util.List;

public class Student {
	private int studentId;
	private static int id=1;
	private String firstName;
	private String lastName;
	private String country;
	private String favoriteLanguage;
	private List<String> favoriteOSs;

	public Student(String firstName, String lastName) {
		studentId = id++;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Student(){
		this(null, null);
	}

	public int getStudentId(){
		return studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}

	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}

	public List<String> getFavoriteOSs() {
		return favoriteOSs;
	}

	public void setFavoriteOSs(List<String> favoriteOSs) {
		this.favoriteOSs = favoriteOSs;
	}
}
