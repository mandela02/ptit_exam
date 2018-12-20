package com.chidm.ptit_exam.model;

import com.chidm.ptit_exam.model.Teacher;

import java.util.*;

public class MajorClass {

	private Collection<Student> students;
	private String idClass;
	private int numberOfStudent;
	private Teacher teacher;

	public MajorClass(Collection<Student> students, String idClass, int numberOfStudent, Teacher teacher) {
		this.students = students;
		this.idClass = idClass;
		this.numberOfStudent = numberOfStudent;
		this.teacher = teacher;
	}

	public Collection<Student> getStudents() {
		return students;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}

	public String getIdClass() {
		return idClass;
	}

	public void setIdClass(String idClass) {
		this.idClass = idClass;
	}

	public int getNumberOfStudent() {
		return numberOfStudent;
	}

	public void setNumberOfStudent(int numberOfStudent) {
		this.numberOfStudent = numberOfStudent;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}