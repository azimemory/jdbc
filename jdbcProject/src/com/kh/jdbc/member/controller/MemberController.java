package com.kh.jdbc.member.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.member.model.service.MemberService;
import com.kh.jdbc.member.model.vo.Member;

//Controller 
//사용자의 요청을 받아 해당 요청을 수행할 Service의 메서드를 호출
//사용자가 보낸 데이터를 Application 내부에서 사용하기 적합하게 가공
//		따라서 사용자가 보내는 데이터의 형식이 바뀌더라도 Model의 코드가 변경될 일은 없어야 한다.
//적합하지 않은 요청에 대해서는 허가/불가 처리를 하는 외벽 역할(권한관리) 
//Service에서 사용자의 요청을 처리해 결과를 반환하면, 
//Controller는 사용자에게 어떤 view를 보여 줄 지를 선택

public class MemberController {
	
	private MemberService memberService = new MemberService();
	
	public Member login(String userId, String password) {
		Member member = memberService.memberAuthenticate(userId, password);
		return member;
	}
	
	public Member searchById(String userId){
		return memberService.selectMemberById(userId);
	}
 		
	public ArrayList<Member> searchAllMember(){
		return memberService.selectMemberList();
	}
	
	public int join(Member member) {
		return memberService.insertMember(member);
	}

	public int modify(Member member) {
		return memberService.updateMember(member);
	}
	
	public int delete(String userId){
		return memberService.deleteMember(userId);
	}
	
	public List<Member> searchByRegDate(String begin, String end){
		Date beginDate = Date.valueOf(begin);
		Date endDate = Date.valueOf(end);
		return memberService.selectMemberByRegdate(beginDate, endDate);
	}
}
