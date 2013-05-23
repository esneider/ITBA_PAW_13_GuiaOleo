package ar.edu.itba.it.paw.web.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.user.User;

public class RestaurantForm {
	
	private String name, address, area, telephone, website, timerange;
	private float avgprice;
	private List<FoodType> foodTypes = new ArrayList<FoodType>();
	
	public RestaurantForm() {
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTimerange() {
		return timerange;
	}
	public void setTimerange(String timerange) {
		this.timerange = timerange;
	}
	public float getAvgprice() {
		return avgprice;
	}
	public List<FoodType> getFoodTypes() {
		return foodTypes;
	}
	public void setFoodTypes(List<FoodType> foodTypes) {
		this.foodTypes = foodTypes;
	}
	public void setAvgprice(float avgprice) {
		this.avgprice = avgprice;
	}

	public Restaurant build(User user, String state) {
		Set<FoodType> ftSet = new HashSet<FoodType>();
		for (FoodType ft : foodTypes) {
			if (ft != null)
				ftSet.add(ft);
		}
		return new Restaurant(name, address, area, telephone, website,
				timerange, avgprice, state, ftSet, user, new Date());
	}

}
