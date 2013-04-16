package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.UserManager;
import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.utils.Utils;

@SuppressWarnings("serial")
public class RegisterServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String destination = getDestination(req);

		if (isLoggedIn(req)) {
			resp.sendRedirect(destination);
			return;
		}

		render(req, resp, "login.jsp", "Login or Register");
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

		check &= checkParameter(req, "registerUsername", 0, 50);
		check &= checkParameter(req, "registerPassword", 0, 64);
		check &= checkParameter(req, "registerRePassword", 0, 64);
		check &= checkParameter(req, "registerName", 0, 50);
		check &= checkParameter(req, "registerSurname", 0, 50);
		check &= checkEmail(req, "registerMail", 0, 50);
		check &= check && checkParamsEqual(req, "registerPassword", "registerRePassword");

		String username = req.getParameter("registerUsername");
		String password = req.getParameter("registerPassword");
		String name = req.getParameter("registerName");
		String surname = req.getParameter("registerSurname");
		String mail = req.getParameter("registerMail");

		req.setAttribute("registerUsername", username);
		req.setAttribute("registerName", name);
		req.setAttribute("registerSurname", surname);
		req.setAttribute("registerMail", mail);

		if (check) {

			User user = UserManager.getInstance().register(name, surname, mail, username, password);
	
			if (user != null) {
	
				setLoggedInUser(req, user);
				resp.sendRedirect(Utils.addParameterToURI(destination, "registerAction", "successful"));
				return;
			}

			req.setAttribute("registerUsernameError", true);
			req.setAttribute("usernameExists", true);
		}

		render(req, resp, "login.jsp", "Login or Register");
	}
}
