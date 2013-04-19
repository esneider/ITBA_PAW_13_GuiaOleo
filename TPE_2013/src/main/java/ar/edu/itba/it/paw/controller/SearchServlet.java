package ar.edu.itba.it.paw.controller;

import java.io.IOException;
//import java.util.Calendar;
//import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.service.RestaurantService;

@SuppressWarnings("serial")
public class SearchServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String query = req.getParameter("query");
		req.setAttribute("restaurantList", RestaurantService.getInstance().getRestaurantsByQuery(query));
		render(req, resp, "list.jsp", "Resultado de la busqueda", true);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}