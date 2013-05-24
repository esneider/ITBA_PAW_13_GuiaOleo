package ar.edu.itba.it.paw.domain.user;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.edu.itba.it.paw.domain.AbstractModel;
import ar.edu.itba.it.paw.domain.picture.Picture;
import ar.edu.itba.it.paw.domain.restaurant.Rating;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;

@Entity
@Table(name="SystemUser")
public class User extends AbstractModel {

	private String name, surname, email, username, password, type;
	private Date registerDate;
	
	@OneToOne
	private Picture avatar;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Rating> comments;
	
	@OneToMany(mappedBy = "registerUser", cascade = CascadeType.ALL)
	private Set<Restaurant> registeredRestaurants;

	public User() {}

	public User(String name, String surname, String email,
			String username, String password, Picture avatar, Date date) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.avatar = avatar;
		this.registerDate = date;
	}
	
	public User(String name, String surname, String email,
			String username, String password, Date date) {

		this(name, surname, email, username, password, null, date);
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

	public Set<Restaurant> getRegisteredRestaurants() {
		return registeredRestaurants;
	}

	public String getType() {
		return type;
	}
	
	public Date getRegisterDate() {
		return registerDate;
	}
	
	public Set<Rating> getComments() {
		return comments;
	}

	public void setType(String type) {
		if (type != null)
			this.type = type;
	}

	public void setName(String name) {
		if (name != null) 
			this.name = name;
	}

	public void setSurname(String surname) {
		if (surname != null) 
			this.surname = surname;
	}

	public void setEmail(String email) {
		if (email != null) 
			this.email = email;
	}

	public void setPassword(String password) {
		if (password != null) 
			this.password = password;
	}

	public void setAvatar(Picture avatar) {
		if (avatar != null) 
			this.avatar = avatar;
	}

	public boolean isAdmin() {
		return type.equals("Admin");
	}
}