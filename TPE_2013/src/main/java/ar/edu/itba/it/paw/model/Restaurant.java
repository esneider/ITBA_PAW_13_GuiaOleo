package ar.edu.itba.it.paw.model;

import java.util.List;

public class Restaurant extends AbstractModel {

	List<FoodType> foodtypes;
	private String name, address, area, telephone, website, timerange;
	private float avgprice;
	
	public Restaurant(int id, String name, String address, String area,
			String telephone, String website, String timerange, float avgprice) {
		super(id);
		this.name = name;
		this.address = address;
		this.area = area;
		this.telephone = telephone;
		this.website = website;
		this.timerange = timerange;
		this.avgprice = avgprice;
	}
	
	
}
