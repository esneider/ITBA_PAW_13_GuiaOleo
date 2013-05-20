package ar.edu.itba.it.paw.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

public class TransactionFilter extends OncePerRequestFilter {

	private SessionFactory sessionFactory;
	
	@Autowired
	public TransactionFilter(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {

			sessionFactory.getCurrentSession().beginTransaction();
			filterChain.doFilter(request, response);
			sessionFactory.getCurrentSession().getTransaction().commit();

		} catch (Throwable ex) {

			try {
				if (sessionFactory.getCurrentSession().getTransaction().isActive())
					sessionFactory.getCurrentSession().getTransaction().rollback();
			} catch (Throwable rbEx) {
				rbEx.printStackTrace();
			}

			throw new ServletException(ex);
		}
	}
}
