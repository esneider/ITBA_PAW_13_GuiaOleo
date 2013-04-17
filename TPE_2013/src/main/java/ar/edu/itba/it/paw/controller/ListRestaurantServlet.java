package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.RestaurantManager;

@SuppressWarnings("serial")
public class ListRestaurantServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String query = req.getParameter("query");

		if (query == null) {

			render(req, resp, "error.jsp", "404 NOT FOUND", false);
			return;
		}

		RestaurantManager r = RestaurantManager.getInstance();

		if (query.equals("all")) {

			req.setAttribute("restaurantList", r.getAll());

		} else if (query.equals("foodtypes")) {

			int foodtypeid = Integer.valueOf(req.getParameter("id"));
			req.setAttribute("restaurantList", r.getRestaurantsByFoodType(foodtypeid));

		} else if (query.equals("bestrated")) {
			
			int how_many = Integer.valueOf(req.getParameter("num"));
			req.setAttribute("restaurantList", r.getBestRatedRestaurants(how_many));
		}

		render(req, resp, "list.jsp", "Lista de Restaurantes", true);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doGet(req, resp);
	}

}
