package jp.co.world.storedevelopment.sp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jp.co.world.storedevelopment.logging.LoginResult;
import jp.co.world.storedevelopment.logging.db.AccessDBInfo;

@Component("SpFailureHandler")
public class FilureHandler implements AuthenticationFailureHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info(new AccessDBInfo(request, LoginResult.FAIL).toString());
		request.getSession().invalidate();
		response.setContentType(MediaType.TEXT_PLAIN_VALUE);
		response.getWriter().append("login failed : " + exception.getMessage());
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().flush();
	}

}
