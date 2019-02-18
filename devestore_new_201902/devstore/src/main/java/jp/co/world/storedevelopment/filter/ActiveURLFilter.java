package jp.co.world.storedevelopment.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveURLFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(ActiveURLFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		String requestURI = hRequest.getRequestURI();
		String modeUrl = System.getProperty("activeUrl");

		if (modeUrl != null && modeUrl.equals("sp")) {
			if (!requestURI.startsWith("/wsd/pc")) {
				chain.doFilter(request, response);
			} else if (requestURI.startsWith("/wsd/pc")) {
				logger.debug("do filter url request from activeUrl");
				hResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

}
