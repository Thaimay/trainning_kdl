package jp.co.world.storedevelopment.logging.file;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.world.storedevelopment.model.Account;

public class AccessFileInfo {

	private String userName = "";
	private String userCode = "";
	private String userInfo = "";
	private String hostName = "";
	private String requestUri = "";

	public AccessFileInfo(HttpServletRequest request) {
		if (request != null) {
			HttpSession session = request.getSession(false);

			if (session != null && session.getAttribute(Account.USER_CODE) != null
					&& session.getAttribute(Account.USER_NAME) != null) {
				userName = session.getAttribute(Account.USER_NAME).toString();
				userCode = session.getAttribute(Account.USER_CODE).toString();
				userInfo = String.format("%s(%s)", userName, userCode);
			}

			if (request.getHeader("host") != null) {
				hostName = request.getHeader("host");
			}

			if (request.getRequestURI() != null) {
				requestUri = request.getRequestURI();
			}
		}
	}

	public String toString() {
		return String.join("、", Arrays.asList(userInfo, hostName, requestUri).stream().filter(x -> !x.isEmpty())
				.collect(Collectors.toList()));
	}
}
