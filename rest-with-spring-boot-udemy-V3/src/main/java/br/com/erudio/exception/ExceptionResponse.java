package br.com.erudio.exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private String details;
	
	

	public ExceptionResponse(Date timestamp, int status, String error, String message, String details) {
		
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}
	
	
	

}
