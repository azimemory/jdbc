package com.uclass.bookmanager.view;

import java.util.Scanner;

import com.uclass.bookmanager.member.controller.MemberController;
import com.uclass.bookmanager.member.model.vo.Member;
import com.uclass.bookmanager.rent.controller.RentController;


public class MemberMenu {
	
	Scanner sc = new Scanner(System.in);
	MemberController memberController = new MemberController();
	RentController rentController = new RentController();
	RentMenu rentMenu = new RentMenu();
	
	public void joinMenu() {
		System.out.println("\n\n********회원가입 페이지 입니다.*********");
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String password = sc.nextLine();
		System.out.print("전화번호 : ");
		String tell = sc.nextLine();
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.println(memberController.joinMember(userId, password, tell, email));
	}
	
	public Member LoginMenu() {
		boolean flg = true;
		Member loginUser = null;
		
		do {
			System.out.println("\n\n********로그인 페이지 입니다.*********");
			System.out.print("아이디 : ");
			String userId = sc.nextLine();
			System.out.print("패스워드 : ");
			String password = sc.nextLine();
			memberController.login(userId, password);
			
			if(Member.loginUser != null) {
				flg = false;
				System.out.println("\n"+Member.loginUser.getUserId() + "님 환영합니다.");
			}else {
				System.out.println("아이디나 비밀번호가 틀렸습니다.");
				System.out.println("\n\n********어디로 이동하시겠습니까.*********");
				System.out.println("0. 회원가입  : ");
				System.out.println("1. 로그인  : ");
				System.out.print("입력 : ");
				String menuNumber = sc.nextLine();
				switch (menuNumber) {
					case "0":
						joinMenu();
						break;
					case "1":
						break;
					}
				}
		}while(flg);
		
		return loginUser;
	}
	
	public void myPage() {
		do {
			System.out.println("\n\n*******MyPage********");
			System.out.println("1. 내 정보 확인하기");
			System.out.println("2. 회원정보변경");
			System.out.println("3. 대출내역확인");
			System.out.println("4. 도서 연장 / 반납");
			System.out.println("0. 이전 메뉴로 돌아가기");
			System.out.print("입력 : ");
			switch (sc.nextLine()) {
				case "1":
					System.out.println(Member.loginUser);
					break;
				case "2":
					updateInfo();
					break;
				case "3":
					rentMenu.myRentInfo();
					break;
				case "4":
					rentMenu.updateRentState();
					break;
				case "0":	return;
				default:
					break;
			}
		}while(true);
	}
	
	public void updateInfo() {
		System.out.println("\n\n***회원정보변경***");
		System.out.println("변경을 원하지 않는 항목은 ENTER를 입력하세요.");
		System.out.print("비밀번호변경 : ");
		String password = sc.nextLine();
		System.out.print("휴대폰번호 변경 : ");
		String tell = sc.nextLine();
		System.out.print("이메일 변경 : ");
		String email = sc.nextLine();
		String userId = Member.loginUser.getUserId();
		
		System.out.println(memberController.modifyMember(userId, password,tell,email));
		
	}
}
