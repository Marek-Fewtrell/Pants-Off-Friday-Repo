package project.shared;

import java.io.Serializable;

public class CalcException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reason;
	
	public CalcException() {
		
	}
	
	public CalcException(String reason) {
		this.reason = reason;
	}
	
	public String getException() {
		return reason;
	}
	
}
