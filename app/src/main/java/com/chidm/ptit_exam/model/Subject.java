package com.chidm.ptit_exam.model;

import com.chidm.ptit_exam.model.Faculty;

import java.util.*;

public class Subject {

	private Collection<Group> groups;
	private String idSubject;
	private String name;
	private int numberOfCredits;
	private Faculty faculty;

	public Subject(Collection<Group> groups, String idSubject, String name, int numberOfCredits, Faculty faculty) {
		this.groups = groups;
		this.idSubject = idSubject;
		this.name = name;
		this.numberOfCredits = numberOfCredits;
		this.faculty = faculty;
	}

	public Collection<Group> getGroups() {
		return groups;
	}

	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}

	public String getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(String idSubject) {
		this.idSubject = idSubject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
}