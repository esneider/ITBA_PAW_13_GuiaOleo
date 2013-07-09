package ar.edu.itba.it.paw.web.auth;

import java.util.Date;
import java.util.List;

import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Bytes;

import ar.edu.itba.it.paw.domain.picture.Picture;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.domain.user.UserType;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;

public class RegisterPanel extends Panel {

	private static final long serialVersionUID = 8933293746150678035L;

	@SpringBean
	private UserRepo userRepo;

    private transient String name;
    private transient String surname;
    private transient String email;
    private transient String username;
    private transient String password;
    private transient String repassword;
    private transient List<FileUpload> avatar;

	public RegisterPanel(String id) {

		super(id);
	
		FeedbackPanel panel = new FeedbackPanel("registerFeedback");
		panel.setFilter(new ContainerFeedbackMessageFilter(this));

		add(panel);

		Form<RegisterPanel> form = new Form<RegisterPanel>("registerForm", new CompoundPropertyModel<RegisterPanel>(this)) {

			private static final long serialVersionUID = 1455744470158143935L;

			@Override
			protected void onSubmit() {

				name = Utils.normalizeString(name);
				surname = Utils.normalizeString(surname);
				email = Utils.normalizeString(email);
				username = Utils.normalizeString(username);
				password = Utils.normalizeString(password);
				repassword = Utils.normalizeString(repassword);
	
				if (name.isEmpty()) {
					error(getString("emptyName"));
				}
				if (surname.isEmpty()) {
					error(getString("emptySurname"));
				}
				if (email.isEmpty()) {
					error(getString("emptyEmail"));
				} else
				if (!Utils.isEmail(email)) {
					error(getString("badformatEmail"));
				} else
				if (userRepo.emailExists(email)) {
					error(getString("duplicatedEmail"));
				}
				if (username.isEmpty()) {
					error(getString("emptyUsername"));
				} else
				if (username.length() > 10) {
					error(getString("toolongUsername"));
				} else
				if (userRepo.usernameExists(username)) {
					error(getString("duplicatedUsername"));
				}
				if (password.isEmpty()) {
					error(getString("emptyPassword"));
				} else
				if (repassword.isEmpty()) {
					error(getString("emptyRepassword"));
				} else
				if (!password.equals(repassword)) {
					error(getString("mismatchRepassword"));
				}
				if (avatar == null || avatar.size() != 1) {
					error(getString("emptyAvatar"));
				} else
				if (!"image/jpeg".equals(avatar.get(0).getContentType()) &&
					!"image/jpg".equals(avatar.get(0).getContentType())) {

					error(getString("invalidformatAvatar"));
				}
				
				if (!hasError()) {
					
					Picture avatarPic = new Picture(avatar.get(0).getBytes(), avatar.get(0).getContentType());
					User user = new User(name, surname, email, username, password, avatarPic, new Date(), UserType.Normal);
					userRepo.save(user);
					
					RestaurantWicketSession session = RestaurantWicketSession.get();

					if (session.signIn(username, password, userRepo)) {
						if (!continueToOriginalDestination()) {
							setResponsePage(getApplication().getHomePage());
						}
						return;						
					}
					setResponsePage(getApplication().getHomePage());
				}
			}
		};
		
		form.add(new TextField<String>("username"));
		form.add(new PasswordTextField("password"));
		form.add(new PasswordTextField("repassword"));
		form.add(new TextField<String>("name"));
		form.add(new TextField<String>("surname"));
		form.add(new TextField<String>("email"));
		form.setMultiPart(true);
		form.setMaxSize(Bytes.megabytes(10));
		form.add(new FileUploadField("avatar"));
		form.add(new Button("register", new ResourceModel("register")));
		add(form);
	}
}
