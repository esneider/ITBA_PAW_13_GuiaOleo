package ar.edu.itba.it.paw.model;

import ar.edu.itba.it.paw.manager.UserManager;

public class User extends AbstractModel {

	private String name, surname, mail, username, password;

	public User(int id, String name, String surname, String mail,
			String username, String password) {
		super(id);
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.username = username;
		this.password = password;
	}
	
	public User(String name, String surname, String mail,
			String username, String password) {
		super(-1);
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getMail() {
		return mail;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void update(String name, String surname, String mail, String password) {

		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.password = password;

		UserManager.getInstance().update(this);
	}
	
}
