package ar.edu.itba.it.paw.utils;

import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;
import ar.edu.itba.it.paw.domain.user.UserRepo;

@Component
public class Utils {

    private static UserRepo userRepo;
    private static FoodTypeRepo foodTypeRepo;

    public Utils() {}

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        Utils.userRepo = userRepo;
    }

    @Autowired
    public void setFoodTypeRepo(FoodTypeRepo foodTypeRepo) {
        Utils.foodTypeRepo = foodTypeRepo;
    }

    public static boolean isEmail(String str) {

        return str != null
                && str.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static String addParameterToURI(String URI, String param,
            String value) {

        URIBuilder builder;

        try {

            builder = new URIBuilder(URI);

        } catch (URISyntaxException e) {

            e.printStackTrace();
            return URI;
        }

        builder.addParameter(param, value);

        return builder.toString();
    }

    public static float round(float num) {

        return ((float) Math.round(num * 100)) / 100;
    }

    public static String normalizeString(String s) {

        if (s == null) {

            return "";
        }

        return s.trim();
    }

    public static boolean usernameExists(String username) {

        return userRepo.usernameExists(username);
    }

    public static boolean emailExists(String email) {

        return userRepo.emailExists(email);
    }

    public static boolean foodTypeExists(String name) {

        return foodTypeRepo.foodTypeExists(name);
    }

    public static String function(Double doubleValue) {

        boolean isWholeNumber = (doubleValue == Math.round(doubleValue));
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        formatSymbols.setDecimalSeparator('.');

        String pattern = isWholeNumber ? "#.##" : "#.00";
        DecimalFormat df = new DecimalFormat(pattern, formatSymbols);
        return (df.format(doubleValue));
    }
}

