package ar.edu.itba.it.paw.domain.restaurant;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ar.edu.itba.it.paw.domain.PersistentEntity;

@Entity
public class DailyReport extends PersistentEntity {

	@ManyToOne(cascade = CascadeType.ALL)
	Restaurant restaurant;
	
	private int highlightShows;
	private int highlightClicks;

	private Date date;
	
	public DailyReport(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.date = new Date();
	}
	
	public Date getDate() {
		return date;
	}
	
	public int getHighlightShows() {
		return highlightShows;
	}
	
	public int getHighlightClicks() {
		return highlightClicks;
	}
	
	public void show() {
		highlightShows++;
	}
	
	public void click() {
		highlightClicks++;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
}
