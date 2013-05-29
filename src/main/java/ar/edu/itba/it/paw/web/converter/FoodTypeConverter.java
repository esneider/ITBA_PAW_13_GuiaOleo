package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;


@Component
public class FoodTypeConverter implements Converter<String, FoodType> {

    private FoodTypeRepo ftRepo;

    @Autowired
    public FoodTypeConverter (FoodTypeRepo ftRepo) {

        this.ftRepo = ftRepo;
    }

    @Override
    public FoodType convert(String arg) {

        return ftRepo.get(Integer.valueOf(arg));
    }
}

