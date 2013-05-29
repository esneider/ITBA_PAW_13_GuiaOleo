package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.restaurant.Rating;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;


@Component
public class RatingConverter implements Converter<String, Rating> {

    private RestaurantRepo restRepo;

    @Autowired
    public RatingConverter (RestaurantRepo restRepo) {

        this.restRepo = restRepo;
    }

    @Override
    public Rating convert(String arg) {

        return restRepo.getRating(Integer.valueOf(arg));
    }
}

