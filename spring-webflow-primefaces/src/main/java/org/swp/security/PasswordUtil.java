package org.swp.security;

public class PasswordUtil {
	public static String encodePassword(String username, String password) {
		Encoder encoder = new Sha256Encoder();
		return encoder.encode(password, username);
	}
}
