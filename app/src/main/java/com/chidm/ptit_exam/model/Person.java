package com.chidm.ptit_exam.model;

public class Person {

	private String idPerson;
	private String name;
	private String address;
	private Account account;

	public Person(String idPerson, String name, String address, Account account) {
		this.idPerson = idPerson;
		this.name = name;
		this.address = address;
		this.account = account;
	}

	public String getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(String idPerson) {
		this.idPerson = idPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}