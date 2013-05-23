package ar.edu.itba.it.paw.domain.restaurant;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import ar.edu.itba.it.paw.domain.AbstractModel;
import ar.edu.itba.it.paw.domain.user.User;


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
	
	@ManyToMany(cascade = CascadeType.ALL)
	Set<User> likes = new HashSet<User>();
	@ManyToMany(cascade = CascadeType.ALL)
	Set<User> unlikes = new HashSet<User>();
	

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
	
	@Override
	public int hashCode() {
		return user.hashCode() + restaurant.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		if (restaurant != other.restaurant || user != other.user)
			return false;
		return true;
	}
}
