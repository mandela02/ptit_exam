package com.chidm.ptit_exam.model;

import com.chidm.ptit_exam.model.Person;

import java.util.*;

public class Supervisor extends Person {

	private Collection<Exam> exams;
	private String idSupervisor;
	private String major;

	public Supervisor(String idPerson, String name, String address, Account account, Collection<Exam> exams, String idSupervisor, String major) {
		super(idPerson, name, address, account);
		this.exams = exams;
		this.idSupervisor = idSupervisor;
		this.major = major;
	}

	public Collection<Exam> getExams() {
		return exams;
	}

	public void setExams(Collection<Exam> exams) {
		this.exams = exams;
	}

	public String getIdSupervisor() {
		return idSupervisor;
	}

	public void setIdSupervisor(String idSupervisor) {
		this.idSupervisor = idSupervisor;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
}