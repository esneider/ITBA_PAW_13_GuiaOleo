package ar.edu.itba.it.paw.web.auth;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.base.NoSideBarPage;
import ar.edu.itba.it.paw.web.restaurant.RestaurantListPage;
import ar.edu.itba.it.paw.web.user.TwoPasswordPanel;

public class ResetPasswordPage extends NoSideBarPage {

	@SpringBean
	private UserRepo userRepo;
	
	private transient String password;
	private transient String repassword;

	public ResetPasswordPage(PageParameters params) {
		super(false);
		String token = params.get("token").toString();
		if (token == null || token.equals("")) {
			setResponsePage(getApplication().getHomePage());
			return;
		}
		final User u = userRepo.getByToken(token);
		if (u == null) {
			setResponsePage(RestaurantListPage.class);
			return;
		}
			
		Form<ResetPasswordPage> form = new Form<ResetPasswordPage>("resetForm"){
			@Override
			protected void onSubmit() {
				password = Utils.normalizeString(password);
				repassword = Utils.normalizeString(repassword);
				
				if (password.equals("")) {
					error("emptyPassword");
				}
				if (repassword.equals("")) {
					error("emptyRePassword");
				}
				if (!password.equals(repassword)) {
					error("passwordsMismatch");
				}
				
				if (!hasError()) {
					u.setPassword(password);
					u.clearToken();
					setResponsePage(RestaurantListPage.class);
				}
					
			}
		};
		
		form.add(new TwoPasswordPanel("passwordPanel"));
		add(form);
	}
}
