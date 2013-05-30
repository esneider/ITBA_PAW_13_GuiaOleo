package ar.edu.itba.it.paw.domain.restaurant;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ar.edu.itba.it.paw.domain.AbstractModel;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.utils.Utils;


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

    Rating() {}

    public Rating(int score, String comment, User user, Restaurant restaurant, Date date) {

        if (score < 0 || score > 5) {
            throw new IllegalArgumentException("Score out of range");
        }

        if (user == null) {
            throw new IllegalArgumentException("Empty user");
        }

        if (restaurant == null) {
            throw new IllegalArgumentException("Empty restaurant");
        }

        if (date == null) {
            throw new IllegalArgumentException("Empty date");
        }

        comment = Utils.normalizeString(comment);

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

    public void like(User user) {

        if (user == null) {
            throw new IllegalArgumentException("Empty user");
        }

        unlikes.remove(user);

        if (likes.add(user)) {
            user.like(this);
        }
    }

    public void unlike(User user) {

        if (user == null) {
            throw new IllegalArgumentException("Empty user");
        }

        likes.remove(user);

        if (unlikes.add(user)) {
            user.unlike(this);
        }
    }

    public int getLikedScore() {
    	return likes.size() - unlikes.size();
    }
    
	@Override
	public int compareTo(Rating arg0) {
		return arg0.getLikedScore() - this.getLikedScore();
	}
}

