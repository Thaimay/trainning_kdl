package jp.co.world.storedevelopment.pc.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

@Component("PcUserDetailsService")
public class AccountAuthService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
		return new AccountRepository().findByCode(code)
				.orElseThrow(() -> new UsernameNotFoundException("存在しないアカウントコードです:" + code));
	}

}
