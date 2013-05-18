package ar.edu.itba.it.paw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.exceptions.SQLNoConnectionException;

public class ErrorFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {
			chain.doFilter(request, response);
		} catch (SQLNoConnectionException e) {
			HttpServletResponse r = (HttpServletResponse) response;
			r.setStatus(500);
			request.getRequestDispatcher("/WEB-INF/jsp/dberror.jsp")
					.forward(request, r);
		} catch (Exception e) {
			e.printStackTrace();
			//request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(
				//	request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
