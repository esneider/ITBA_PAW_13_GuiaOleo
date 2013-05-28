package ar.edu.itba.it.paw.web.services;

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
			
			String current = new java.io.File(".").getCanonicalPath();
			FileInputStream fis = new FileInputStream(current
					+ "/src/main/resources/mail.properties");

			final Properties props = new Properties();
			props.load(fis);
			

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(props
									.getProperty("user"), props
									.getProperty("pass"));
						}
					});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("user")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					props.getProperty("to")));
			message.setSubject(props.getProperty("subject"));
			message.setText(msg);

			Transport.send(message);

		} catch (Exception e) {
			return false;
		}
		return true;
	}
}


