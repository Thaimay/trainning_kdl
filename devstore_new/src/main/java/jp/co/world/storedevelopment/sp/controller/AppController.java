package jp.co.world.storedevelopment.sp.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jp.co.world.storedevelopment.logging.LogUtil;
import jp.co.world.storedevelopment.model.Account;

public abstract class AppController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HttpSession session;

	@Autowired
	private HttpServletRequest request;

	protected Account getAccount() {
		return (Account) session.getAttribute(Account.SESSION);
	}

	protected ServletContext getServletContext() {
		return session.getServletContext();
	}

	private void logInfo(String methodName, String msg) {
		logger.info("{}.{} - {}", getClass().getName(), methodName, msg);
	}

	public void logStartMethod(String methodName) {
		String param = LogUtil.getParameterFromRequest(request);

		if (param.isEmpty()) {
			logInfo(methodName, "メソッドの開始");
		} else {
			logInfo(methodName, "メソッドの開始 - Parameter: " + param);
		}

	}

	public void logStartMethod(String methodName, Object obj) {
		logInfo(methodName, "メソッドの開始 - Parameter: " + LogUtil.toJSON(obj));
	}

	public void logEndMethod(String methodName) {
		logInfo(methodName, "メソッドの終了");
	}

	private void logError(String methodName, String msg) {
		logger.error("{}.{} - {}", getClass().getName(), methodName, msg);
	}

	public void logException(String methodName, String exMsg) {
		logError(methodName, String.format("%s: %s", "例外", exMsg));
	}
}
