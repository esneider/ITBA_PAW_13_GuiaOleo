package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.UserManager;
import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.utils.ValidationHelpers;

@SuppressWarnings("serial")
public class LoginServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String destination = getDestination(req);

		if (isLoggedIn(req)) {

			resp.sendRedirect(destination);
			return;
		}

		render(req, resp, "login.jsp", "Login or Register", false);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String destination = getDestination(req);

		if (isLoggedIn(req)) {
			resp.sendRedirect(destination);
			return;
		}

		boolean check = true;

		check &= ValidationHelpers.checkParameter(req, "loginUsername", 0, 50);
		check &= ValidationHelpers.checkParameter(req, "loginPassword", 0, 64);

		String username = req.getParameter("loginUsername");
		String password = req.getParameter("loginPassword");

		req.setAttribute("loginUsername", username);

		if (check) {
			
			User user = UserManager.getInstance().login(username, password);
	
			if (user != null) {
	
				setLoggedInUser(req, user);
				resp.sendRedirect(Utils.addParameterToURI(destination, "loginAction", "successful"));
				return;
			}
	
			req.setAttribute("invalidUser", true);
		}

		render(req, resp, "login.jsp", "Login or Register", false);
	}
}
