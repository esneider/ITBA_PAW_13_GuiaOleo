package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListRestaurantServlet extends BaseServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Listado Resutarantes");
		render(req, resp, "/WEB-INF/jsp/index.jsp", "Guia Oleo Facha");
		//req.setAttribute("restaurants", manager.getAll());
		req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
	}
}
