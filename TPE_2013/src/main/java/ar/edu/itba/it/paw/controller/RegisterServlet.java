package ar.edu.itba.it.paw.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

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
import com.oreilly.servlet.MultipartRequest;

@SuppressWarnings("serial")
public class RegisterServlet extends BaseServlet {

	private static final String tmp = "./src/main/webapp/assets/tmp/";

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

		/*MultipartRequest formHandler = new MultipartRequest(req, tmp);
		Enumeration paramList = formHandler.getParameterNames();
		for (; paramList.hasMoreElements();) {
			String paramName = (String) paramList.nextElement();
			System.out.println("Ahi va esto:" + paramName
					+ (String) formHandler.getParameter(paramName));
			req.setAttribute(paramName,
					(String) formHandler.getParameter(paramName));
		}*/
		// Ok, and then , the we store the uploaded files
		/*paramList = formHandler.getFileNames();
		for (; paramList.hasMoreElements();) {
			String paramName = (String) paramList.nextElement();
			String fileName = null;
			fileName = formHandler.getFilesystemName(paramName);
			f = new File(tmp + filepart.getFileName());
			f = formHandler.getFile(fileName);
			if (fileName != null) {
				System.out.println("Ahi va esto:" + paramName
						+ (String) fileName);
			}
		}*/

		MultipartParser mp = new MultipartParser(req, 10 * 1024 * 1024);
		Part part;
		File f = null;

		while ((part = mp.readNextPart()) != null) {
			if (part.isFile()) {
				FilePart filepart = (FilePart) part;
				f = new File(tmp + filepart.getFileName());
				if (!f.exists())
					f.createNewFile();
				filepart.writeTo(f);
			} else if (part.isParam()) {

			}
		}

		req.setAttribute("registerUsername", username);
		req.setAttribute("registerName", name);
		req.setAttribute("registerSurname", surname);
		req.setAttribute("registerEmail", email);
		req.setAttribute("registerAvatar", f);

		if (true) {

			FileInputStream fs = new FileInputStream(f);
			/*
			 * User user = UserManager.getInstance().register(name, surname,
			 * email, username, password, fs, (int)f.length());
			 */

			User user = UserManager.getInstance().register("asd", "asd", "asd",
					"asd", "asd", fs, (int) f.length(), f.getName());

			System.out.println(user);

			f.delete();

			if (user != null) {

				setLoggedInUser(req, user);
				resp.sendRedirect(Utils.addParameterToURI(destination,
						"registerAction", "successful"));
				return;
			}
		}

		render(req, resp, "login.jsp", "Login or Register", false);
	}

	/*
	 * public void stringToFile(String archivo, File dir) { FileWriter fwriter =
	 * null; BufferedWriter bwriter = null; try { fwriter = new FileWriter(dir);
	 * bwriter = new BufferedWriter(fwriter); bwriter.write(archivo);
	 * bwriter.close(); fwriter.close(); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */
}
