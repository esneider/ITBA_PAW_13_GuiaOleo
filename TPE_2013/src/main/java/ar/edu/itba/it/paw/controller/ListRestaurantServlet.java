package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.dao.JDBCRestaurantDAO;
import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;

@SuppressWarnings("serial")
public class ListRestaurantServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RestaurantDAO r = JDBCRestaurantDAO.getInstance();
		req.setAttribute("restaurantList", r.getAll());
		render(req, resp, "list.jsp", "Lista de Restaurantes");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
