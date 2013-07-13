package ar.edu.itba.it.paw.service.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.service.exception.MailConfigurationException;

@Component
public class RestaurantMailService implements MailService {

	private static String FROM = "admin@oleosguide.com";
	private static String ERROR_TO = "lmoscovicz@gmail.com";

	@Override
	public void sendRecoveryMail(User user, HttpServletRequest request)
			throws AddressException, MessagingException,
			MailConfigurationException {
		String token = RandomStringUtils.randomAlphabetic(20);
		user.setToken(token);

		String url = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath()
				+ "/bin/resetPassword?token=" + user.getToken();
		String content = "Click<a href=\"" + url
				+ "\"> here </a>to reset your password.";

		send(FROM, user.getEmail(), "Recover your password", content,
				"text/html");

	}
	@Override
	public void sendErrorMail(Exception e) throws AddressException,
			MessagingException, MailConfigurationException {
		send(FROM, ERROR_TO, "Exception at Oleo's", e.getStackTrace()
				.toString(), "text/html");
	}

	private void send(String from, String to, String subject, String content,
			String contentType) throws AddressException, MessagingException,
			MailConfigurationException {
		Properties props = new Properties();
		try {
			InputStream propsIs = getClass().getClassLoader()
					.getResourceAsStream("mail.properties");
			props.load(propsIs);
		} catch (IOException ex) {
			throw new MailConfigurationException();
		}

		Session session = Session.getInstance(props);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setContent(content, contentType);
		Transport.send(message, message.getAllRecipients());
	}

}
