package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.Rating;
import ar.edu.itba.it.paw.model.Restaurant;
import ar.edu.itba.it.paw.service.RatingService;
import ar.edu.itba.it.paw.service.RestaurantService;

@SuppressWarnings("serial")
public class RestaurantDetailServlet extends BaseServlet {

	/*protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			int id = Integer.valueOf(req.getParameter("id"));
	
			Restaurant rest = RestaurantService.getInstance().getSingleRestaurant(
					id);
	
			if (rest != null) {
				
				req.setAttribute("restaurant", rest);
			
			    req.setAttribute("commentList", RatingService.getInstance()
						.getRatingsByRestaurant(RestaurantService.getInstance().getSingleRestaurant(id)));
	
				if (isLoggedIn(req)) {
					Rating r = RatingService.getInstance().getSingleRating(
							getLoggedInUser(req), RestaurantService.getInstance().getSingleRestaurant(id));
					if (r != null)
						req.setAttribute("userComment", r);
				}
	
				render(req, resp, "view.jsp", "SimpleRestaurant", true);
				
			} else {
				render(req, resp, "error.jsp", "404 NOT FOUND", false);
			}
		} catch (Exception e) {
			render(req, resp, "error.jsp", "404 NOT FOUND", false);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String rid = (String) req.getParameter("id");
		if (rid != null) {
			try {
				int id = Integer.valueOf(rid);
				int rating = Integer.valueOf((String) req
						.getParameter("restaurant_rating"));
				String comment = (String) req.getParameter("comment");
				RatingService.getInstance().insertRating(rating, comment,
						getLoggedInUser(req), RestaurantService.getInstance().getSingleRestaurant(id));
				doGet(req, resp);
			} catch (Exception e) {
				render(req, resp, "error.jsp", "404 NOT FOUND", false);
				return;
			}
		} else {
			render(req, resp, "error.jsp", "404 NOT FOUND", false);
			return;
		}
	}*/
}
