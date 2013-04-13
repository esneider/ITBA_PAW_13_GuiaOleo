package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.RatingManager;
import ar.edu.itba.it.paw.manager.RestaurantManager;
import ar.edu.itba.it.paw.model.Rating;

@SuppressWarnings("serial")
public class RestaurantDetailServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));

		req.setAttribute("restaurant", RestaurantManager.getInstance().getSingleRestaurant(id));
		req.setAttribute("commentList", RatingManager.getInstance()
				.getRatingsByRestaurant(id));
		
		if (isLoggedIn(req)) {
			Rating r = RatingManager.getInstance().getSingleRating(getLoggedInUser(req), id);
			if (r != null)
				req.setAttribute("userComment", r);
		}

		render(req, resp, "view.jsp", "SimpleRestaurant");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String rid = (String) req.getParameter("id");
		if (rid != null) {
			int id = Integer.valueOf(rid);
			RatingManager.getInstance().insertRating(
					Integer.valueOf((String) req
							.getParameter("restaurant_rating")),
					(String) req.getParameter("comment"), getLoggedInUser(req),
					id);
			doGet(req, resp);
		} else {
			render(req, resp, "error.jsp", "404 NOT FOUND");
			return;
		}
	}
}
