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
public class RestaurantDetailServlet extends BaseServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
System.out.println("Restaurant List");
RestaurantDAO r = JDBCRestaurantDAO.getInstance();
int id = Integer.valueOf(req.getParameter("id"));
System.out.println(id);
// req.setAttribute("restaurant", r.getRestaurant(id));

Restaurant deleteme = new Restaurant(id, "Prueba", "address", "area", "telephone", "website", "timerange", 1, 2, 3);
req.setAttribute("restaurant", deleteme);
render(req, resp, "view.jsp", "SimpleRestaurant");

}

protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
doGet(req, resp);
}
}
