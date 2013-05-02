package ar.edu.itba.it.paw.model;

public class User extends AbstractModel {

	private String name, surname, email, username, password;
	private Picture avatar;

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
	
	public User(int id, String name, String surname, String email,
			String username, String password) {

		this(id, name, surname, email, username, password, null);
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
}
