package ar.edu.itba.it.paw.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;


@Entity
public class FoodType extends AbstractModel implements Comparable<FoodType> {

	private String name;
	private Integer ammount;

	@ManyToMany
	private Set<Restaurant> restaurants;
	
	public FoodType() {}

	public FoodType(String name, Integer ammount) {
		this.name = name;
		this.ammount = ammount;
	}

	public String getName() {
		return name;
	}
	
	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}

	public Integer getAmmount() {
		return ammount;
	}
	
	@Override
	public int compareTo(FoodType other) {

		return name.compareTo(other.name);
	}
}
