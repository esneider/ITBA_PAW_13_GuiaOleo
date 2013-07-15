package ar.edu.itba.it.paw.service.mail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.service.exception.MailConfigurationException;


public interface MailService {

	public void sendRecoveryMail(User user, HttpServletRequest context)
			throws AddressException, MessagingException,
			MailConfigurationException;

	public void sendErrorMail(Exception e) throws AddressException,
			MessagingException, MailConfigurationException;

}
