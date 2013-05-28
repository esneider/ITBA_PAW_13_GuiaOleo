package ar.edu.itba.it.paw.domain.foodtype;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import ar.edu.itba.it.paw.domain.AbstractModel;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantState;


@Entity
public class FoodType extends AbstractModel implements Comparable<FoodType> {

    private String name;

    @ManyToMany(mappedBy = "foodtypes")
    private Set<Restaurant> restaurants;

    FoodType() {}

    public FoodType(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public Set<Restaurant> getRestaurants() {

        Set<Restaurant> acceptedRestaurants = new HashSet<Restaurant>();

        for (Restaurant r: restaurants) {
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
}

