package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.dao.JDBCRestaurantDAO;
import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;

@SuppressWarnings("serial")
public class RestaurantDetailServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Restaurant List");
		RestaurantDAO r = JDBCRestaurantDAO.getInstance();
		int id = Integer.valueOf(req.getParameter("id"));
		req.setAttribute("restaurant", r.getSingleRestaurant(id));
		render(req, resp, "view.jsp", "SimpleRestaurant");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
