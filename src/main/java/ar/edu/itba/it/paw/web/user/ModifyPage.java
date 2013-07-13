package ar.edu.itba.it.paw.web.user;

import ar.edu.itba.it.paw.web.base.NoSideBarPage;

public class ModifyPage extends NoSideBarPage {

	private static final long serialVersionUID = -3866983774739588297L;

	public ModifyPage() {
		super(true);
		add(new ModifyPanel("modify"));
	}
}
