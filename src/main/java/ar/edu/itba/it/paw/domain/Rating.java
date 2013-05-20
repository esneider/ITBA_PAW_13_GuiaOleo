package ar.edu.itba.it.paw.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
public class Rating extends AbstractModel {

	private Integer score;
	private String comment;
	
	@ManyToOne
	private User user;

	@ManyToOne
	private Restaurant restaurant;

	private Date date;

	@Transient
	private java.sql.Date SQLdate;

	public Rating() {}

	public Rating(int score, String comment, User user, Restaurant restaurant, Date date) {

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
}
