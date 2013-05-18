package ar.edu.itba.it.paw.web.command;

import ar.edu.itba.it.paw.model.Picture;
import ar.edu.itba.it.paw.model.User;

public class RegisterForm {

	private String name, surname, email, username, password;
	private Picture avatar;
	private User user; // CREAR LA CLASE USERFORM ???

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String repassword;

	public RegisterForm() {
	}

	public RegisterForm(User user, String repassword) {
		this.user = user;
		this.setName(user.getName());
		this.setSurname(user.getSurname());
		this.setEmail(user.getEmail());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setAvatar(user.getAvatar());
		this.setRepassword(repassword); // TODO DESIGN SUCKS

	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Picture getAvatar() {
		return avatar;
	}

	public void setAvatar(Picture avatar) {
		this.avatar = avatar;
	}

	public User build() {
		if (user == null) {
			return new User(name, surname, email, username, password);
		} else {
			user.setEmail(email);
			user.setPassword(password);
			user.setSurname(surname);
			user.setName(name);
			return user;
		}
	}
}