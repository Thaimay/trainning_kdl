package jp.co.world.storedevelopment.pc.security;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

@Configuration
public class AuthenticationFilter implements AuthenticationProvider {
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String code = (String) auth.getPrincipal();
		Optional<Account> account = new AccountRepository().findByCode(code);

		if (account.isPresent()) {
			return new UsernamePasswordAuthenticationToken(account.get(), new NonPasswordEncoder());
		} else {
			throw new BadCredentialsException("Authentication Error");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
