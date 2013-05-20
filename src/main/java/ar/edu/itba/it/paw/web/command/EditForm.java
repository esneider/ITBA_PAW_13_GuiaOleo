package ar.edu.itba.it.paw.web.command;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ar.edu.itba.it.paw.model.Picture;
import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.service.interfaces.UserService;

@Component
public class EditForm {

	private String name, surname, email, password, oldPassword, repassword;
	private CommonsMultipartFile avatar;
	private User user; 
	private int userId;
	
	public EditForm(User user, String repassword, String oldPassword) {
		this.user = user;
		this.setName(user.getName());
		this.setSurname(user.getSurname());
		this.setEmail(user.getEmail());
		this.setAvatar(null);
		this.setRepassword(repassword); 
		this.setOldPassword(oldPassword);
		this.setUserId(user.getId());
	}
	
	EditForm(){
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public User build(UserService userService) {
		Picture pic = null;
		if (avatar != null && !avatar.isEmpty()) {
			try {
				pic = new Picture(avatar.getInputStream(), avatar.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (user == null)
			user = userService.getSingleUser(getUserId());
	
		if (pic != null)
			user.setAvatar(pic);
		if (!email.equals(""))
			user.setEmail(email);
		if (!password.equals(""))
			user.setPassword(password);
		if (!surname.equals(""))
			user.setSurname(surname);
		if (!name.equals(""))
			user.setName(name);
		return user;
	}
	
}
