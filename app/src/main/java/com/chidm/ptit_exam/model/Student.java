package com.chidm.ptit_exam.model;

import com.chidm.ptit_exam.model.MajorClass;
import com.chidm.ptit_exam.model.Person;

import java.util.*;

public class Student extends Person {

	private Collection<Student_Group> student_Groups;
	private String idStudent;
	private String schoolYear;
	private MajorClass majorClass;
	private String trainingGroupID;
	private String trainingPersonID; // Id của api trả về là id của 1 người
	private List<String> listFaceID; // list id của ảnh. 1 người có nhiều ảnh

	public Student(String idPerson, String name, String address, Account account, Collection<Student_Group> student_Groups, String idStudent, String schoolYear, MajorClass majorClass) {
		super(idPerson, name, address, account);
		this.student_Groups = student_Groups;
		this.idStudent = idStudent;
		this.schoolYear = schoolYear;
		this.majorClass = majorClass;
	}

	public Collection<Student_Group> getStudent_Groups() {
		return student_Groups;
	}

	public void setStudent_Groups(Collection<Student_Group> student_Groups) {
		this.student_Groups = student_Groups;
	}

	public String getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(String idStudent) {
		this.idStudent = idStudent;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public MajorClass getMajorClass() {
		return majorClass;
	}

	public void setMajorClass(MajorClass majorClass) {
		this.majorClass = majorClass;
	}
}