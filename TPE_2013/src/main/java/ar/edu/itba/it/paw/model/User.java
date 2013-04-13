package ar.edu.itba.it.paw.model;

public class User extends AbstractModel {

	private String name, surname, mail, username, password;

	public User(int id, String name, String surname, String mail,
			String username, String password) {
		super(id);
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.username = username;
		this.password = password;
	}

	public User(String name, String surname, String mail,
			String username, String password) {
		super(-1);
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getMail() {
		return mail;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	
}
