package ar.edu.itba.it.paw.model;

public class FoodType extends AbstractModel implements Comparable<FoodType> {

	private String name;
	private int ammount;

	public FoodType(String name, int ammount) {
		super();
		this.name = name;
		this.ammount = ammount;
	}

	public String getName() {
		return name;
	}

	public int getAmmount() {
		return ammount;
	}
	
	@Override
	public int compareTo(FoodType other) {

		return name.compareTo(other.name);
	}
}
