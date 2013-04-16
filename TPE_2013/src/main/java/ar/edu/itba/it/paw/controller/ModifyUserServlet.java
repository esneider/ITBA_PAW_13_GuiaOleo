package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.User;

@SuppressWarnings("serial")
public class ModifyUserServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User user = getLoggedInUser(req);

		req.setAttribute("registerName", user.getName());
		req.setAttribute("registerSurname", user.getSurname());
		req.setAttribute("registerMail", user.getMail());

		render(req, resp, "modify_user.jsp", "Modify your data");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		boolean check = true;

		check &= checkParameter(req, "registerPassword", 0, 64, true);
		check &= checkParameter(req, "registerRePassword", 0, 64, true);
		check &= checkParameter(req, "registerName", 0, 50);
		check &= checkParameter(req, "registerSurname", 0, 50);
		check &= checkEmail(req, "registerMail", 0, 50);
		check &= check && checkParamsEqual(req, "registerPassword", "registerRePassword");

		String password = req.getParameter("registerPassword");
		String name = req.getParameter("registerName");
		String surname = req.getParameter("registerSurname");
		String mail = req.getParameter("registerMail");

		req.setAttribute("registerName", name);
		req.setAttribute("registerSurname", surname);
		req.setAttribute("registerMail", mail);

		if (check) {

			if (password == null || password.isEmpty()) {
				password = getLoggedInUser(req).getPassword();
			}

			getLoggedInUser(req).update(name, surname, mail, password);
			resp.sendRedirect("index?modifyAction=successful");
			return;
		}

		render(req, resp, "modify_user.jsp", "Modify your data");
	}

}
