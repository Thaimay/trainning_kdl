package jp.co.world.storedevelopment.pc.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jp.co.world.storedevelopment.logging.LoginResult;
import jp.co.world.storedevelopment.logging.db.AccessDBInfo;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Role;

@Component("PcSuccessHandler")
public class SuccessHandler implements AuthenticationSuccessHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if (setLoginAccount(request, authentication)) {
			logger.info(new AccessDBInfo(request, LoginResult.SUCCESS).toString());
			response.sendRedirect(request.getContextPath() + "/pc/");
		} else {
			logger.info(new AccessDBInfo(request, LoginResult.FAIL).toString());
			response.sendRedirect(request.getContextPath() + "/pc/login/roleError");
		}
	}

	private Boolean setLoginAccount(HttpServletRequest request, Authentication authentication) {
		Account account = (Account) authentication.getPrincipal();
		Optional<Role> role = Optional.ofNullable(account.getRole());

		if (role.isPresent()) {
			request.getSession(true).setAttribute(Account.SESSION, account);
			request.getSession(true).setAttribute(Role.SESSION, role);
			request.getSession(true).setAttribute(Account.USER_CODE, account.getEmployeeCd());
			request.getSession(true).setAttribute(Account.USER_NAME, account.getFullName());
			request.getSession(true).setAttribute("version", version());
			return true;
		} else {
			return false;
		}
	}

	private static String version() {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream is = classLoader.getResource("application.properties").openStream();
			Properties properties = new Properties();
			properties.load(is);
			return properties.getProperty("application.version");
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("application.propertiesが存在しません");
		}
	}

}
