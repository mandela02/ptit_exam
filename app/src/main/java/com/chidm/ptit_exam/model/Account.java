package com.chidm.ptit_exam.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class Account {

	private Person person;
	private String username;
	private String password;

	public Account(Person person, String username, String password) {
		this.person = person;
		this.username = username;
		this.password = password;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Account)) return false;
		Account account = (Account) o;
		return Objects.equals(username, account.username);
	}

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	@Override
	public int hashCode() {
		return Objects.hash(username);
	}
}