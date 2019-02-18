package jp.co.world.storedevelopment.sp.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jp.co.world.storedevelopment.model.mapper.repository.WorldAuthAccountRepository;

@Component("SpUserDetailsService")
public class AccountAuthService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return new WorldAuthAccountRepository().findByMail(email)
				.orElseThrow(() -> new UsernameNotFoundException("存在しないemailアドレスです:" + email));
	}

}
