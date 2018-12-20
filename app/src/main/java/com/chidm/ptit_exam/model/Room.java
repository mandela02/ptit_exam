package com.chidm.ptit_exam.model;

import com.chidm.ptit_exam.model.Exam;

import java.util.*;


public class Room {

	private Collection<Exam> exams;
	private String idRoom;
	private String address;

	public Room(Collection<Exam> exams, String idRoom, String address) {
		this.exams = exams;
		this.idRoom = idRoom;
		this.address = address;
	}

	public Collection<Exam> getExams() {
		return exams;
	}

	public void setExams(Collection<Exam> exams) {
		this.exams = exams;
	}

	public String getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(String idRoom) {
		this.idRoom = idRoom;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}