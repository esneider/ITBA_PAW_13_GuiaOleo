package ar.edu.itba.it.paw.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import ar.edu.itba.it.paw.web.services.MailSender;


public class ErrorFilter implements Filter {

    private static Logger logger = Logger.getLogger(ErrorFilter.class);

    @Override
    public void init(FilterConfig arg0) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        try {

            chain.doFilter(request, response);

        } catch (HibernateException e) {

            logger.error(e.getMessage(), e.fillInStackTrace());

            HttpServletResponse r = (HttpServletResponse) response;
            r.setStatus(500);
            sendMail(e);
            request.getRequestDispatcher("/WEB-INF/jsp/dberror.jsp").forward(request, r);

        } catch (Exception e) {

            sendMail(e);
            HttpServletResponse r = (HttpServletResponse) response;
            r.setStatus(500);
            logger.error(e.getMessage(), e.fillInStackTrace());
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

    private void sendMail(Exception e) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        if (!MailSender.send(sw.toString())) {
        	logger.error("Error Sending mail");
        }
    }
}

