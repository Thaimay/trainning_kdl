package jp.co.world.storedevelopment.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class MockAccountFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(MockAccountFilter.class);

	private Account account = new AccountRepository().getHead().get();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("init mock account filter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpSession session = hRequest.getSession();

		logger.info("do mock account filter");
		session.setAttribute(Account.SESSION, account);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
