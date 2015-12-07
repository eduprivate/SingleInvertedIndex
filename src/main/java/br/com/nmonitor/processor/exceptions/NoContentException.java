package br.com.nmonitor.processor.exceptions;

@SuppressWarnings("serial")
public class NoContentException extends Throwable {

	public NoContentException() {
		super();
	}
	
	public NoContentException(Throwable cause){
		super(cause);
	}
	
	public NoContentException(String message) {
		super(message);
	}
	
	public NoContentException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
