package ar.edu.itba.it.paw.web;

import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.web.common.HibernateRequestCycleListener;

@Component
public class RestaurantApplication extends WebApplication {

	private final SessionFactory sessionFactory;
	
	@Autowired
	public RestaurantApplication(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	protected void init() {
		super.init();
		getRequestCycleListeners().add(new HibernateRequestCycleListener(sessionFactory));
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}
	
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new RestaurantWicketSession(request);
	}

	@Override
	protected IConverterLocator newConverterLocator() {
		/*ConverterLocator converterLocator = new ConverterLocator();
		converterLocator.set(Professor.class, new ProfessorConverter());
		converterLocator.set(Department.class, new DepartmentConverter());
		converterLocator.set(Subject.class, new SubjectConverter(subjects));
		return converterLocator;*/
		return null;
	}

}
