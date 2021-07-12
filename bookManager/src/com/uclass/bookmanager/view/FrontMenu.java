package com.uclass.bookmanager.view;

import java.util.Scanner;

import com.uclass.bookmanager.member.model.vo.Member;

public class FrontMenu {
	
	MemberMenu memberMenu = new MemberMenu();
	BookMenu bookMenu = new BookMenu();
	Scanner sc = new Scanner(System.in);
	
	public void frontMenu() {
		do {
			System.out.println("\n\n***************Welcome*************");
			System.out.println("0. 회원가입");
			System.out.println("1. Login");
			System.out.println("2. 프로그램 종료");
			System.out.print("입력 : ");
			String menuNumber = sc.nextLine();
			
			switch (menuNumber) {
			case "0": memberMenu.joinMenu();
				break;
			case "1":  memberMenu.LoginMenu();
					   displayMenu();
				break;
			case "2":System.out.println("프로그램을 종료합니다.");
				return;
			default:System.out.println("정확한 값을 입력해주세요.");
				break;
			}
		}while(true);
	}
	
	public void displayMenu() {
		do {
		System.out.println("\n\n원하는 메뉴를 선택해주세요.");
		System.out.println("1. 도서 메뉴");
		System.out.println("2. MyPage");
		System.out.println("3. LogOut");
		System.out.print("입력 : ");
		String menuNumber = sc.nextLine();

		switch (menuNumber) {
		case "1": bookMenu.bookMenu();
			break;
		case "2": memberMenu.myPage();
			break;
		case "3": 
			Member.loginUser = null;
			return;
		default:System.out.println("정확한 번호를 입력해주세요.");
			break;
		}
		
		}while(true);
	}

}
