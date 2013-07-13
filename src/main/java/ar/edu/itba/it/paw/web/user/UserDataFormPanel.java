package ar.edu.itba.it.paw.web.user;

import java.util.List;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.lang.Bytes;


@SuppressWarnings("unused")
public class UserDataFormPanel extends Panel {

	private static final long serialVersionUID = 1003216339451423879L;

	public UserDataFormPanel(String id) {

		super(id);

		add(new PasswordTextField("password").setRequired(false));
		add(new PasswordTextField("repassword").setRequired(false));
		add(new TextField<String>("name"));
		add(new TextField<String>("surname"));
		add(new TextField<String>("email"));
		add(new FileUploadField("avatar"));
	}
}
