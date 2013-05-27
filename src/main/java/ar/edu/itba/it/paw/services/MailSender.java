package ar.edu.itba.it.paw.services;

import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	public static boolean send(String msg) {
		try {

			/*
			 * Retrieve value from the text field using getParameter() method on
			 * Request object. Otherwise you can set it directly also if you are
			 * not using any interface
			 */

			// final String to="tlorestotlosumo@gmail.com";
			// final String subject="Lindo asunto";
			// final String user="tlorestotlosumo@gmail.com";
			// final String pass="tloresto123456";

			// create an instance of Properties Class
			final Properties props = new Properties();
			String current = new java.io.File(".").getCanonicalPath();
			FileInputStream fis = new FileInputStream(current
					+ "/src/main/resources/mail.properties");
			props.loadFromXML(fis);
			/*
			 * Specifies the IP address of your default mail server for e.g if
			 * you are using gmail server as an email sever you will pass
			 * smtp.gmail.com as value of mail.smtp host. As shown here in the
			 * coding. Change accordingly, if your email id is not an gmail id
			 */

			// props.put("mail.smtp.host", "smtp.gmail.com");
			// props.put("mail.smtp.port", "587"); //this is optional
			// props.put("mail.smtp.auth", "true");
			// props.put("mail.smtp.starttls.enable", "true");

			/*
			 * Pass Properties object(props) and Authenticator object for
			 * authentication to Session instance
			 */

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(props
									.getProperty("user"), props
									.getProperty("pass"));
						}
					});

			/*
			 * Create an instance of MimeMessage, it accept MIME types and
			 * headers
			 */

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("user")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					props.getProperty("to")));
			message.setSubject(props.getProperty("subject"));
			message.setText(msg);

			/* Transport class is used to deliver the message to the recipients */

			Transport.send(message);

		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
