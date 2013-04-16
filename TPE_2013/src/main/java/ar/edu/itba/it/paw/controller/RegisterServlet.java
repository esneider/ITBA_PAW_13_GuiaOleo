package ar.edu.itba.it.paw.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.UserManager;
import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.utils.ValidationHelpers;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;

@SuppressWarnings("serial")
public class RegisterServlet extends BaseServlet {

	private static final String tmp = "/assets/tmp/";

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

		check &= ValidationHelpers
				.checkUsername(req, "registerUsername", 0, 50);
		check &= ValidationHelpers.checkParameter(req, "registerPassword", 0,
				64);
		check &= ValidationHelpers.checkParameter(req, "registerRePassword", 0,
				64);
		check &= ValidationHelpers.checkParameter(req, "registerName", 0, 50);
		check &= ValidationHelpers
				.checkParameter(req, "registerSurname", 0, 50);
		check &= ValidationHelpers.checkEmail(req, "registerEmail", 0, 50);
		check &= check
				&& ValidationHelpers.checkParamsEqual(req, "registerPassword",
						"registerRePassword");

		String username = req.getParameter("registerUsername");
		String password = req.getParameter("registerPassword");
		String name = req.getParameter("registerName");
		String surname = req.getParameter("registerSurname");
		String email = req.getParameter("registerEmail");

		InputStream is = null;
		
		MultipartParser mp = new MultipartParser(req, 10 * 1024 * 1024);
		Part part;

		while ((part = mp.readNextPart()) != null) {
			if (part.isFile()) {
				FilePart filepart = (FilePart) part;
				is = filepart.getInputStream();
			}
		}

		req.setAttribute("registerAvatar", is);
		req.setAttribute("registerUsername", username);
		req.setAttribute("registerName", name);
		req.setAttribute("registerSurname", surname);
		req.setAttribute("registerEmail", email);
		
		if (check) {

			User user = UserManager.getInstance().register(name, surname,
					email, username, password, is);
			
			System.out.println(user);

			if (user != null) {

				setLoggedInUser(req, user);
				resp.sendRedirect(Utils.addParameterToURI(destination,
						"registerAction", "successful"));
				return;
			}
		}

		render(req, resp, "login.jsp", "Login or Register");
	}

	public void stringToFile(String archivo, File dir) {
		FileWriter fwriter = null;
		BufferedWriter bwriter = null;
		try {
			fwriter = new FileWriter(dir);
			bwriter = new BufferedWriter(fwriter);
			bwriter.write(archivo);
			bwriter.close();
			fwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
