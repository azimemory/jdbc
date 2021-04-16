package com.kh.jdbc.common.code;

public enum ErrorCode {

	DATABASE_ACCESS_ERROR("데이터베이스와 통신 중 에러가 발생하였습니다.");

	public String desc;
	
	ErrorCode(String desc){
		this.desc = desc;
	}
}
