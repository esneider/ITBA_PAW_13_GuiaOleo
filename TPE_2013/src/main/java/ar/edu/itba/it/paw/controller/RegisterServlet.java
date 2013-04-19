package ar.edu.itba.it.paw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemStream;

import ar.edu.itba.it.paw.manager.PictureManager;
import ar.edu.itba.it.paw.manager.UserManager;
import ar.edu.itba.it.paw.model.Picture;
import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.utils.ValidationHelpers;

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

		render(req, resp, "login.jsp", "Login or Register", false);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		MultipartPictures multipart = multipartPictures(req);
		req = multipart.req;

		String destination = getDestination(req);

		if (isLoggedIn(req)) {
			resp.sendRedirect(destination);
			return;
		}

		boolean check = true;

		check &= ValidationHelpers.checkUsername(req, "registerUsername", 0, 50);
		check &= ValidationHelpers.checkParameter(req, "registerPassword", 0, 64);
		check &= ValidationHelpers.checkParameter(req, "registerRePassword", 0, 64);
		check &= ValidationHelpers.checkParameter(req, "registerName", 0, 50);
		check &= ValidationHelpers.checkParameter(req, "registerSurname", 0, 50);
		check &= ValidationHelpers.checkEmail(req, "registerEmail", 0, 50, false, -1);
		check &= ValidationHelpers.checkHasParameter(req, "registerAvatar");
		check &= check && ValidationHelpers.checkParamsEqual(req, "registerPassword", "registerRePassword");

		String username = req.getParameter("registerUsername");
		String password = req.getParameter("registerPassword");
		String name = req.getParameter("registerName");
		String surname = req.getParameter("registerSurname");
		String email = req.getParameter("registerEmail");

		req.setAttribute("registerUsername", username);
		req.setAttribute("registerName", name);
		req.setAttribute("registerSurname", surname);
		req.setAttribute("registerEmail", email);

		if (check) {

			User user = UserManager.getInstance().register(name, surname, email, username, password, multipart.pictures.get(0));

			if (user != null) {

				setLoggedInUser(req, user);
				resp.sendRedirect(Utils.addParameterToURI(destination, "registerAction", "successful"));
				return;
			}
		}

		render(req, resp, "login.jsp", "Login or Register", false);
	}
}
