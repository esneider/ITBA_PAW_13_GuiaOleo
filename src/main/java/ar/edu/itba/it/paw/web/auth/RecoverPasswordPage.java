package ar.edu.itba.it.paw.web.auth;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.service.exception.MailConfigurationException;
import ar.edu.itba.it.paw.service.mail.MailService;
import ar.edu.itba.it.paw.web.base.NoSideBarPage;

public class RecoverPasswordPage extends NoSideBarPage {

	private transient String username;
	
	@SpringBean
	private MailService mailer;
	
	@SpringBean
	private UserRepo userRepo;
	
	public RecoverPasswordPage() {
		super(false);
		
		FeedbackPanel panel = new FeedbackPanel("recoverFeedback");
		panel.setFilter(new ContainerFeedbackMessageFilter(this));

		add(panel);
		
		Form<RecoverPasswordPage> form = new Form<RecoverPasswordPage>("recoverForm",
				new CompoundPropertyModel<RecoverPasswordPage>(this)) {

			private static final long serialVersionUID = -7094369998728459726L;

			@Override
			protected void onSubmit() {
				if (username != null) {
					User u = userRepo.get(username);
					if (u == null) {
						error(getString("nonExistingUser"));
					} else {
						try {
							mailer.sendRecoveryMail(u, (HttpServletRequest) getRequest().getContainerRequest());
							setResponsePage(getApplication().getHomePage());
						} catch (AddressException e) {
							e.printStackTrace();
						} catch (MessagingException e) {
							e.printStackTrace();
						} catch (MailConfigurationException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		
		form.add(new TextField("username").setRequired(true));
		form.add(new Button("recover", new ResourceModel("recover")));
	
		add(form);
	}

}
