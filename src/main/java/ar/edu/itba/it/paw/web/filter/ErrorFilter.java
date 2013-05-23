package ar.edu.itba.it.paw.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ar.edu.itba.it.paw.domain.exceptions.SQLNoConnectionException;

public class ErrorFilter implements Filter {

	private static Logger logger = Logger.getLogger(ErrorFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {

			chain.doFilter(request, response);

		} catch (SQLNoConnectionException e) {

			logger.error(e.getMessage(), e.fillInStackTrace());

			HttpServletResponse r = (HttpServletResponse) response;
			r.setStatus(500);
			request.getRequestDispatcher("/WEB-INF/jsp/dberror.jsp").forward(request, r);

		} catch (Exception e) {

			logger.error(e.getMessage(), e.fillInStackTrace());

			//e.printStackTrace();
			//request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}

	}
}
