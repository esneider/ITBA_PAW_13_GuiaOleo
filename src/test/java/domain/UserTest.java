package domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;
import ar.edu.itba.it.paw.domain.picture.Picture;
import ar.edu.itba.it.paw.domain.restaurant.Rating;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantState;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.domain.user.UserType;
import ar.edu.itba.it.paw.utils.Utils;


public class UserTest {

	class MockUserRepo implements UserRepo {
		@Override
		public User get(int id) {return null;}
		@Override
		public boolean emailExists(String email) {return false;}
		@Override
		public boolean usernameExists(String username) {return false;}
		@Override
		public User login(String username, String password) {return null;}
		@Override
		public void save(User user) {}
		@Override
		public List<User> getAll() {return null;}
		@Override
		public User get(String username) {return null;}
	}

	class MockFoodTypeRepo implements FoodTypeRepo {
		@Override
		public List<FoodType> getAll() {return null;}
		@Override
		public FoodType get(int id) {return null;}
		@Override
		public boolean foodTypeExists(String name) {return false;}
	}

	Picture picture;
	Rating rating;

	@Before
	public void init() {

		Utils utils = new Utils();

		utils.setUserRepo(new MockUserRepo());
		utils.setFoodTypeRepo(new MockFoodTypeRepo());

		picture = new Picture(new byte[1], "");

		User user = new User("a", "a", "aa@aa.aa", "a", "a", picture, new Date(), UserType.Normal);

		Set<FoodType> set = new HashSet<FoodType>();
		
		set.add(new FoodType("bla"));

		Restaurant restaurant = new Restaurant("a", "a", "a", "a", "a", "a", 1, RestaurantState.Accepted,
											   set, user, new Date());

		rating = new Rating(2, "", user, restaurant, new Date());
	}

    @Test
    public void newValidUserTest() {

        User user = new User("name", "surname", "email@email.com", "username", "password", picture, new Date(),
                             UserType.Normal);

        Assert.assertNotNull(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullNameTest() {

        @SuppressWarnings("unused")
        User user = new User(null, "surname", "email@email.com", "username", "password", picture, new Date(),
                             UserType.Normal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullSurnameTest() {

        @SuppressWarnings("unused")
        User user = new User("name", null, "email@email.com", "username", "password", picture, new Date(),
                             UserType.Normal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullEmailTest() {

        @SuppressWarnings("unused")
        User user = new User("name", "surname", null, "username", "password", picture, new Date(),
                             UserType.Normal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullUsernameTest() {

        @SuppressWarnings("unused")
        User user = new User("name", "surname", "email@email.com", null, "password", picture, new Date(),
                             UserType.Normal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullPasswordTest() {

        @SuppressWarnings("unused")
        User user = new User("name", "surname", "email@email.com", "username", null, picture, new Date(),
                             UserType.Normal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullPictureTest() {

        @SuppressWarnings("unused")
        User user = new User("name", "surname", "email@email.com", "username", "password", null, new Date(),
                             UserType.Normal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullDateTest() {

        @SuppressWarnings("unused")
		User user = new User("name", "surname", "email@email.com", null, "password", picture, null,
                             UserType.Normal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyNameTest() {

        @SuppressWarnings("unused")
        User user = new User("", "surname", "email@email.com", "username", "password", picture, new Date(),
                             UserType.Normal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptySurnameTest() {

        @SuppressWarnings("unused")
        User user = new User("name", "", "email@email.com", "username", "password", picture, new Date(),
                             UserType.Normal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyEmailTest() {

        @SuppressWarnings("unused")
        User user = new User("name", "surname", "", "username", "password", picture, new Date(),
                             UserType.Normal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyUsernameTest() {

        @SuppressWarnings("unused")
        User user = new User("name", "surname", "email@email.com", "", "password", picture, new Date(),
                             UserType.Normal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyPasswordTest() {

        @SuppressWarnings("unused")
        User user = new User("name", "surname", "email@email.com", "username", "", picture, new Date(),
                             UserType.Normal);
    }

    @Test
    public void isAdministratorTest() {

        User user = new User("name", "surname", "email@email.com", "username", "password", picture, new Date(),
                             UserType.Admin);

        Assert.assertTrue(user.isAdmin());
    }

    @Test
    public void isNotAdministratorTest() {

        User user = new User("name", "surname", "email@email.com", "username", "password", picture, new Date(),
                             UserType.Normal);

        Assert.assertFalse(user.isAdmin());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidEmailTest() {

        @SuppressWarnings("unused")
		User user = new User("name", "surname", "berhsdfbdhj", "username", "password", picture, new Date(),
                             UserType.Normal);
    }

    @Test
    public void likesTest() {

        User user = new User("name", "surname", "email@email.com", "username", "password", picture, new Date(),
                             UserType.Normal);

        Assert.assertEquals(user.getLikes().size(), 0);
        
        user.like(rating);
        
        Assert.assertEquals(user.getLikes().size(), 1);

        user.like(rating);
        
        Assert.assertEquals(user.getLikes().size(), 1);

        user.unlike(rating);

        Assert.assertEquals(user.getLikes().size(), 0);

        user.unlike(rating);

        Assert.assertEquals(user.getLikes().size(), 0);
    }
}

