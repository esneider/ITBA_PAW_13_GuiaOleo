package ar.edu.itba.it.paw.web.common;

import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import ar.edu.itba.it.paw.service.mail.RestaurantMailService;

public class ErrorRequestCycleListener extends AbstractRequestCycleListener {

	private RestaurantMailService mailer = new RestaurantMailService();

	@Override
	public IRequestHandler onException(RequestCycle cycle, Exception ex) {
		
		mailer.sendErrorMail(ex);
		return null;
	}
	
	
}
