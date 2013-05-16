package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.service.UserService;
import ar.edu.itba.it.paw.utils.ValidationHelpers;

@SuppressWarnings("serial")
public class ModifyUserServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User user = getLoggedInUser(req);

		req.setAttribute("registerName", user.getName());
		req.setAttribute("registerSurname", user.getSurname());
		req.setAttribute("registerEmail", user.getEmail());

		render(req, resp, "modify_user.jsp", "Modify your data", false);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		MultipartPictures multipart = multipartPictures(req);
		req = multipart.req;

		boolean check = true;

		check &= ValidationHelpers.checkParameter(req, "registerPassword", 0, 64, true);
		check &= ValidationHelpers.checkParameter(req, "registerRePassword", 0, 64, true);
		check &= ValidationHelpers.checkParameter(req, "registerName", 0, 50);
		check &= ValidationHelpers.checkParameter(req, "registerSurname", 0, 50);
		check &= ValidationHelpers.checkEmail(req, "registerEmail", 0, 50, true, getLoggedInUser(req).getId());
		check &= check && ValidationHelpers.checkParamsEqual(req, "registerPassword", "registerRePassword");

		String oldPassword = req.getParameter("registerOldPassword");
		String password = req.getParameter("registerPassword");
		String name = req.getParameter("registerName");
		String surname = req.getParameter("registerSurname");
		String email = req.getParameter("registerEmail");

		req.setAttribute("registerName", name);
		req.setAttribute("registerSurname", surname);
		req.setAttribute("registerEmail", email);

		if (password == null || password.isEmpty()) {

			password = getLoggedInUser(req).getPassword();

		} else {

			check &= ValidationHelpers.checkParameter(req, "registerOldPassword", 0, 64);

			if (check && !getLoggedInUser(req).getPassword().trim().equals(oldPassword)) {

				check = false;
				req.setAttribute("registerOldPasswordError", true);
				req.setAttribute("registerOldPasswordInvalid", true);
			}
		}

		if (check) {

			User updateUser = getLoggedInUser(req);

			updateUser.setName(name);
			updateUser.setSurname(surname);
			updateUser.setEmail(email);
			updateUser.setPassword(password);

			if (multipart.pictures != null) {
				updateUser.setAvatar(multipart.pictures.get(0));
			}

			//UserService.getInstance().update(updateUser);

			resp.sendRedirect("index?modifyAction=successful");
			return;
		}

		render(req, resp, "modify_user.jsp", "Modify your data", false);
	}
}
