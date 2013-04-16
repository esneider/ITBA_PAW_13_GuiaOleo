package ar.edu.itba.it.paw.model;

import ar.edu.itba.it.paw.manager.UserManager;

public class User extends AbstractModel {

	private String name, surname, email, username, password;

	public User(int id, String name, String surname, String email,
			String username, String password) {
		
		super(id);
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public User(String name, String surname, String email,
			String username, String password) {
		
		super(-1);
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void update(String name, String surname, String email, String password) {

		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;

		UserManager.getInstance().update(this);
	}
	
}
