package ar.edu.itba.it.paw.web.command;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ar.edu.itba.it.paw.domain.picture.Picture;
import ar.edu.itba.it.paw.domain.user.User;

public class RegisterForm {

	private String name, surname, email, username, password, repassword;
	private CommonsMultipartFile avatar;
	private User user; // CREAR LA CLASE USERFORM ???
	private int userId;

	public RegisterForm() {
	}

	public RegisterForm(User user, String repassword) {
		this.user = user;
		this.setName(user.getName());
		this.setSurname(user.getSurname());
		this.setEmail(user.getEmail());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setAvatar(null);
		this.setRepassword(repassword);
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

	public User build() {
		Picture pic = null;
		if (avatar != null && !avatar.isEmpty()) 
			pic = new Picture(avatar.getBytes(), avatar.getOriginalFilename());
		if (user == null) {
			return new User(name, surname, email, username, password, pic, new Date(), "Normal");
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
