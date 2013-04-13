package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.dao.JDBCRatingsDAO;
import ar.edu.itba.it.paw.dao.JDBCRestaurantDAO;
import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.model.Rating;
import ar.edu.itba.it.paw.model.Restaurant;
import ar.edu.itba.it.paw.model.User;

@SuppressWarnings("serial")
public class RestaurantDetailServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RestaurantDAO r = JDBCRestaurantDAO.getInstance();
		int id = Integer.valueOf(req.getParameter("id"));
		req.setAttribute("restaurant", r.getSingleRestaurant(id));
		render(req, resp, "view.jsp", "SimpleRestaurant");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String rid = (String) req.getParameter("rid");
		Restaurant rest = null;
		if (rid != null) {
			rest = JDBCRestaurantDAO.getInstance().getSingleRestaurant(Integer.valueOf(rid));
			if (rest != null) {
				
				JDBCRatingsDAO.getInstance();
				
				Rating rate = new Rating(Integer.valueOf((String)req.getParameter("restaurant_rating")), 
						(String)req.getParameter("comment"), new User(getLoggedInUser(req), "", "", "", "", ""), rest);
				JDBCRatingsDAO.getInstance().insertSingleRating(rate);
				req.setAttribute("restaurant", rest);
				render(req, resp, "view.jsp", "SimpleRestaurant");
			
			}
		}
	}
}
