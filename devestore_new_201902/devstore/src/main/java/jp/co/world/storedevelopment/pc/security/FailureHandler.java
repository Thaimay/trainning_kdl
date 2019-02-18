package jp.co.world.storedevelopment.pc.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jp.co.world.storedevelopment.logging.LoginResult;
import jp.co.world.storedevelopment.logging.db.AccessDBInfo;

@Component("PcFailureHandler")
public class FailureHandler implements AuthenticationFailureHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info(new AccessDBInfo(request, LoginResult.FAIL).toString());
		response.sendRedirect(request.getContextPath() + "/pc/login/error");
	}

}
