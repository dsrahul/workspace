package com.marketplace.offer.exception;

public class OfferNotUpdatedException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public OfferNotUpdatedException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
