package com.uclass.common.exception.service;

import com.uclass.common.code.ErrorCode;

public class ErrorService {
	
	public void printErrorMessage(Exception e) {
		//지금은 콘솔이지만 나중에는 사용자에게 보내는 이메일이 될 수도, 개발자에게 문자메시지를 보내줄 수도 있다.
		System.out.println("\n\n\n\n ****  에러메시지 입니다. **** \n\n");
		System.out.println(ErrorCode.valueOf(e.getMessage()).errMsg());
		System.out.println("\n\n*********************\n\n\n\n");
	}
}
