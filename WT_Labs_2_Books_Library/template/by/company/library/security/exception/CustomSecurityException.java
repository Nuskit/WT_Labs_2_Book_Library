package by.company.library.security.exception;

public class CustomSecurityException extends Exception {
	private static final long serialVersionUID = 1L;

	public CustomSecurityException(){
	}

	public CustomSecurityException(Exception e){
		super(e);
	}
	
	public CustomSecurityException(String message){
		super(message);
	}
	
	public CustomSecurityException(String message, Exception e){
		super(message, e);
	}
}
