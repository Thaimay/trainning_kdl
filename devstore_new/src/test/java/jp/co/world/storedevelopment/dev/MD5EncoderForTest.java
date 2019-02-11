package jp.co.world.storedevelopment.dev;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class MD5EncoderForTest {

	public static void main(String[] args) {
		System.out.println(new Md5PasswordEncoder().encodePassword("test1234", null));
	}

}
