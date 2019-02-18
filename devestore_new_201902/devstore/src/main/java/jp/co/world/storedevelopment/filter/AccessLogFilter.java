package jp.co.world.storedevelopment.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.world.storedevelopment.logging.db.AccessDBInfo;

public class AccessLogFilter implements Filter {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// [時間] [ユーザー名（ある場合）] [IP] [URL] [画面名] [処理名（ある場合）] [クエリ（ある場合）] [端末]
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestUri = httpRequest.getRequestURI();

		if (!requestUri.endsWith(".js") && !requestUri.endsWith(".css")
				&& (requestUri.startsWith("/wsd/pc/") || requestUri.startsWith("/wsd/sp/"))) {
			logger.info(new AccessDBInfo(httpRequest).toString());
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
