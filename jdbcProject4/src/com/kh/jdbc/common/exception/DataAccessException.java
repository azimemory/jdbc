package com.kh.jdbc.common.exception;

//예외처리가 강제되지 않는 예외
//Checked Exception -> RuntimeException을 제외한 다른 Exception을 상속
//UnChecked Exception -> RuntimeException을 상속받는 예외
public class DataAccessException extends RuntimeException{
	
	private static final long serialVersionUID = 521587827126031031L;
	
	public DataAccessException(String message) {
		super(message);
	}
	
	public DataAccessException(String message, String errMsg) {
		super(message);
	}
	

}
