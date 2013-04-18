package ar.edu.itba.it.paw.model;

import ar.edu.itba.it.paw.manager.UserManager;

public class User extends AbstractModel {

	private String name, surname, email, username, password;
	private Picture avatar;

	public User(int id, String name, String surname, String email,
			String username, String password) {
		this(id, name, surname, email, username, password, null);
	}
	
	public User(int id, String name, String surname, String email,
			String username, String password, Picture avatar) {	
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
		this(NO_ID, name, surname, email, username, password, null);
	}
	
	public User(String name, String surname, String email,
			String username, String password, Picture avatar) {
		this(NO_ID, name, surname, email, username, password, avatar);
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
	
	public Picture getAvatar() {
		return avatar;
	}

	public void update(String name, String surname, String email, String password, Picture avatar) {

		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;

		if (avatar != null) {
			this.avatar = avatar;
		}

		UserManager.getInstance().update(this);
	}
	
}
