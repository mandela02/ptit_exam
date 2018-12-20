package com.chidm.ptit_exam.model;

public class Exam {
	private String idExam;
	private String time;
	private String state;
	Student_Group student_Group;
	Supervisor supervisor;
	Room room;

	public Exam(String idExam, String time, String state, Student_Group student_Group, Supervisor supervisor, Room room) {
		this.idExam = idExam;
		this.time = time;
		this.state = state;
		this.student_Group = student_Group;
		this.supervisor = supervisor;
		this.room = room;
	}

    public String getIdExam() {
        return idExam;
    }

    public void setIdExam(String idExam) {
        this.idExam = idExam;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Student_Group getStudent_Group() {
        return student_Group;
    }

    public void setStudent_Group(Student_Group student_Group) {
        this.student_Group = student_Group;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}