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

public class RestaurantTest {

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
		@Override
		public User getByToken(String token) {
			return null;
		}
	}

	class MockFoodTypeRepo implements FoodTypeRepo {
		@Override
		public List<FoodType> getAll() {return null;}
		@Override
		public FoodType get(int id) {return null;}
		@Override
		public boolean foodTypeExists(String name) {return false;}
	}

	Rating rating;
	User user;
	
	Set<FoodType> foodTypeSet;

	@Before
	public void init() {



		Picture picture = new Picture(new byte[1], "");

		user = new User("a", "a", "aa@aa.aa", "a", "a", picture, new Date(), UserType.Normal);

		foodTypeSet = new HashSet<FoodType>();
		foodTypeSet.add(new FoodType("bla"));

		Restaurant restaurant = new Restaurant("a", "a", "a", "a", "a", "a", 1, RestaurantState.Accepted,
				foodTypeSet, user, new Date());

		rating = new Rating(2, "", user, restaurant, new Date());
	}

	 @Test
	    public void newValidRestaurantTest() {

	        Restaurant rest = new Restaurant("name", "address", "area", "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
	                             foodTypeSet, user, new Date());

	        Assert.assertNotNull(rest);
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void emptyNameTest() {

	        @SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("", "address", "area", "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void emptyAddressTest() {

	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "", "area", "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void emptyAreaTest() {

	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "", "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void emptyTelephoneTest() {

	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "area", "", "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void emptyWebsiteTest() {

	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "area", "telephone", "", "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void emptyTimeRangeTest() {

	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "area", "telephone", "website", "", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void negativeValueTest() {

	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "area", "telephone", "website", "timerange", (float)-1.0, null,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void emptySetTest() {

	        @SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "area", "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    new HashSet<FoodType>(), user, new Date());
	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void nullNameTest() {

	        @SuppressWarnings("unused")
	        Restaurant rest = new Restaurant(null, "address", "area", "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void nullAddressTest() {

	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", null, "area", "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void nullAreaTest() {

	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", null, "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void nullTelephoneTest() {

	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "area", null, "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void nullWebsiteTest() {

	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "area", "telephone", null, "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void nullTimeRangeTest() {

	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "area", "telephone", "website", null, (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void nullStateTest() {

	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "area", "telephone", "website", "timerange", (float)1.0, null,
                    foodTypeSet, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void nullSetTest() {

	        @SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "area", "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    null, user, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void nullUserTest() {

	        @SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "area", "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, null, new Date());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void nullDateTest() {
	    	@SuppressWarnings("unused")
	        Restaurant rest = new Restaurant("name", "address", "area", "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, null);
	    }



	    @Test
	    public void addRatingTest() {

	        Restaurant rest = new Restaurant("name", "address", "area", "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
                    foodTypeSet, user, new Date());

	        rest.addRating(rating);
	        Assert.assertEquals(rest.getRatings().size(),1);
	    }

	    @Test
	    public void addRemoveRatingTest() {

	    	 Restaurant rest = new Restaurant("name", "address", "area", "telephone", "website", "timerange", (float)1.0, RestaurantState.Accepted,
	                    foodTypeSet, user, new Date());

		        rest.addRating(rating);
		        rest.removeRating(rating);
		        Assert.assertTrue(rest.getRatings().isEmpty());
	    }

	
}
