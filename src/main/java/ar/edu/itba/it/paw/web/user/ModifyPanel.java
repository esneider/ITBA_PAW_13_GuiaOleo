package ar.edu.itba.it.paw.web.user;

import java.util.List;

import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.picture.Picture;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;

public class ModifyPanel extends Panel {

	private static final long serialVersionUID = 3547021068873798286L;

	@SpringBean
	private UserRepo userRepo;

    private transient String name;
    private transient String surname;
    private transient String email;
    private transient String oldpassword;
    private transient String password;
    private transient String repassword;
    private transient List<FileUpload> avatar;

	public ModifyPanel(String id) {

		super(id);
	
		FeedbackPanel panel = new FeedbackPanel("feedback");
		panel.setFilter(new ContainerFeedbackMessageFilter(this));

		add(panel);

		Form<ModifyPanel> form = new Form<ModifyPanel>("modifyForm", new CompoundPropertyModel<ModifyPanel>(this)) {

			private static final long serialVersionUID = -4729945112106897386L;

			@Override
			protected void onSubmit() {

				oldpassword = Utils.normalizeString(oldpassword);
				name = Utils.normalizeString(name);
				surname = Utils.normalizeString(surname);
				email = Utils.normalizeString(email);
				password = Utils.normalizeString(password);
				repassword = Utils.normalizeString(repassword);

				Picture picture = null;
				

				RestaurantWicketSession session = RestaurantWicketSession.get();
				User user = session.getUser();

				if (user == null) {

					setResponsePage(getApplication().getHomePage());
					return;
				}

				if (name.isEmpty())
				if (surname.isEmpty())
				if (email.isEmpty())
				if (password.isEmpty())
				if (repassword.isEmpty())
				if (avatar == null || avatar.isEmpty()) {

					setResponsePage(getApplication().getHomePage());
					return;
				}

				if (oldpassword.isEmpty()) {
					
					error(getString("emptyOldPassword"));
					
				} else
				if (!oldpassword.equals(user.getPassword())) {
					
					error(getString("mismatchOldPassword"));
				}

				if (!email.isEmpty()) {
					
					if (!Utils.isEmail(email)) {
						
						error(getString("badformatEmail"));
						
					} else
					if (userRepo.emailExists(email)) {
						
						error(getString("duplicatedEmail"));
					}
				}

				if (!password.isEmpty()) {
					
					if (!password.equals(repassword)) {
						
						error(getString("mismatchRepassword"));
					}
				}

				if (avatar != null && !avatar.isEmpty()) {

					if (avatar.size() != 1 || avatar.get(0) == null || avatar.get(0).getSize() == 0) { 
	
						error(getString("emptyOrMultipleAvatar"));
	
					} else
					if (!"image/jpeg".equals(avatar.get(0).getContentType()))
					if (!"image/jpg".equals(avatar.get(0).getContentType())) {
	
						error(getString("invalidformatAvatar"));
					}
				}

				if (hasError()) {

					updateFormComponentModels();
					
				} else {
					
					if (!name.isEmpty()) {
						user.setName(name);
					}
					if (!surname.isEmpty()) {
						user.setSurname(surname);
					}
					if (!email.isEmpty()) {
						user.setEmail(email);
					}
					if (!password.isEmpty()) {
						user.setPassword(password);
					}
					if (avatar != null && !avatar.isEmpty()) {
						picture = new Picture(avatar.get(0).getBytes(), avatar.get(0).getContentType());
						user.setAvatar(picture);
					}				

					continueToOriginalDestination();

					setResponsePage(getApplication().getHomePage());
				}
			}
		};
		
		form.add(new PasswordTextField("oldpassword").setRequired(false));
		form.add(new UserDataFormPanel("userDataForm"));
		form.add(new Button("modify", new ResourceModel("Modify")));
		add(form);
	}
}
