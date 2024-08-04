package com.generation.Ecommerce.dto;

public class UserToken {
	private final String accessToken;

	public UserToken(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}
}
