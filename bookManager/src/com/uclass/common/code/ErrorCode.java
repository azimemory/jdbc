package com.uclass.common.code;

/**
 * @PackageName: com.uclass.common.code
 * @FileName : ErrorCode.java
 * @Date : 2020. 6. 16.
 * @프로그램 설명 : 
 * @author :하명도
 */
public enum ErrorCode {
	
	SM01("회원정보를 조회하는 도중 에러가 발생하였습니다."),
	SM02("아이디 중복검사를 진행하던 중 에러가 발생하였습니다."),
	SM03("이미 존재하는 아이디 입니다."),
	IM01("회원가입 도중 에러가 발생하였습니다."),
	UM01("회원정보를 수정하는 도중 에러가 발생하였습니다."),
	SB01("도서 검색 중 에러가 발생하였습니다."),
	SR01("대출정보를 불러오는 중 에러가 발생하였습니다."),
	IR01("대출정보를 입력 하는 중 에러가 발생하였습니다."),
	UR01("대출정보를 수정 하는 중 에러가 발생하였습니다."),
	IH01("대출정보 히스토리를 등록하다 에러가 발생하였습니다.");
	
	private String errMsg;
	
	ErrorCode(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public String errMsg() {
		return errMsg;
	}
}
