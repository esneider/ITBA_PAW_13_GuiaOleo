package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.WebPage;

import ar.edu.itba.it.paw.web.RestaurantWicketSession;

@SuppressWarnings("serial")
public class BasePage extends WebPage {
	
	public BasePage() {
		RestaurantWicketSession session = RestaurantWicketSession.get();
		if (session.isSignedIn()) {
			add(new LoggedHeaderPanel("header"));
		} else {
			add(new LoggedHeaderPanel("header"));
		}
	}
	
//	@Override
//	public void renderHead(IHeaderResponse response) {
//		response.renderCSSReference(new PackageResourceReference(RestaurantApplication.class, "style.css"));
//		response.renderCSSReference(new PackageResourceReference(RestaurantApplication.class, "bootstrap.css"));
//		response.renderCSSReference(new PackageResourceReference(RestaurantApplication.class, "bootstrap.min.css"));
//		
//		response.renderJavaScriptReference(new PackageResourceReference(RestaurantApplication.class, "bootstrap.js"));
//		response.renderJavaScriptReference(new PackageResourceReference(RestaurantApplication.class, "js.js"));
//		response.renderJavaScriptReference(new PackageResourceReference(RestaurantApplication.class, "bootstrap.min.js"));
//	}
}
