package com.chidm.ptit_exam.model;

import com.chidm.ptit_exam.model.Teacher;

import javax.security.auth.Subject;
import java.util.*;

public class Faculty {

	private Collection<Teacher> teachers;
	private Collection<Subject> subjects;
	private String idFaculty;
	private String name;

	public Faculty(Collection<Teacher> teachers, Collection<Subject> subjects, String idFaculty, String name) {
		this.teachers = teachers;
		this.subjects = subjects;
		this.idFaculty = idFaculty;
		this.name = name;
	}

	public Collection<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Collection<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Collection<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Collection<Subject> subjects) {
		this.subjects = subjects;
	}

	public String getIdFaculty() {
		return idFaculty;
	}

	public void setIdFaculty(String idFaculty) {
		this.idFaculty = idFaculty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}