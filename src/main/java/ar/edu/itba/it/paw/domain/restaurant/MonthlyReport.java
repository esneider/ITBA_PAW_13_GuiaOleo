package ar.edu.itba.it.paw.domain.restaurant;


public class MonthlyReport {

	private Restaurant rest;
	private int clicks;
	private int shows;
	
	public MonthlyReport(Restaurant rest, int clicks, int shows) {
		this.rest = rest;
		this.clicks = clicks;
		this.shows = shows;
	}
	
	public Restaurant getRest() {
		return rest;
	}

	public int getClicks() {
		return clicks;
	}

	public int getShows() {
		return shows;
	}

}
