package ar.edu.itba.it.paw.model;

import java.util.Date;

public class Rating extends AbstractModel {

	private int score;
	private String comment;
	private User user;
	private Restaurant restaurant;
	private Date date;
	private java.sql.Date SQLdate;

	public Rating(int id, int score, String comment, User user, Restaurant restaurant, Date date) {
		super(id);
		this.score = score;
		this.comment = comment;
		this.user = user;
		this.restaurant = restaurant;
		this.date = date;
		this.SQLdate = new java.sql.Date(date.getTime());
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
	
	public Date getDate() {
		return date;
	}
	
	public java.sql.Date getSQLDate() {
		return SQLdate;
	}

	public Rating(int score, String comment, User user, Restaurant restaurant, Date date) {
		this(-1, score, comment, user, restaurant, date);
	}

}
