package jp.co.world.storedevelopment.logging.db;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.world.storedevelopment.logging.LogCategory;
import jp.co.world.storedevelopment.logging.LoginResult;
import jp.co.world.storedevelopment.model.Account;

public class AccessDBInfo {

	private String category = LogCategory.ACCESS.toString();
	private String userName = "";
	private String userCode = "";
	private String requestUri = "";
	private String loginResult = "";

	public AccessDBInfo(HttpServletRequest request) {
		if (request != null) {
			HttpSession session = request.getSession(false);

			if (session != null && session.getAttribute(Account.USER_NAME) != null) {
				userName = session.getAttribute(Account.USER_NAME).toString();
			}

			if (session != null && session.getAttribute(Account.USER_CODE) != null) {
				userCode = session.getAttribute(Account.USER_CODE).toString();
			}

			if (request.getRequestURI() != null) {
				requestUri = request.getRequestURI();
			}
		}
	}

	public AccessDBInfo(HttpServletRequest request, LoginResult loginResult) {
		this(request);
		this.category = LogCategory.LOGIN.toString();
		this.loginResult = loginResult.toString();
	}

	public String toString() {
		return String.join("、", Arrays.asList(category, userName, userCode, requestUri, loginResult).stream()
				.collect(Collectors.toList()));
	}
}
