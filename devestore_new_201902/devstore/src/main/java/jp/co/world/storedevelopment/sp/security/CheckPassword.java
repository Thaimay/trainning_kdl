package jp.co.world.storedevelopment.sp.security;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class CheckPassword extends MessageDigestPasswordEncoder {
	public CheckPassword() {
		super("MD5");
	}

	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		String pass1 = "" + encPass;
		String pass2 = encodePassword(rawPass, salt);

		if ("".equals(pass1) || pass1 == null) {
			throw new IllegalStateException("パスワードの有効期限が切れております");
		}

		if (pass1.equals(pass2)) {
			return true;
		}
		return false;
	}

}
