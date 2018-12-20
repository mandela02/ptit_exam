package com.chidm.ptit_exam.model;

import com.chidm.ptit_exam.model.Student_Group;
import com.chidm.ptit_exam.model.Teacher;

import javax.security.auth.Subject;
import java.util.*;

public class Group {

	private Collection<Student_Group> student_Groups;
	private Teacher teacher;
	private String idGroup;
	private int numberOfStudent;
	private Subject subject;

	public Group(Collection<Student_Group> student_Groups, Teacher teacher, String idGroup, int numberOfStudent, Subject subject) {
		this.student_Groups = student_Groups;
		this.teacher = teacher;
		this.idGroup = idGroup;
		this.numberOfStudent = numberOfStudent;
		this.subject = subject;
	}

	public Collection<Student_Group> getStudent_Groups() {
		return student_Groups;
	}

	public void setStudent_Groups(Collection<Student_Group> student_Groups) {
		this.student_Groups = student_Groups;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}

	public int getNumberOfStudent() {
		return numberOfStudent;
	}

	public void setNumberOfStudent(int numberOfStudent) {
		this.numberOfStudent = numberOfStudent;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}