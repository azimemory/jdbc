package com.kh.jdbc.view.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.member.controller.MemberController;
import com.kh.jdbc.member.model.vo.Member;

public class MemberMenu {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController memberController = new MemberController();
	
	public void memberMenu() {
		
		do {
			System.out.println("\n***  회원 관리 프로그램 ***");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 새 회원 등록");
			System.out.println("3. 회원 암호 수정");
			System.out.println("4. 회원 탈퇴");
			System.out.println("5. 회원 검색");
			System.out.println("6. 종료");
			System.out.print("번호 입력 : ");
			
			switch(sc.nextInt()) {
				case 1: ArrayList<Member> memberList = memberController.searchAllMember();
						for (Member member : memberList) {
							System.out.println(member);
						 }
					break;
				case 2: if(memberController.join(join()) > 0) {
							System.out.println("회원 추가 성공");
						}else {
							System.out.println("회원 추가 실패");
						}
					break;
				case 3: 
					sc.nextLine();
					Member member = new Member();
					System.out.print("비밀번호를 변경할 아이디 : ");
					member.setUserId(sc.nextLine());
					
					System.out.print("변경할 비밀번호 : ");
					member.setPassword(sc.next());
					int res = memberController.modify(member);
					
					if(res > 0) {
						System.out.println("암호 수정이 성공적으로 완료되었습니다.");
					}else {
						System.out.println("존재하지 않는 사용자입니다.");
					}
					break;
					
				case 4: 
					System.out.print("탈퇴 시킬 회원의 아이디 입력 : ");
					String userId = sc.next();
					res = memberController.delete(userId);
					
					if(res > 0) {
						System.out.println("회원탈퇴 처리가 완료되었습니다.");
					}else {
						System.out.println("존재하지 않는 사용자 입니다.");
					}
					
					
					//TB_MEMBER 테이블의 IS_LEAVE 컬럼의 값을 1로 변경하여 탈퇴처리
					//로그인할 때 IS_LEAVE 컬럼의 값이 1이라면 조회되지 않도록
					//조건절 변경
					//DAO : updateMemberToLeave() 메서드 추가;
					
					break;
					
				case 5: searchMenu(); break;
				case 6: return;
				default : System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
			}
		}while(true);
	}
	
	public void searchMenu() {
		
		do {
			System.out.println("\n*** 회원 검색 메뉴 ***");
			System.out.println("1. 아이디로 검색");
			System.out.println("2. 가입날짜별 검색");
			System.out.println("3. 이전 메뉴로 이동");
			System.out.print("번호 선택 : ");
			
			switch(sc.nextInt()) {
				case 1 : System.out.print("검색할 아이디 : ");
						 String userId = sc.next();
						
						 Member member = memberController.searchById(userId);
						 
						 if(member != null) {
							 System.out.println(member);
						 }else {
							System.out.println("검색하신 아이디의 회원은 존재하지 않습니다."); 
						 }
						 
						 break;
				case 2 : System.out.print("검색할 가입 시작 날짜[yyyy-mm-dd] :");
						 String begin = sc.next();
						 System.out.print("검색할 가입 끝날짜[yyyy-mm-dd] : ");
						 String end = sc.next();
						 
						 //MemberController의 searchByRegDate 메서드에 사용자가
						 //입력한 시작날짜와 끝날짜를 전달
						 List<Member> memberList = memberController.searchByRegDate(begin, end);
						 
						 //MemberController가 반환하는 member를 출력
						 for (Member m : memberList) {
							System.out.println(m);
						 }
						 
						 break;
						 
				case 3 : return;
				default : System.out.println("잘못 입력되었습니다. 다시 입력하세요.");
						 
			}
		}while(true);
	}
		
	//사용자로부터 회원가입 정보를 받아서 member객체로 반환
	public Member join() {
		
		Member member = new Member();
		
		System.out.println("회원 정보를 입력하세요.-------------");
		
		System.out.print("아이디 : ");
		member.setUserId(sc.next());
		
		System.out.print("암호 : ");
		member.setPassword(sc.next());
		
		System.out.print("이메일 : ");
		member.setEmail(sc.next());
		
		System.out.print("전화 번호 : ");
		member.setTell(sc.next());
		return member;
	}

	
	
	
	
	
	
	
}
