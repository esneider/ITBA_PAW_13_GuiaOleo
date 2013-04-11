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

		boolean check = checkParameter(req, "username", 0, 50) &&
				        checkParameter(req, "password", 0, 64);

		if (check) {
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
	
			User user = UserManager.getInstance().login(username, password);
	
			if (user != null) {
	
				setLoggedInUser(req, user);
				resp.sendRedirect("");
	
			} else {
	
				req.setAttribute("invalidUser", true);
			}
		}

		render(req, resp, "login.jsp", "Login or Register");
	}
}
