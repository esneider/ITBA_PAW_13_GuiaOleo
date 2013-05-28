package domain;

import ar.edu.itba.it.paw.domain.user.UserRepo;

public class UserRepoTest {
	private UserRepo repo;

	//Ejemplos
	
	/*
	 * @Test public void emptyRepoTest(){ Assert.assertTrue(repo.getAll().size()
	 * == 0); }
	 * 
	 * @Test public void addUserTest(){ Assert.assertTrue(repo.getAll().size()
	 * == 0); User user = new User("name", "surname", "mail", "username",
	 * "password"); repo.add(user); Assert.assertTrue(repo.getAll().size() ==
	 * 1); }
	 * 
	 * @Test public void getAllUserTest(){
	 * Assert.assertTrue(repo.getAll().size() == 0); User user = new
	 * User("name", "surname", "mail", "username", "password"); repo.add(user);
	 * Assert.assertTrue(repo.getAll().size() == 1);
	 * Assert.assertTrue(repo.getAll().get(0).equals(user)); }
	 * 
	 * @Test public void getUserByUsernameTest(){
	 * Assert.assertTrue(repo.getAll().size() == 0); User user = new
	 * User("name", "surname", "mail", "username", "password"); repo.add(user);
	 * Assert.assertTrue(repo.get(user.getUsername()).equals(user)); }
	 * 
	 * @Test public void getAllAdministratorsTest(){
	 * Assert.assertTrue(repo.getAll().size() == 0); User user = new
	 * User("name", "surname", "mail", "username", "password");
	 * user.toAdministrator(); repo.add(user); User user2 = new User("name2",
	 * "surname2", "mail2", "username2", "password2"); user2.toRegularUser();
	 * repo.add(user2); Assert.assertTrue(repo.getAll().size() == 2);
	 * Assert.assertTrue(repo.getAllAdministrators().size() == 1);
	 * Assert.assertTrue(repo.getAllAdministrators().get(0).equals(user)); }
	 * 
	 * @Test public void getAllRegularUsersTest(){
	 * Assert.assertTrue(repo.getAll().size() == 0); User user = new
	 * User("name", "surname", "mail", "username", "password");
	 * user.toAdministrator(); repo.add(user); User user2 = new User("name2",
	 * "surname2", "mail2", "username2", "password2"); user2.toRegularUser();
	 * repo.add(user2); Assert.assertTrue(repo.getAll().size() == 2);
	 * Assert.assertTrue(repo.getAllRegularUsers().size() == 1);
	 * Assert.assertTrue(repo.getAllRegularUsers().get(0).equals(user2)); }
	 * 
	 * @Test(expected = DuplicatedUserException.class) public void
	 * reAddUserTest(){ Assert.assertTrue(repo.getAll().size() == 0); User user
	 * = new User("name", "surname", "mail", "username", "password");
	 * repo.add(user); repo.add(user); }
	 */
}
