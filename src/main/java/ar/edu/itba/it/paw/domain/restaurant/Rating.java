package ar.edu.itba.it.paw.domain.restaurant;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import ar.edu.itba.it.paw.domain.AbstractModel;
import ar.edu.itba.it.paw.domain.user.User;

@Entity
public class Rating extends AbstractModel implements Comparable<Rating> {

	private Integer score;
	private String comment;

	@ManyToOne
	private User user;

	@ManyToOne
	private Restaurant restaurant;

	private Date date;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="likes")
	Set<User> likes = new HashSet<User>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="unlikes")
	Set<User> unlikes = new HashSet<User>();

	public Rating() {}

	public Rating(int score, String comment, User user, Restaurant restaurant, Date date) {

		this.score = score;
		this.comment = comment;
		this.user = user;
		this.restaurant = restaurant;
		this.date = date;
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

	public Set<User> getLikes() {
		return likes;
	}

	public Set<User> getUnlikes() {
		return unlikes;
	}

	public int getLikeAmmount() {
		return likes.size();
	}

	public int getUnlikeAmmount() {
		return unlikes.size();
	}

	public Date getDate() {
		return date;
	}

	public java.sql.Date getSQLDate() {
		return new java.sql.Date(date.getTime());
	}

	@Override
	public int hashCode() {

		return user.hashCode() + 31 * restaurant.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (!super.equals(obj))
			return false;

		if (getClass() != obj.getClass())
			return false;

		Rating other = (Rating) obj;

		if (restaurant != other.restaurant || user != other.user)
			return false;

		return true;
	}

	@Override
	public int compareTo(Rating o) {

	    // TODO: WAT? nothing to do with o?
		return this.likes.size() - this.unlikes.size();
	}

	public void like(User user) {

		if (unlikes.contains(user)) {
			unlikes.remove(user);
        }

		if (likes.add(user)) {
			user.like(this);
        }
	}

	public void unlike(User user) {

		if (likes.contains(user)) {
			likes.remove(user);
        }

		if (unlikes.add(user)) {
			user.unlike(this);
        }
	}
}

