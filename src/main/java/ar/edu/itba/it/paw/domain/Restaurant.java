package ar.edu.itba.it.paw.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Restaurant extends AbstractModel {

	@OneToOne
	FoodType foodtype;
	private String name, address, area, telephone, website, timerange;
	private float avgprice, avgscore;
	private int ratings;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	List<Rating> ratingsList = new ArrayList<Rating>();

	public Restaurant() {
	}

	public Restaurant(String name, String address, String area,
			String telephone, String website, String timerange, float avgprice,
			float avgscore, int ratings, FoodType foodtype) {

		this.name = name;
		this.address = address;
		this.area = area;
		this.telephone = telephone;
		this.website = website;
		this.timerange = timerange;
		this.avgprice = avgprice;
		this.avgscore = avgscore;
		this.ratings = ratings;
		this.foodtype = foodtype;
	}

	public FoodType getFoodtype() {
		return foodtype;
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

	public int getRatings() {
		return ratings;
	}

	public void setAvgScore(float val) {
		this.avgscore = val;
	}

	public void setRatings(int val) {
		this.ratings = val;
	}

}
