package com.kh.jdbc.rent.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.kh.jdbc.book.model.vo.Book;
import com.kh.jdbc.common.exception.DataAccessException;
import com.kh.jdbc.common.template.JDBCTemplate;
import com.kh.jdbc.rent.model.dao.RentDao;
import com.kh.jdbc.rent.model.vo.Rent;
import com.kh.jdbc.rent.model.vo.RentBook;

public class RentService {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	RentDao rentDao = new RentDao();
	
	public List<Rent> selectRentList(String userId){
		List<Rent> rentList = new ArrayList<Rent>();
		return rentList;
	}
	
	public List<RentBook> selectRentBookList( String rmIdx){
		List<RentBook> rentBookList = new ArrayList<RentBook>();
		return rentBookList;
	}
	
	//도서 대출 처리
	public boolean insertRentInfo(Rent rent, List<Book> bookList) {
		
		Connection conn = jdt.getConnection();
		
		//대출건 정보를 tb_rent_master에 입력
		
		try {
			rentDao.insertRentInfo(conn, rent);
			for(Book book : bookList) {
				rentDao.insertRentBookInfo(conn, book.getbIdx());
			}
			jdt.commit(conn);			
		}catch(DataAccessException e) {
			jdt.rollback(conn);
			return false;
		}finally {
			jdt.close(conn);
		}
		
		return true;
	}
	
	public boolean updateReturnRentBook(int rbIdx){
		return false;
	}
	
	public boolean updateExtendRentState(int rbIdx){
		return false;
	}
	
}
