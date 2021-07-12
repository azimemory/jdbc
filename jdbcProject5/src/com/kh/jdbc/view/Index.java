package com.kh.jdbc.view;

import java.util.Scanner;

import com.kh.jdbc.common.code.MemberGrade;
import com.kh.jdbc.member.controller.MemberController;
import com.kh.jdbc.member.model.vo.Member;
import com.kh.jdbc.view.book.BookMenu;
import com.kh.jdbc.view.member.MemberMenu;
import com.kh.jdbc.view.rent.RentMenu;

//MVC2 모델에서 V는 VIEW를 의미한다
//VIEW는 사용자에게 데이를 보여주는 형태와 양식을 의미한다.
public class Index {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController memberController = new MemberController();
	MemberMenu memberMenu = new MemberMenu();
	BookMenu bookMenu = new BookMenu();
	RentMenu rentMenu = new RentMenu();
	
	public void startMenu() {
		System.out.println("로그인 하세요.");
		
		System.out.print("아이디 : ");
		String userId = sc.next();
		
		System.out.print("암호 : ");
		String password = sc.next();
		
		//사용자가 입력한 아이디와 암호로 식별되는 회원 정보를 
		//MemberController에게 요청
		Member member = memberController.login(userId, password);
		
		//반환받은 회원정보가 존재하면
		//"OOO님 환영합니다." 출력 후 회원관리메뉴를 출력
		//반환받은 회원정보가 존재하지 않으면
		//"아이디나 암호를 확인하세요" 출력 후 프로그램 종료
		if(member != null) {
			while(true) {
				System.out.println("관리할 메뉴를 선택하세요.");
				System.out.println("1. 회원관리");
				System.out.println("2. 도서관리");
				System.out.println("3. 대출관리");
				System.out.println("4. 종료");
				System.out.print("입력 : ");
				
				switch(sc.nextInt()) {
				case 1 : memberMenu.memberMenu(); break;
				case 2 : bookMenu.bookMenu(); break;
				case 3 : rentMenu.rentMenu(); break;
				case 4 : return;
				default :  System.out.println("잘못된 숫자를 입력하셨습니다.");
				
				}
			}
		}else {
			System.out.println("아이디나 암호를 확인하세요.");
		}
	}
}
