package ar.edu.itba.it.paw.model;

public class User extends AbstractModel {

	private String name, lastname, mail, username, password;

	public User(int id, String name, String lastname, String mail,
			String username, String password) {
		super(id);
		this.name = name;
		this.lastname = lastname;
		this.mail = mail;
		this.username = username;
		this.password = password;
	}

}
