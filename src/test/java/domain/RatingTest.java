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


public class RatingTest {

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

	User user;
	Restaurant restaurant;

	@Before
	public void init() {

		Utils utils = new Utils();


		Picture picture = new Picture(new byte[1], "");

		user = new User("a", "a", "aa@aa.aa", "a", "a", picture, new Date(), UserType.Normal);

		Set<FoodType> set = new HashSet<FoodType>();
		
		set.add(new FoodType("bla"));

		restaurant = new Restaurant("a", "a", "a", "a", "a", "a", 1, RestaurantState.Accepted, set, user, new Date());

	}

    @Test
    public void newValidRatingTest() {

		Rating rating = new Rating(2, "", user, restaurant, new Date());

        Assert.assertNotNull(rating);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidScoreTest() {

		@SuppressWarnings("unused")
		Rating rating = new Rating(-1, "", user, restaurant, new Date());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullUserTest() {

		@SuppressWarnings("unused")
		Rating rating = new Rating(2, "", null, restaurant, new Date());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullRestaurantTest() {

		@SuppressWarnings("unused")
		Rating rating = new Rating(2, "", user, null, new Date());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullDateTest() {

		@SuppressWarnings("unused")
		Rating rating = new Rating(2, "", user, restaurant, null);
    }

    @Test
    public void likesTest() {

		Rating rating = new Rating(2, "", user, restaurant, new Date());

        Assert.assertEquals(rating.getLikes().size(), 0);
        
        rating.like(user);
        
        Assert.assertEquals(rating.getLikes().size(), 1);

        rating.like(user);
        
        Assert.assertEquals(rating.getLikes().size(), 1);

        rating.unlike(user);

        Assert.assertEquals(rating.getLikes().size(), 0);

        rating.unlike(user);

        Assert.assertEquals(rating.getLikes().size(), 0);
    }
}

