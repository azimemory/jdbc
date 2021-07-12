package com.uclass.bookmanager.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.uclass.bookmanager.book.model.vo.Book;
import com.uclass.bookmanager.member.model.vo.Member;
import com.uclass.bookmanager.rent.controller.RentController;
import com.uclass.bookmanager.rent.model.vo.Rent;

/**
 * @PackageName: com.uclass.bm.view
 * @FileName : RentMenu.java
 * @Date : 2020. 5. 29.
 * @프로그램 설명 : 
 * @author 
 */
public class RentMenu {
	
	private Scanner sc = new Scanner(System.in);
	RentController rentController = new RentController();

	public void rentBook(List<Book> bookList) {
	  if(bookList.size() > 0) {
		  System.out.print("\n\n대출할 도서가 있으신가요? [Y/N] : ");
		  if(sc.nextLine().equalsIgnoreCase("Y")) {
			 System.out.print("대출할 도서번호를 입력해주세요 [여러 권일 경우 ,로 구분]: "); 
			 String rbIdx = sc.nextLine(); 
			 System.out.println(rentController.rentBook(Member.loginUser,rbIdx,bookList)); 
		  } 
	   }  
    }
	
	public void myRentInfo() {
		while(true) {
			System.out.println("\n\n******대출내역******");
			if(rentController.searchRentinfo().size() == 0) {
				System.out.println("대출내역이 없습니다.");
				return;
			}
			for(Rent r : rentController.searchRentinfo()) {
				System.out.println(r);
			}
			System.out.print("상세보기 [대출번호 or 0(종료) 입력]: ");
			String menuNumber = sc.nextLine();
			if(menuNumber.equals("0")) return;				
			System.out.println("\n\n********상세정보********");
			List<Map<String,Object>> rentBooks = rentController.searchRentbook(Member.loginUser,"rmIdx", menuNumber);
			for(Map<String,Object> data:rentBooks) {
				System.out.println(data);
			}
		}
	}
	
	public void updateRentState() {
		
		while(true) {
			System.out.println("\n\n******대출내역수정하기******");
			if(rentController.searchRentinfo().size() == 0) {
				System.out.println("대출내역이 없습니다.");
				return;
			}
			for(Rent r : rentController.searchRentinfo()) {
				System.out.println(r);
			}
			System.out.print("상세보기 [대출번호 입력] / 이전 페이지[0]: ");
			String rmIdx = sc.nextLine();
			List<Map<String,Object>>rentDetail = null;
			
			switch (rmIdx) {
				case "0":  return;
				default : rentDetail = rentController.searchRentbook(Member.loginUser,"rmIdx", rmIdx);
			}
			
			System.out.println("\n\n********도서 대출 상세내역 입니다.********");
			
			if(rentDetail.size() == 0) {
				System.out.println("대출내역이 없습니다.");
				return;
			}
			
			for(Map<String,Object> data : rentDetail) {
				System.out.println(data);
			}
			
			System.out.println("\n\n********진행********");
			System.out.println("1. 도서 반납 ");
			System.out.println("2. 도서 연장 ");
			System.out.println("0. 이전 메뉴 ");
			System.out.print("번호를 선택하세요 : ");
			String menuNumber = sc.nextLine();
			
			switch (menuNumber) {
			case "0":  return;
			case "1":
				System.out.print("\n\n반납할 도서의 대출도서코드를 입력하세요:");
				String returnRbIdx = sc.nextLine();
				if(checkRbIdx(returnRbIdx,rentDetail) != null) {
					System.out.println("\n\n"+rentController.returnBook(rmIdx,returnRbIdx));
				}else {
					System.out.println("**********************");
					System.out.println("\n대출도서코드를 확인하세요.\n");
				}
				break;
			
			case "2":
				System.out.print("\n\n연장할 도서의 대출도서코드를 입력하세요:");
				String extendRbIdx = sc.nextLine();
				Map<String,Object> extendBook = checkRbIdx(extendRbIdx,rentDetail);
				if(extendBook != null) {
					System.out.println(rentController.extendBook(extendBook));	
				}else {
					System.out.println("**********************");
					System.out.println("\n도서대출코드를 확인해주세요.\n");
				}
				break;
			}
			
			System.out.println("**********************");
		}
	}
	
	private Map<String,Object> checkRbIdx(String rbIdx, List<Map<String,Object>> rentDetailInfo) {
		for(Map<String,Object> data : rentDetailInfo) {
			if(rbIdx.equals(String.valueOf(data.get("rbIdx")))) {
				return data;
			}
		}
		return null;
	}
}
