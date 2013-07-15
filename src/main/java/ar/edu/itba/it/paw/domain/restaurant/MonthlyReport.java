package ar.edu.itba.it.paw.domain.restaurant;



public class MonthlyReport {

	private Restaurant restaurant;
	private int clicks;
	private int shows;
	
	public MonthlyReport(Restaurant restaurant, int clicks, int shows) {
		this.restaurant = restaurant;
		this.clicks = clicks;
		this.shows = shows;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public int getClicks() {
		return clicks;
	}

	public int getShows() {
		return shows;
	}
	
	public void addShows(int shows) {
		this.shows += shows;
	}
	
	public void addClicks(int clicks) {
		this.clicks += clicks;
	}

}
