package ar.edu.itba.it.paw.web.command;

import java.io.IOException;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ar.edu.itba.it.paw.model.Picture;
import ar.edu.itba.it.paw.model.User;

public class RegisterForm {

	private String name, surname, email, username, password, oldPassword;
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	private CommonsMultipartFile avatar;
	private User user; // CREAR LA CLASE USERFORM ???
	private int userId;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public int getUserId(){
		return userId;
	}
	
	public void setUserId(int userId){
		this.userId = userId;
	}

	private String repassword;

	public RegisterForm() {
	}

	public RegisterForm(User user, String repassword, String oldPassword) {
		this.user = user;
		this.setName(user.getName());
		this.setSurname(user.getSurname());
		this.setEmail(user.getEmail());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setAvatar(null);
		this.setRepassword(repassword); // TODO DESIGN SUCKS
		this.setOldPassword(oldPassword);
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

	public CommonsMultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(CommonsMultipartFile avatar) {
		this.avatar = avatar;
	}

	public User build() {
		Picture pic = null;
		if (!avatar.isEmpty()) {
			try {
				pic = new Picture(avatar.getInputStream(), avatar.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (user == null) {
			return new User(name, surname, email, username, password, pic);
		} else {
			user.setAvatar(pic);
			user.setEmail(email);
			user.setPassword(password);
			user.setSurname(surname);
			user.setName(name);
			return user;
		}
	}
}
