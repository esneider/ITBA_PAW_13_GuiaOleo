package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.UserManager;
import ar.edu.itba.it.paw.model.User;

@SuppressWarnings("serial")
public class RegisterServlet extends BaseServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String rePassword = req.getParameter("repeat-password");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");

		if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {

			User user = UserManager.getInstance().login(username, password);

			if (user != null) {

				setLoggedInUser(req, user);
				resp.sendRedirect("");

			} else {

				req.setAttribute("loginError", "Incorrect user or password.");
			}

		} else {

			req.setAttribute("loginError", "Provide a user and a password please.");
		}

		render(req, resp, "login.jsp", "Login or Register");
	}
}
