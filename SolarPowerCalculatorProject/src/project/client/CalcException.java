package project.client;

import java.io.Serializable;

public class CalcException extends Exception implements Serializable {

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
