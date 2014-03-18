package org.swp.security;

public interface Encoder {
	public String encode(String password, String salt);
}
