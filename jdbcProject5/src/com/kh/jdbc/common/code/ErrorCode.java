package com.kh.jdbc.common.code;

public enum ErrorCode {

	SM01("회원정보를 조회하는 도중 에러가 발생하였습니다."),
	IM01("회원정보를 입력하는 도중 에러가 발생하였습니다."),
	UM01("회원정보 수정 중 에러가 발생하였습니다."),
	DM01("회원정보 삭제 중 에러가 발생하였습니다."),
	
	SR01("대출 정보를 조회하는 중 에러가 발생하였습니다."),
	IR01("대출 정보를 입력하는 중 에러가 발생하였습니다."),
	UR01("대출 정보를 수정하는 중 에러가 발생하였습니다."),
	DR01("대출 정보를 삭제하는 중 에러가 발생하였습니다.");

	private String errMsg;
	
	ErrorCode(String errMsg){
		this.errMsg = errMsg;
	}
	
	public String errMsg() {
		return errMsg;
	}

}
