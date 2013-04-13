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
			render(req, resp, "error.jsp", "404 NOT FOUND");
			return;
		}
		RestaurantManager r = RestaurantManager.getInstance();

		if (query.compareTo("all") == 0) {
			req.setAttribute("restaurantList", r.getAll());

		} else if (query.compareTo("foodtypes") == 0) {
			int foodtypeid = Integer.valueOf(req.getParameter("id"));
			req.setAttribute("restaurantList",
					r.getRestaurantsByFoodType(foodtypeid));
		}
		render(req, resp, "list.jsp", "Lista de Restaurantes");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
