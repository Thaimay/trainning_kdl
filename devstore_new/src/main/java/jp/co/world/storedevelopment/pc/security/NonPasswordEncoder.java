package jp.co.world.storedevelopment.pc.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class NonPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return "";
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return true;
	}

}
