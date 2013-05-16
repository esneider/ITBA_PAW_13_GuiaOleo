package ar.edu.itba.it.paw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.Restaurant;
import ar.edu.itba.it.paw.service.RestaurantService;

@SuppressWarnings("serial")
public class IndexServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//req.setAttribute("restaurantList", RestaurantService.getInstance().getBestRatedRestaurants(10));
		render(req, resp, "list.jsp", "Guia Oleo Facha - Welcome!", true);
	}
}
