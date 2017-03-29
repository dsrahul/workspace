package com.marketplace.offer.user;

import org.springframework.data.authentication.UserCredentials;

public class AppUserCredentials extends UserCredentials {

	public AppUserCredentials() {
		super(null, null);
		// TODO Auto-generated constructor stub
	}
	
	public AppUserCredentials(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}

}
