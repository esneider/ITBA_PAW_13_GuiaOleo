package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.dao.JDBCRestaurantDAO;
import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;

@SuppressWarnings("serial")
public class IndexServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RestaurantDAO rsDAO = JDBCRestaurantDAO.getInstance();
		req.setAttribute("bestRestaurants", rsDAO.getBestRatedRestaurants(10));
		render(req, resp, "index.jsp", "Guia Oleo Facha");
	}
	
}
