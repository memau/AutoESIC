package com.esic.exception;


public class ESICException extends RuntimeException {

	public ESICException(String string, Exception e) {
		super(string, e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3804584889224457908L;

}
