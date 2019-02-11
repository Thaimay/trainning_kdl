package jp.co.world.storedevelopment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class PcAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(code, "");

		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
