package ar.edu.itba.it.paw.domain;

import javax.persistence.Entity;


@Entity
public class FoodType extends AbstractModel implements Comparable<FoodType> {

	private String name;
	private Integer ammount;

	public FoodType() {}

	public FoodType(String name, Integer ammount) {
		this.name = name;
		this.ammount = ammount;
	}

	public String getName() {
		return name;
	}

	public Integer getAmmount() {
		return ammount;
	}
	
	@Override
	public int compareTo(FoodType other) {

		return name.compareTo(other.name);
	}
}
