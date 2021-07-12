package com.uclass.bookmanager.book.controller;

import java.util.List;

import com.uclass.bookmanager.book.model.service.BookService;
import com.uclass.bookmanager.book.model.vo.Book;
import com.uclass.bookmanager.view.Alert;

public class BookController {
	
	private BookService bookService = new BookService();
	
	public List<Book> SearchBook(String searchType, String keyword){
		List<Book> bookList = bookService.selectBook(searchType, keyword);
		//도서검색결과가 없다면
		if(bookList.size() == 0) {
			new Alert().alert("도서가 검색되지 않았습니다.");
		}
		
		return bookList;
	}
}
