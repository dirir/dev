package net.codejava.exception;

public class AccessLevelException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public AccessLevelException() {
		super();
	}
	public AccessLevelException(final String message) {
		super(message);
	}
}
