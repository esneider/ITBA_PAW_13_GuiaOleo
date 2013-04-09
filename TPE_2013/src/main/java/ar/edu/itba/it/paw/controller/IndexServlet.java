package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.dao.JDBCFoodTypesDAO;
import ar.edu.itba.it.paw.dao.JDBCRestaurantDAO;
import ar.edu.itba.it.paw.dao.interfaces.FoodTypesDAO;
import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;

@SuppressWarnings("serial")
public class IndexServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RestaurantDAO rsDAO = JDBCRestaurantDAO.getInstance();
		FoodTypesDAO ftDAO = JDBCFoodTypesDAO.getInstance();
		
		req.setAttribute("bestRestaurants", rsDAO.getBestRatedRestaurants(10));
		req.setAttribute("foodTypesList", ftDAO.getAll());
		
		render(req, resp, "index.jsp", "Guia Oleo Facha");
	}
	
}
