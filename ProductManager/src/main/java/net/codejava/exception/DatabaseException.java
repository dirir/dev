package net.codejava.exception;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = -9079454849611061074L;
	
	public DatabaseException() {
		super();
	}
	
	public DatabaseException(final String message) {
		super(message);
	}
}
