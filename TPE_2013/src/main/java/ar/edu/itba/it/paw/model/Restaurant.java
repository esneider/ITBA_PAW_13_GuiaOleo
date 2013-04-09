package ar.edu.itba.it.paw.model;


public class Restaurant extends AbstractModel {

	FoodType foodtype;
	private String name, address, area, telephone, website, timerange;
	private float avgprice, avgscore;
	private int ratings;

	public Restaurant(int id, String name, String address, String area,
			String telephone, String website, String timerange, float avgprice,
			float avgscore, int ratings) {
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
	
	public float getAvgScore(){
		return avgscore;
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

	public float getAvgscore() {
		return avgscore;
	}

	public int getRatings() {
		return ratings;
	}

}
