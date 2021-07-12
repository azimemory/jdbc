package com.uclass.bookmanager.view;

import java.util.List;
import java.util.Scanner;

import com.uclass.bookmanager.book.controller.BookController;
import com.uclass.bookmanager.book.model.vo.Book;

public class BookMenu {
	
	Scanner sc = new Scanner(System.in);
	private BookController bookController = new BookController();
	RentMenu rentMenu = new RentMenu();
	public void bookMenu() {
	
		List<Book> bookList = null;
		
		do {
			System.out.println("\n\n***도서검색***");
			System.out.println("1. 도서명 검색");
			System.out.println("2. 작가 검색");
			System.out.println("3. 이전메뉴로");
			System.out.print("입력 : ");
		
			String menuNumber = sc.nextLine();
		
			switch(menuNumber) {
				case "1" : 
					System.out.print("\n\n검색할 도서명을 입력하세요 : ");
					bookList = bookController.SearchBook("도서명",sc.nextLine());

					for(Book book : bookList) {
						System.out.println("도서번호  : " + book.getbIdx());
						System.out.println("제목 : " + book.getTitle());
						System.out.println("작가 : " + book.getAuthor());
						System.out.println("도서관 비치 권수 : " + book.getBookAmt()+ "\n");
					}
					rentMenu.rentBook(bookList);
					break;
				case "2" : 
					System.out.print("\n\n검색할 작가명을 입력하세요 : ");
					bookList = bookController.SearchBook("작가명",sc.nextLine());
					for(Book book : bookList) {
						System.out.println("도서번호  : " + book.getbIdx());
						System.out.println("제목 : " + book.getTitle());
						System.out.println("작가 : " + book.getAuthor());
						System.out.println("도서관 비치 권수 : " + book.getBookAmt()+ "\n");
					}
					rentMenu.rentBook(bookList);
					break;
				case "3" :return;
				default : System.out.println("번호를 정확히 입력해주세요.");	
				
		  }
			
		}while(true);
	}
}
