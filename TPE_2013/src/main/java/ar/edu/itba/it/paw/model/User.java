package ar.edu.itba.it.paw.model;

import java.io.InputStream;

import ar.edu.itba.it.paw.manager.UserManager;

public class User extends AbstractModel {

	private String name, surname, email, username, password;
	private InputStream avatar;
	private int avatarLength;

	public User(int id, String name, String surname, String email,
			String username, String password) {
		this(id, name, surname, email, username, password, null, 0);
	}
	
	public User(int id, String name, String surname, String email,
			String username, String password, InputStream avatar, int avatarLength) {	
		super(id);
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.avatar = avatar;
		this.avatarLength = avatarLength;
	}
	
	public User(String name, String surname, String email,
			String username, String password) {
		this(-1, name, surname, email, username, password, null, 0);
	}
	
	public User(String name, String surname, String email,
			String username, String password, InputStream is, int avatarLength) {
		this(-1, name, surname, email, username, password, is, avatarLength);
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
	
	public int getAvatarLength() {
		return avatarLength;
	}

	public void update(String name, String surname, String email, String password) {

		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;

		UserManager.getInstance().update(this);
	}
	
}
