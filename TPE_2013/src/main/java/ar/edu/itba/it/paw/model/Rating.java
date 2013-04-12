package ar.edu.itba.it.paw.model;

public class Rating extends AbstractModel {

	private int score;
	private String comment;
	private User user;
	private Restaurant restaurant;

	public Rating(int id, int score, String comment, User user, Restaurant restaurant) {
		super(id);
		this.score = score;
		this.comment = comment;
		this.user = user;
		this.restaurant = restaurant;
	}
	
	public int getScore() {
		return score;
	}

	public String getComment() {
		return comment;
	}

	public User getUser() {
		return user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public Rating(int score, String comment, User user, Restaurant restaurant) {
		super(-1);
		this.score = score;
		this.comment = comment;
		this.user = user;
		this.restaurant = restaurant;
	}

}
