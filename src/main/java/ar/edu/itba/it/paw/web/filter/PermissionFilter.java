package ar.edu.itba.it.paw.web.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class PermissionFilter implements Filter {

	private static Logger logger = Logger.getLogger(PermissionFilter.class);
	
	private class Resource implements Comparable<Resource> {

		String path, method;

		public Resource(String path, String method) {
			this.path = path;
			this.method = method;
		}

		@Override
		public int compareTo(Resource other) {

			int ret = path.compareTo(other.path);

			return ret != 0 ? ret : method.compareTo(other.method);
		}

		@Override
		public boolean equals(Object obj) {

			Resource other = (Resource) obj;
			return path == other.path || method == other.method;
		}
	}

	private Set<Resource> restrictedActions = new TreeSet<Resource>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		String actionsString = filterConfig
				.getInitParameter("restricted-actions");

		if (actionsString == null) {

			return;
		}

		String[] actions = actionsString.split(",");

		for (String action : actions) {

			String[] parts = action.split(":");

			if (parts.length != 2) {
				break;
			}

			restrictedActions
					.add(new Resource(parts[0], parts[1].toUpperCase()));
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {

			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			
			String s = "{";
			for (Resource r : restrictedActions) {
				s += ("[" + r.path + "," + r.method + "],");
			}
			s += ("} => [" + req.getServletPath() + ","
					+ req.getMethod().toUpperCase() + "]");

			logger.warn(s);

			if (restrictedActions.contains(new Resource(req.getServletPath(),
					req.getMethod().toUpperCase()))) {

				logger.warn("RESTRICTED");

				Integer id = (Integer) req.getSession().getAttribute("userId");

				if (id == null) {

					logger.warn("- You shall not pass!!!");

					String from = req.getRequestURI();

					if (req.getQueryString() != null) {
						from += "?" + req.getQueryString();
					}

					logger.warn("Set destination: "
							+ URLEncoder.encode(from, "UTF-8"));

					resp.sendRedirect(req.getContextPath() + "/login?from="
							+ URLEncoder.encode(from, "UTF-8"));
					return;
				}
			}
		}

		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {

	}

}
