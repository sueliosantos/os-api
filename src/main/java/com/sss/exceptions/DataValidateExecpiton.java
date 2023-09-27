package com.sss.exceptions;

public class DataValidateExecpiton extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataValidateExecpiton(String message, Throwable cause) {
		super(message, cause);
	}

	public DataValidateExecpiton(String message) {
		super(message);
	}

}

