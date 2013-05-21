package ar.edu.itba.it.paw.domain.restaurant;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import ar.edu.itba.it.paw.domain.AbstractModel;
import ar.edu.itba.it.paw.domain.FoodType;
import ar.edu.itba.it.paw.domain.Rating;

@Entity
public class Restaurant extends AbstractModel {

	@ManyToMany(mappedBy = "restaurants")
	Set<FoodType> foodtypes;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	Set<Rating> ratingsList = new HashSet<Rating>();

	private String name, address, area, telephone, website, timerange;
	private float avgprice, avgscore;


	public Restaurant() {
	}

	public Restaurant(String name, String address, String area,
			String telephone, String website, String timerange, float avgprice,
			float avgscore, Set<FoodType> foodtypes) {

		this.name = name;
		this.address = address;
		this.area = area;
		this.telephone = telephone;
		this.website = website;
		this.timerange = timerange;
		this.avgprice = avgprice;
		this.avgscore = avgscore;
		this.foodtypes = foodtypes;
	}

	public Set<FoodType> getFoodtypes() {
		return foodtypes;
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
		return avgprice;
	}

	public float getAvgScore() {
		return avgscore;
	}

	public String getName() {
		return name;
	}

	public void setAvgScore(float val) {
		this.avgscore = val;
	}

	public float getRestaurantAvgRating() {
		float avg = 0;
		for (Rating r : ratingsList) {
			avg += r.getScore();
		}
		return avg/ratingsList.size();
	}

}
