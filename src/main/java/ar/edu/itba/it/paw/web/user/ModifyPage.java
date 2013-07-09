package ar.edu.itba.it.paw.web.user;

import ar.edu.itba.it.paw.web.auth.LoginPanel;
import ar.edu.itba.it.paw.web.auth.RegisterPanel;
import ar.edu.itba.it.paw.web.base.NoSideBarPage;

public class ModifyPage extends NoSideBarPage {

	public ModifyPage() {
		super(true);
		add(new ModifyPanel("modify"));
	}
}
