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

		boolean check = true;

		check &= checkParameter(req, "registerUsername", 0, 50);
		check &= checkParameter(req, "registerPassword", 0, 64);
		check &= checkParameter(req, "registerRePassword", 0, 64);
		check &= checkParameter(req, "registerName", 0, 50);
		check &= checkParameter(req, "registerSurname", 0, 50);
		check &= checkParameter(req, "registerMail", 0, 50);

		String username = req.getParameter("registerUsername");
		String password = req.getParameter("registerPassword");
		String rePassword = req.getParameter("registerRePassword");
		String name = req.getParameter("registerName");
		String surname = req.getParameter("registerSurname");
		String mail = req.getParameter("registerMail");

		req.setAttribute("registerUsername", username);
		req.setAttribute("registerName", name);
		req.setAttribute("registerSurname", surname);
		req.setAttribute("registerMail", mail);

		if (check && !password.equals(rePassword)) {

			check = false;
			req.setAttribute("registerPasswordsDontMatch", true);
		}

		if (check) {

			User user = UserManager.getInstance().register(name, surname, mail, username, password);
	
			if (user != null) {
	
				setLoggedInUser(req, user);
				resp.sendRedirect("index");

			} else {
	
				req.setAttribute("usernameExists", true);
			}
		}

		render(req, resp, "login.jsp", "Login or Register");
	}
}
