package com.chidm.ptit_exam.model;

import com.chidm.ptit_exam.model.Exam;

import java.security.acl.Group;
import java.util.*;

public class Student_Group {

	private Collection<Exam> exams;
	private String idStudent_Group;
	private Group group;
	private Student student;

	public Student_Group(Collection<Exam> exams, String idStudent_Group, Group group, Student student) {
		this.exams = exams;
		this.idStudent_Group = idStudent_Group;
		this.group = group;
		this.student = student;
	}

	public Collection<Exam> getExams() {
		return exams;
	}

	public void setExams(Collection<Exam> exams) {
		this.exams = exams;
	}

	public String getIdStudent_Group() {
		return idStudent_Group;
	}

	public void setIdStudent_Group(String idStudent_Group) {
		this.idStudent_Group = idStudent_Group;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}