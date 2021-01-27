package com.kh.jdbc.member.view;

import java.util.Scanner;
import com.kh.jdbc.member.controller.MemberController;
import com.kh.jdbc.member.model.vo.Member;

//MVC2 모델에서 V는 VIEW를 의미한다
//VIEW는 사용자에게 데이를 보여주는 형태와 양식을 의미한다.
public class Index {
	private Scanner sc = new Scanner(System.in);
	private MemberController memberController = new MemberController();
	MemberMenu memberMenu = new MemberMenu();
	
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
			System.out.println(member.getUserId() + "님 환영합니다.");
			memberMenu.memberMenu();
		}else {
			System.out.println("아이디나 암호를 확인하세요.");
		}
	}
}
