package ar.edu.itba.it.paw.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;


public abstract class BaseController {

    @Autowired
    private FoodTypeRepo ftRepo;

    @Autowired
    private RestaurantRepo restRepo;

    public boolean isLoggedIn(HttpSession session) {

        return session.getAttribute("user") != null;
    }

    public User getLoggedInUser(HttpSession session) {

        return (User)session.getAttribute("user");
    }

    public void setLoggedInUser(HttpSession session, User user) {

        session.setAttribute("user", user);
    }

    public void logoutUser(HttpSession session) {

        session.invalidate();
    }

    public EnhancedModelAndView generateContext(String title, boolean sidebar, boolean setFoodTypes) {

        EnhancedModelAndView mav = new EnhancedModelAndView(title);

        mav.addObject("sidebar", sidebar);
        mav.addObject("numberOfRestaurants", restRepo.getAll().size()); // TODO: here?

        if (setFoodTypes) {
            mav.addObject("foodTypesList", ftRepo.getAll()); // TODO: isn't this the same as sidebar?
        }

        return mav;
    }

    public EnhancedModelAndView generateContext(String title, boolean sidebar, boolean setFoodTypes, String viewName) {

        EnhancedModelAndView mav = generateContext(title, sidebar, setFoodTypes);
        mav.setViewName(viewName);
        return mav;
    }

    public EnhancedModelAndView indexContext() {

        EnhancedModelAndView mav = new EnhancedModelAndView("Oleo's guide");
        mav.setViewName("redirect:/bin/index/list");
        return mav;
    }
}

