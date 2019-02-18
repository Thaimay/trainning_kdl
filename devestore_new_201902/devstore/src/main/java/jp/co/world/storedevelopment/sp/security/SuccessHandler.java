package jp.co.world.storedevelopment.sp.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.world.storedevelopment.logging.LoginResult;
import jp.co.world.storedevelopment.logging.db.AccessDBInfo;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.WorldAuthAccount;

@Component("SpSuccessHandler")
public class SuccessHandler implements AuthenticationSuccessHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if (setLoginAccount(request, authentication)) {
			logger.info(new AccessDBInfo(request, LoginResult.SUCCESS).toString());

			boolean role = (boolean) request.getSession(true).getAttribute(Account.USER_ROLE);
			if (role) {
				response.setStatus(HttpStatus.OK.value());
				response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
				response.getWriter().write(createAccountJson(authentication));
			} else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
				response.getWriter().write("{ \"hasRole\":false }");
			}

			response.getWriter().flush();
		} else {
			logger.info(new AccessDBInfo(request, LoginResult.FAIL).toString());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType(MediaType.TEXT_PLAIN_VALUE);
			response.getWriter().write("user not found in system");
			response.getWriter().flush();
		}

	}

	private String createAccountJson(Authentication authentication) {
		WorldAuthAccount wAccount = getAccount(authentication);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(wAccount.createAuthJson());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}
	}

	private WorldAuthAccount getAccount(Authentication authentication) {
		return (WorldAuthAccount) authentication.getPrincipal();
	}

	private boolean setLoginAccount(HttpServletRequest request, Authentication authentication) {
		WorldAuthAccount wAccount = getAccount(authentication);
		Optional<Account> account = wAccount.getSystemAccount();
		Logger log = LoggerFactory.getLogger(getClass());

		if (account.isPresent() == false) {
			log.info(String.format("社員コード[%s]はアカウントが存在していません。", wAccount.getSyaincd()));
			return false;
		} else if (account.get().hasRole() == null) {
			log.info(String.format("社員コード[%s]は権限がありません", account.get().getEmployeCode()));
			return false;
		} else if (account.get().usePhone() == false) {
			log.info(String.format("社員コード[%s]はスマホでのアクセスが許可されていません", account.get().getEmployeCode()));
			return false;
		} else {
			request.getSession(true).setAttribute(Account.SESSION, account.get());
			request.getSession(true).setAttribute(Account.USER_CODE, account.get().getEmployeeCd());
			request.getSession(true).setAttribute(Account.USER_NAME, account.get().getFullName());
			request.getSession(true).setAttribute(Account.USER_ROLE, true);

			return true;			
		}
	}

}
