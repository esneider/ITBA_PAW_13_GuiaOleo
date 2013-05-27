package domain;

import java.util.Date;

import org.junit.Test;

import ar.edu.itba.it.paw.domain.user.User;

public class UserTest {
	@Test
	public void newValidUserTest() {
		new User("name", "surname", "email@email.com", "username", "password",
				new Date(), "type");
	}

	@Test(expected = Exception.class)
	public void nullUsernameTest() {
		new User("name", "surname", "email@email.com", null, "password",
				new Date(), "type");
	}
}
