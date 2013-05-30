package domain;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.it.paw.domain.picture.Picture;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserType;

public class UserTest {

	@Test
	public void newValidUserTest() {

		Picture picture = new Picture(new byte[1], "");
		User user = new User("name", "surname", "email@email.com", "username", "password", picture, new Date(),
				             UserType.Normal);

		Assert.assertNotNull(user);
	}

	// EJEMPLOS DE MAS TESTS
	
	/*
	 * @Test(expected = Exception.class) public void nullUsernameTest() { new
	 * User("name", "surname", "email@email.com", null, "password", new Date(),
	 * "type"); }
	 * 
	 * @Before public void setUser(){ this.user = new User("name", "surname",
	 * "email", "username", "password"); this.rest = new Restaurant("name",
	 * "address", "neighbourhood", "phone", "webAddress", 20.0, "timetable"); }
	 * 
	 * @Test public void newValidUserTest(){ new User("name", "surname",
	 * "email", "username", "password"); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * nullUsernameTest(){ new User("name", "surname", "email", null,
	 * "password"); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * emptyUsernameTest(){ new User("name", "surname", "email", "",
	 * "password"); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * nullNameTest(){ new User(null, "surname", "email", "username",
	 * "password"); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * emptyNameTest(){ new User("", "surname", "email", "username",
	 * "password"); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * nullSurnameTest(){ new User("name", null, "email", "username",
	 * "password"); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * emptySurnameTest(){ new User("name", "", "email", "username",
	 * "password"); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * nullEmailTest(){ new User("name", "surname", null, "username",
	 * "password"); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * emptyEmailTest(){ new User("name", "surname", "", "username",
	 * "password"); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * nullPasswordTest(){ new User("name", "surname", "email", "username",
	 * null); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * emptyPasswordTest(){ new User("name", "surname", "email", "username",
	 * ""); }
	 * 
	 * @Test public void isAdministratorTest(){ User u = new User("name",
	 * "surname", "email", "username", "password", 1);
	 * Assert.assertTrue(u.isAdministrator());
	 * Assert.assertFalse(u.isRegularUser()); }
	 * 
	 * @Test public void isRegularTest(){ User u = new User("name", "surname",
	 * "email", "username", "password", 0);
	 * Assert.assertFalse(u.isAdministrator());
	 * Assert.assertTrue(u.isRegularUser()); }
	 * 
	 * @Test public void toAdministratorTest(){
	 * Assert.assertTrue(user.isRegularUser()); user.toAdministrator();
	 * Assert.assertTrue(user.isAdministrator());
	 * Assert.assertFalse(user.isRegularUser()); }
	 * 
	 * @Test public void toRegularUserTest(){ user.toAdministrator();
	 * Assert.assertTrue(user.isAdministrator()); user.toRegularUser();
	 * Assert.assertTrue(user.isRegularUser());
	 * Assert.assertFalse(user.isAdministrator()); }
	 * 
	 * @Test public void addFavouritesTest(){ user.addFavourite(rest);
	 * Assert.assertTrue(user.getFavourites().size() == 1); }
	 * 
	 * @Test public void noFavouritesTest(){
	 * Assert.assertTrue(user.getFavourites().size() == 0); }
	 * 
	 * @Test public void reAddFavouritesTest(){ user.addFavourite(rest);
	 * Assert.assertTrue(user.getFavourites().size() == 1);
	 * user.addFavourite(rest); Assert.assertTrue(user.getFavourites().size() ==
	 * 1); }
	 * 
	 * @Test public void removeFavouritesTest(){ user.addFavourite(rest);
	 * Assert.assertTrue(user.getFavourites().size() == 1);
	 * user.removeFavourite(rest); Assert.assertTrue(user.getFavourites().size()
	 * == 0); }
	 */
}
