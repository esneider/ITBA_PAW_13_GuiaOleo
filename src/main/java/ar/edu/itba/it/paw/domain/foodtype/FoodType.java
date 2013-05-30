package ar.edu.itba.it.paw.domain.foodtype;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.itba.it.paw.domain.AbstractModel;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantState;
import ar.edu.itba.it.paw.utils.Utils;

@Entity
public class FoodType extends AbstractModel implements Comparable<FoodType> {

	private String name;

	@ManyToMany(mappedBy = "foodtypes")
	private Set<Restaurant> restaurants;

	FoodType() {}

	public FoodType(String name) {

		name = Utils.normalizeString(name);

		if (name.isEmpty()) {
			throw new IllegalArgumentException("Empty name");
		}

		if (Utils.foodTypeExists(name)) {
			throw new IllegalArgumentException("Duplicated name");
		}

		this.name = name;
	}

	public String getName() {

		return name;
	}

	public Set<Restaurant> getRestaurants() {

		Set<Restaurant> acceptedRestaurants = new HashSet<Restaurant>();

		for (Restaurant r : restaurants) {
			if (r.getState() == RestaurantState.Accepted) {
				acceptedRestaurants.add(r);
			}
		}

		return acceptedRestaurants;
	}

	public Integer getAmmount() {

		return getRestaurants().size();
	}

	@Override
	public int compareTo(FoodType other) {

		return name.compareTo(other.name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		FoodType other = (FoodType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
