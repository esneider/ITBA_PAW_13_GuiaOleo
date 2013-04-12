package ar.edu.itba.it.paw.controller;

import java.io.IOException;
//import java.util.Calendar;
//import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.dao.JDBCRestaurantDAO;
import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.model.User;

@SuppressWarnings("serial")
public class SearchServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RestaurantDAO r = JDBCRestaurantDAO.getInstance();
		String query = req.getParameter("query");
		req.setAttribute("restaurantList", r.getRestaurantsByQuery(query));
		render(req, resp, "list.jsp", "Resultado de la busqueda");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}