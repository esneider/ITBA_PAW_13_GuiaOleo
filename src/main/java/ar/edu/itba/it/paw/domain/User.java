package ar.edu.itba.it.paw.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class User extends AbstractModel {

	private String name, surname, email, username, password;

	@OneToOne
	private Picture avatar;

	public User() {
	}

	public User(String name, String surname, String email, String username,
			String password, Picture avatar) {

		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.avatar = avatar;
	}

	public User(String name, String surname, String email, String username,
			String password) {

		this(name, surname, email, username, password, null);
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

	public void setName(String name) {

		if (name != null) {
			this.name = name;
		}
	}

	public void setSurname(String surname) {

		if (surname != null) {
			this.surname = surname;
		}
	}

	public void setEmail(String email) {

		if (email != null) {
			this.email = email;
		}
	}

	public void setPassword(String password) {

		if (password != null) {
			this.password = password;
		}
	}

	public void setAvatar(Picture avatar) {

		if (avatar != null) {
			this.avatar = avatar;
		}
	}

	public void register(User user) {
	}
	public void update(User user) {
	}
}
