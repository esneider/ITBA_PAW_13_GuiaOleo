package ar.edu.itba.it.paw.utils;

import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.user.UserRepo;


@Component
public class Utils {

	private static UserRepo userRepo;

	Utils() {}

	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		Utils.userRepo = userRepo;
	}

    public static boolean isEmail(String str) {

        return str != null && str.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static String addParameterToURI(String URI, String param, String value) {

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
}

