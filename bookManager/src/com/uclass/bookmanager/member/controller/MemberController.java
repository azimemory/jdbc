package com.uclass.bookmanager.member.controller;

import com.uclass.bookmanager.member.model.service.MemberService;
import com.uclass.bookmanager.member.model.vo.Member;
import com.uclass.bookmanager.view.Alert;

public class MemberController {
	
	MemberService memberService = new MemberService();
	
	public void login(String userId, String userPwd){
		Member.loginUser = memberService.selectMember(userId,userPwd);
		if(Member.loginUser == null) {
			new Alert().alert("로그인에 실패하였습니다.");
		}
	}
	
	public String joinMember(String userId, String password, String tell, String email){
		String res = "";
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		member.setTell(tell);
		member.setEmail(email);
		
		if(memberService.insertMember(member)) {
			res = "회원가입에 성공하였습니다.";
		}else {
			res = "회원가입에 실패했습니다.";
		}
		
		return res;
	}
	
	public String modifyMember(String userId, String password, String tell, String email) {
		
		Member member = new Member();
		member.setUserId(userId);
		
		if(password.equals("")) {
			password = Member.loginUser.getPassword();
			member.setPassword(password);
		}else {
			member.setPassword(password);
		}
		
		if(tell.equals("")) {
			tell = Member.loginUser.getTell();
			member.setTell(tell);
		}else {
			member.setTell(tell);
		}
		
		if(email.equals("")) {
			email = Member.loginUser.getEmail();
			member.setEmail(email);
		}else {
			member.setEmail(email);
		}
		
		if(memberService.updateMemberInfo(member)) {
			Member.loginUser = memberService.selectMember(userId, password);
			return "회원 수정에 성공하였습니다.";
		}else {
			return "회원 수정에 실패하였습니다.";
		}
	}
}
