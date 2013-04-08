package ar.edu.itba.it.paw.model;

import java.util.List;

public class Restaurant extends AbstractModel {

	FoodType foodtype;
	private String name, address, area, telephone, website, timerange;
	private float avgprice, avgscore;
	private int ratings;
	
	public Restaurant(int id, String name, String address, String area,
			String telephone, String website, String timerange, float avgprice, float avgscore, int ratings) {
		super(id);
		this.name = name;
		this.address = address;
		this.area = area;
		this.telephone = telephone;
		this.website = website;
		this.timerange = timerange;
		this.avgprice = avgprice;
		this.avgscore = avgscore;
		this.ratings = ratings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
