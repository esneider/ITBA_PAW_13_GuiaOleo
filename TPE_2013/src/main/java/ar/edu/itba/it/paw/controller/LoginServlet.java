package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.UserManager;
import ar.edu.itba.it.paw.model.User;

@SuppressWarnings("serial")
public class LoginServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		render(req, resp, "login.jsp", "Login or Register");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		boolean check = true;
		check &= checkParameter(req, "loginUsername", 0, 50);
		check &= checkParameter(req, "loginPassword", 0, 64);

		String username = req.getParameter("loginUsername");
		String password = req.getParameter("loginPassword");

		req.setAttribute("loginUsername", username);

		if (check) {
			
			User user = UserManager.getInstance().login(username, password);
	
			if (user != null) {
	
				setLoggedInUser(req, user);
				resp.sendRedirect("index");
	
			} else {
	
				req.setAttribute("invalidUser", true);
			}
		}

		render(req, resp, "login.jsp", "Login or Register");
	}
}
