package com.chidm.ptit_exam.model;

import com.chidm.ptit_exam.model.Person;

import java.security.acl.Group;
import java.util.*;

public class Teacher extends Person {

	private Collection<MajorClass> majorClasses;
	private Collection<Group> groups;
	private String idTeacher;
	private String position;
	private Faculty faculty;

	public Teacher(String idPerson, String name, String address, Account account, Collection<MajorClass> majorClasses, Collection<Group> groups, String idTeacher, String position, Faculty faculty) {
		super(idPerson, name, address, account);
		this.majorClasses = majorClasses;
		this.groups = groups;
		this.idTeacher = idTeacher;
		this.position = position;
		this.faculty = faculty;
	}

	public Collection<MajorClass> getMajorClasses() {
		return majorClasses;
	}

	public void setMajorClasses(Collection<MajorClass> majorClasses) {
		this.majorClasses = majorClasses;
	}

	public Collection<Group> getGroups() {
		return groups;
	}

	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}

	public String getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(String idTeacher) {
		this.idTeacher = idTeacher;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
}