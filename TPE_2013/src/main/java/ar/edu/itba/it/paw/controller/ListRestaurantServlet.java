package ar.edu.itba.it.paw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.dao.JDBCRestaurantDAO;
import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.model.Restaurant;

@SuppressWarnings("serial")
public class ListRestaurantServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Restaurant List");
		RestaurantDAO r = JDBCRestaurantDAO.getInstance();
		List<Restaurant> lr = new ArrayList<Restaurant>();
		lr.add(new Restaurant(0, "Test1", "Address", "area", "telephone",
				"website", "timerange", 320, 10, 100));
		lr.add(new Restaurant(1, "Test2", "Addrdss", "areas", "telepsahone",
				"webssdsite", "timerandsge", 32500, 10, 200));
		// req.setAttribute("restaurantList", r.getAll());
		req.setAttribute("restaurantList", lr);
		render(req, resp, "list.jsp", "Lista de Restaurantes");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
