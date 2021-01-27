package com.kh.jdbc.member.model.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.member.model.dao.MemberDao;
import com.kh.jdbc.member.model.vo.Member;

//Service
//웹어플리케이션의 비지니스 로직 작성
//사용자가 전송한 데이터를 Controller에게 전달 받고
//비지니스 로직을 위해 추가적으로 필요한 데이터를 Dao에게 요청해
//핵심로직인 비지니스로직을 작성한다.
//비지니스 로직을 Service가 담당하기 때문에 transaction관리도 Service가 담당.
public class MemberService {
	
	MemberDao memberDao = new MemberDao();
	
	public Member memberAuthenticate(String userId, String password){	
		//Dao에게 userId와 password로 식별할 수 있는 회원정보를 DB에서 조회할 것을 요청
		Member member = memberDao.memberAuthenticate(userId, password);
		return member;
	}
	
	public Member selectMemberById(String userId){		
		Member member = memberDao.selectMemberById(userId);
		return member;
	}
	
	public List<Member> selectMemberByRegdate(Date begin, Date end){
		List<Member> memberList = memberDao.selectMemberByRegdate(begin, end);
		return memberList;
	}
 		
	public ArrayList<Member> selectMemberList(){
		ArrayList<Member> memberList = memberDao.selectMemberList();
		return memberList;
	}
	
	public int insertMember(Member member){
		int res = memberDao.insertMember(member);
		return res;
	}
	
	public int updateMember(Member member){
		return memberDao.updateMember(member);
	}
	
	public int deleteMember(String userId){
		return memberDao.deleteMember(userId);
	}
}
