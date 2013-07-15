package ar.edu.itba.it.paw.domain.restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.utils.Utils;

@Entity
public class Restaurant extends PersistentEntity {

	@ManyToMany
	Set<FoodType> foodtypes;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	Set<DailyReport> reports;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	Set<Rating> ratingsList = new HashSet<Rating>();

	@ManyToOne
	private User registerUser;

	private String name, address, area, telephone, website, timerange;

	@Enumerated(EnumType.STRING)
	private RestaurantState state;

	private float avgprice;

	private Date applicationDate;
	private Long accessCount;
	private Boolean highlighted = false;

	Restaurant() {
	}

	public Restaurant(String name, String address, String area,
			String telephone, String website, String timerange, float avgprice,
			RestaurantState state, Set<FoodType> foodtypes, User user,
			Date appDate) {
		name = Utils.normalizeString(name);
		address = Utils.normalizeString(address);
		area = Utils.normalizeString(area);
		telephone = Utils.normalizeString(telephone);
		website = Utils.normalizeString(website);
		timerange = Utils.normalizeString(timerange);

		if (name.isEmpty()) {
			throw new IllegalArgumentException("Empty name");
		}
		if (address.isEmpty()) {
			throw new IllegalArgumentException("Empty address");
		}
		if (area.isEmpty()) {
			throw new IllegalArgumentException("Empty area");
		}
		if (telephone.isEmpty()) {
			throw new IllegalArgumentException("Empty telephone");
		}
		if (website.isEmpty()) {
			throw new IllegalArgumentException("Empty website");
		}
		if (timerange.isEmpty()) {
			throw new IllegalArgumentException("Empty timerange");
		}
		if (avgprice <= 0) {
			throw new IllegalArgumentException("Non-positive avgprice");
		}
		if (foodtypes == null || foodtypes.isEmpty()) {
			throw new IllegalArgumentException("Empty foodtypes");
		}
		if (user == null) {
			throw new IllegalArgumentException("Empty user");
		}
		if (appDate == null) {
			throw new IllegalArgumentException("Empty appDate");
		}

		this.name = name;
		this.address = address;
		this.area = area;
		this.telephone = telephone;
		this.website = website;
		this.timerange = timerange;
		this.avgprice = Utils.round(avgprice);
		this.foodtypes = foodtypes;
		this.registerUser = user;
		this.applicationDate = appDate;

		setState(state);
	}

	public Set<FoodType> getFoodtypes() {
		return foodtypes;
	}
	
	public void addFoodType(FoodType ft) {
		foodtypes.add(ft);
	}

	public String getAddress() {
		return address;
	}

	public String getArea() {
		return area;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getWebsite() {
		return website;
	}

	public String getTimerange() {
		return timerange;
	}

	public float getAvgprice() {
		return (avgprice);
	}

	public String getName() {
		return name;
	}

	public List<Rating> getRatings() {
		List<Rating> sortedList = new ArrayList<Rating>(ratingsList);
		Collections.sort(sortedList);
		return sortedList;
	}

	public User getRegisterUser() {
		return registerUser;
	}

	public RestaurantState getState() {
		return state;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setState(RestaurantState state) {

		if (state == null) {
			throw new IllegalArgumentException("Empty state");
		}

		this.state = state;
	}

	public void addRating(Rating r) {

		if (r == null) {
			throw new IllegalArgumentException("Empty rating");
		}

		ratingsList.add(r);
	}

	public void removeRating(Rating r) {

		if (r == null) {
			throw new IllegalArgumentException("Empty rating");
		}

		ratingsList.remove(r);
	}

	public int getRatingsAmmount() {

		return ratingsList.size();
	}

	public Rating getUserRating(User user) {

		for (Rating rating : ratingsList) {
			if (rating.getUser().equals(user)) {
				return rating;
			}
		}

		return null;
	}

	public float getAvgScore() {

		float avg = 0;

		for (Rating rating : ratingsList) {
			avg += rating.getScore();
		}

		return Utils.round(avg / ratingsList.size());
	}

	@Override
	public int hashCode() {

		final int prime = 31;

		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (!super.equals(obj))
			return false;

		if (getClass() != obj.getClass())
			return false;

		Restaurant other = (Restaurant) obj;

		if (address == null && other.address != null)
			return false;

		if (address != null && !address.equals(other.address))
			return false;

		if (area == null && other.area != null)
			return false;

		if (area != null && !area.equals(other.area))
			return false;

		if (name == null && other.name != null)
			return false;

		if (name != null && !name.equals(other.name))
			return false;

		return true;
	}

	public boolean isAccepted() {
		return this.state.equals(RestaurantState.Accepted);
	}

	public boolean isHighlighted() {

		if (highlighted == null) {
			return false;
		}

		return highlighted;
	}

	public void highlight() {
		this.highlighted = true;
	}

	public void unhighlight() {
		this.highlighted = false;
	}

	public Long getAccessCount() {
		if (accessCount == null) {
			accessCount = (long) 0;
		}
		return accessCount;
	}
	
	public void setAccessCount(Long accessCount) {
		this.accessCount = accessCount;
	}

	public void setNewAccess() {
		
		this.accessCount = getAccessCount() + 1;
	}
	
	public void click(DailyReportRepo reportRepo) {
		DailyReport report = getReport(reportRepo);
		report.click();
	}
	
	public void show(DailyReportRepo reportRepo) {
		DailyReport report = getReport(reportRepo);
		report.show();
	}
	
	private DailyReport getReport(DailyReportRepo reportRepo) {
		DailyReport report = reportRepo.get(this, new Date());
		
		if (report == null) { 
			report = new DailyReport(this);
			reports.add(report);
		}
		
		return report;
	}
}
