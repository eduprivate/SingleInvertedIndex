package br.com.nmonitor.processor.exceptions;

@SuppressWarnings("serial")
public class ServerInitializationError extends Throwable {
	
	public ServerInitializationError() {
		super();
	}

	public ServerInitializationError(Throwable cause) {
		super(cause);
	}

	public ServerInitializationError(String message) {
		super(message);
	}

	public ServerInitializationError(String message, Throwable cause) {
		super(message, cause);
	}
}
