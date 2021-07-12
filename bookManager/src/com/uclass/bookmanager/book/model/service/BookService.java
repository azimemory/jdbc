package com.uclass.bookmanager.book.model.service;

import java.sql.Connection;
import java.util.List;

import com.uclass.bookmanager.book.model.dao.BookDao;
import com.uclass.bookmanager.book.model.vo.Book;
import com.uclass.common.db.JDBCTemplate;
import com.uclass.common.exception.DataAccessException;
import com.uclass.common.exception.service.ErrorService;

public class BookService{
	
	private BookDao bdao = new BookDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public List<Book> selectBook(String searchType, String keyword) {
		
		Connection conn = jdt.getConnection();
		List<Book> result = null;
		
		try {
			result = bdao.selectBook(conn, searchType, keyword);
		} catch (DataAccessException e) {
			//사용자 또는 개발자에게 적절한 안내메시지를 보내준다.
			new ErrorService().printErrorMessage(e);
		}finally {
			jdt.close(conn);
		}
		
		return result;
	}
}
