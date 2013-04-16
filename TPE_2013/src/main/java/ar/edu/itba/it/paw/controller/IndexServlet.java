package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.FoodTypeManager;
import ar.edu.itba.it.paw.manager.RestaurantManager;

@SuppressWarnings("serial")
public class IndexServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute("bestRestaurants", RestaurantManager.getInstance()
				.getBestRatedRestaurants(10));
		req.setAttribute("foodTypesList", FoodTypeManager.getInstance()
				.getAll());

		render(req, resp, "index.jsp", "Guia Oleo Facha");
	}

}
