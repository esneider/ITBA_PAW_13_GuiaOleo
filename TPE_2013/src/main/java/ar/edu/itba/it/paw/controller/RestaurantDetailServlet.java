package ar.edu.itba.it.paw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.RatingManager;
import ar.edu.itba.it.paw.manager.RestaurantManager;
import ar.edu.itba.it.paw.model.Rating;
import ar.edu.itba.it.paw.model.Restaurant;

@SuppressWarnings("serial")
public class RestaurantDetailServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));

		Restaurant rest = RestaurantManager.getInstance().getSingleRestaurant(
				id);

		if (rest != null) {
			req.setAttribute("restaurant", rest);
		    req.setAttribute("commentList", RatingManager.getInstance()
					.getRatingsByRestaurant(id));

			if (isLoggedIn(req)) {
				Rating r = RatingManager.getInstance().getSingleRating(
						getLoggedInUser(req), id);
				if (r != null)
					req.setAttribute("userComment", r);
			}

			render(req, resp, "view.jsp", "SimpleRestaurant", true);
		} else {
			render(req, resp, "error.jsp", "404 NOT FOUND", false);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String rid = (String) req.getParameter("id");
		if (rid != null) {
			int id = Integer.valueOf(rid);
			int rating = Integer.valueOf((String) req
					.getParameter("restaurant_rating"));
			String comment = (String) req.getParameter("comment");
			RatingManager.getInstance().insertRating(rating, comment,
					getLoggedInUser(req), id);
			doGet(req, resp);
		} else {
			render(req, resp, "error.jsp", "404 NOT FOUND", false);
			return;
		}
	}
}
