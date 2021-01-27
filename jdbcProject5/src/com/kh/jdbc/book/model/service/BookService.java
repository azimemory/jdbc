package com.kh.jdbc.book.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.book.model.dao.BookDao;
import com.kh.jdbc.book.model.vo.Book;
import com.kh.jdbc.common.template.JDBCTemplate;

public class BookService {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	BookDao bookDao = new BookDao();
	
	public List<Book> selectAllBooks(){
		
		Connection conn = jdt.getConnection();
		List<Book> bookList = null;
		
		try {
			bookList = bookDao.selectAllBooks(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdt.close(conn);
		}
		
		return bookList;
	}
	
	public List<Book> selectBookOrderByRank() {
		Connection conn = jdt.getConnection();
		List<Book> bookList = new ArrayList<Book>();
		
		try {
			bookList = bookDao.selectBookOrderByRank(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdt.close(conn);
		}
		
		return bookList;
	}
	
	public Book selectBookByTitle(String title) {
		
		Connection conn = jdt.getConnection();
		Book book = null;
		
		try {
			book = bookDao.selectBookByTitle(conn,title);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdt.close(conn);
		}
		
		return book;
	}
	
	public int insertBook(Book book) {
		
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			res = bookDao.insertBook(conn,book);
			jdt.commit(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
	
		return res;
	}

	public int updateBook(Book book) {
		
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			res = bookDao.updateBook(conn,book);
			jdt.commit(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		
		return res;
	}
	
	public int deleteBookByBIdx(int bIdx){
		
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			res = bookDao.deleteBookByBIdx(conn,bIdx);
			jdt.commit(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		
		return res;
	}
}
