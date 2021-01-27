package com.kh.jdbc.book.controller;

import java.util.List;

import com.kh.jdbc.book.model.service.BookService;
import com.kh.jdbc.book.model.vo.Book;

public class BookController {
	
	BookService bookService = new BookService();
	
	public List<Book> searchAllBooks(){
		List<Book> bookList = bookService.selectAllBooks();
		return bookList;
	}
	
	public List<Book> searchBooksWithRank(){
		List<Book> bookList = bookService.selectAllBooks();
		return bookList;
	}
	
	public Book searchBookByTitle(String title) {
		Book book = bookService.selectBookByTitle(title);
		return book;
	}
	
	public boolean registBook(Book book) {
		int res = bookService.insertBook(book);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean modifyBook(Book book) {
		int res = bookService.updateBook(book);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean deleteBook(int bIdx) {
		int res = bookService.deleteBookByBIdx(bIdx);
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}

}
