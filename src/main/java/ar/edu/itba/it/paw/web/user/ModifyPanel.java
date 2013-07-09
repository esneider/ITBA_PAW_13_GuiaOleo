package ar.edu.itba.it.paw.web.user;

import java.util.List;

import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Bytes;

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

				if (!oldpassword.equals(user.getPassword())) {
					error(getString("mismatchOldPassword"));
				} else {
					if (!name.isEmpty()) {
						user.setName(name);
					}
					if (!surname.isEmpty()) {
						user.setSurname(surname);
					}
					if (!email.isEmpty()) {
						if (Utils.isEmail(email)) {
							if (!userRepo.emailExists(email)) {
								user.setEmail(email);
							} else {
								error(getString("duplicatedEmail"));
							}
						} else {
							error(getString("badformatEmail"));
						}
					}
					if (!password.isEmpty()) {
						if (password.equals(repassword)) {
							user.setPassword(password);
						} else {
							error(getString("mismatchRepassword"));
						}
					}
					if (avatar != null && avatar.size() == 1 && avatar.get(0) != null && avatar.get(0).getSize() > 0) {

						if ("image/jpeg".equals(avatar.get(0).getContentType()) ||
							"image/jpg".equals(avatar.get(0).getContentType())) {
	
							picture = new Picture(avatar.get(0).getBytes(), avatar.get(0).getContentType());
							user.setAvatar(picture);
						} else {
							error(getString("invalidformatAvatar"));
						}
					}
				}
				
				if (!hasError()) {
					
					if (!continueToOriginalDestination()) {
						setResponsePage(getApplication().getHomePage());
					}
					return;
				}
			}
		};
		
		form.add(new PasswordTextField("oldpassword").setRequired(false));
		form.add(new PasswordTextField("password").setRequired(false));
		form.add(new PasswordTextField("repassword").setRequired(false));
		form.add(new TextField<String>("name"));
		form.add(new TextField<String>("surname"));
		form.add(new TextField<String>("email"));
		form.setMultiPart(true);
		form.setMaxSize(Bytes.megabytes(10));
		form.add(new FileUploadField("avatar"));
		form.add(new Button("modify", new ResourceModel("Modify")));
		add(form);
	}
}
