package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.PackageResourceReference;

import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.application.RestaurantApplication;

@SuppressWarnings("serial")
public class BasePage extends WebPage {
	
	public BasePage() {
		RestaurantWicketSession session = RestaurantWicketSession.get();
		if (session.isSignedIn()) {
			add(new LoggedHeaderPanel("header"));
		} else {
			add(new UnLoggedHeaderPanel("header"));
		}
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		response.renderCSSReference(new PackageResourceReference(RestaurantApplication.class, "style.css"));
		response.renderCSSReference(new PackageResourceReference(RestaurantApplication.class, "bootstrap.css"));
		
		response.renderJavaScriptReference(new PackageResourceReference(RestaurantApplication.class, "jquery.js"));
		response.renderJavaScriptReference(new PackageResourceReference(RestaurantApplication.class, "bootstrap.js"));
		response.renderJavaScriptReference(new PackageResourceReference(RestaurantApplication.class, "bootstrap-modal.js"));
		response.renderJavaScriptReference(new PackageResourceReference(RestaurantApplication.class, "maps.js"));
	}
}
