package ar.edu.itba.it.paw.model;

import java.io.InputStream;

import ar.edu.itba.it.paw.manager.UserManager;

public class User extends AbstractModel {

	private String name, surname, email, username, password;
	private InputStream avatar;

	public User(int id, String name, String surname, String email,
			String username, String password) {
		this(id, name, surname, email, username, password, null);
	}
	
	public User(int id, String name, String surname, String email,
			String username, String password, InputStream avatar) {	
		super(id);
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.avatar = avatar;
	}
	
	public User(String name, String surname, String email,
			String username, String password) {
		this(-1, name, surname, email, username, password, null);
	}
	
	public User(String name, String surname, String email,
			String username, String password, InputStream is) {
		this(-1, name, surname, email, username, password, is);
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
	
	public InputStream getAvatar() {
		return avatar;
	}

	public void update(String name, String surname, String email, String password) {

		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;

		UserManager.getInstance().update(this);
	}
	
}
