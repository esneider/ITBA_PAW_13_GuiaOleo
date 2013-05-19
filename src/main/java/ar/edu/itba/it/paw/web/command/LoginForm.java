package ar.edu.itba.it.paw.web.command;

public class LoginForm {

	private String username, password;

	public LoginForm(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public LoginForm() {
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
	
}
