package com.kh.jdbc.view.book;

import java.util.Scanner;

import com.kh.jdbc.book.model.vo.Book;

public class BookMenu {
	
	private Scanner sc = new Scanner(System.in);
	
	public void bookMenu() {
		
		do {
			System.out.println("\n*** 도서 관리 ***");
			System.out.println("1. 도서 전체 조회");
			System.out.println("2. 도서 등록");
			System.out.println("3. 도서 소개 수정");
			System.out.println("4. 도서 삭제");
			System.out.println("5. 도서 검색");
			System.out.println("6. 종료");
			System.out.println("번호 선택 : ");
			
			switch(sc.nextInt()) {
			case 1 :
				//bookController의 searchAllBooks()를 호출하고
				//결과값을 출력
				break;
			case 2 : 
				//registBook 메서드를 호출해 사용자로부터 추가할 도서 정보를 입력받고
				//bookController의 registBook 메서드를 호출해 도서정보를 추가
				//도서가 성공적으로 추가되면 "도서 추가 성공"
				//도서 추가에 실패하면 "도서 추가 실패" 출력
				break;
			case 3:
				//수정할 도서의 도서번호와 도서 소개(info컬럼)를 사용자로 부터 입력받아
				//bookController 의 modifyBook을 호출해 도서 소개를 수정하고
				//성공하면 "도서 수정 성공", 실패하면 "도서 수정 실패"를 출력하시오.
				break;
			case 4:
				//삭제할 도서의 도서번호를 사용자로 부터 입력받아
				//bookController 의 deleteBook 메서드를 호출하고
				//도서 삭제에 성공하면 "도서 삭제 성공", 실패하면 "도서 삭제 실패" 출력
				break;
			case 5:
				//searckBookMenu 메서드를 호출해 도서 검색 메뉴창 출력
				break;
			case 6: return;
			default : System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}while(true);
	}
	
	public void searchBookMenu() {
		
		do {
			System.out.println("\n*** 도서 검색 메뉴 ***");
			System.out.println("1. 도서명 검색");
			System.out.println("2. 인기 top 5 검색");
			System.out.println("3. 이전 메뉴로 이동");
			System.out.print("입력 : ");
			
			switch(sc.nextInt()) {
			case 1 :
				sc.nextLine();
				System.out.print("검색할 도서명 : ");
				
				//bookController의 searchBookByTitle 메서드에 사용자가 입력한
				//도서명을 전달하고 결과를 출력하시오.
				break;
			case 2 :
				System.out.println("대출 건수가 많은 상위 5권의 목록입니다.");
				
				//bookController의 searchBookWithRank() 메서드를 호출해
				//상위 다섯권의 도서 리스트를 반환받아, 리스트 안의 도서들을 출력
				
			case 3: return;
			default : System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
			}
			
		}while(true);
	}
	
	public Book registBook() {
		Book book = new Book();
		System.out.println("도서정보를 입력하세요---------------------");
		System.out.print("도서 제목 : ");
		book.setTitle(sc.nextLine());
		
		System.out.print("작가 : " );
		book.setAuthor(sc.next());
		
		System.out.print("ISBN");
		book.setIsbn(sc.next());
		
		System.out.print("카테고리코드 : ");
		book.setCategory(sc.next());
		return book;
	}
	
	
	
	
	
	
	
	
	
	

}

