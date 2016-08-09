package com.raremile.training.exceptions;

/**
 * Exceptions that cannot be recovered from. Critical errors should be
 * FatalException
 * 
 */
public class FatalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FatalException() {
		super();
	}

	public FatalException(String message) {
		super(message);
	}

	public FatalException(String message, Throwable thrw) {
		super(message, thrw);
	}

}
