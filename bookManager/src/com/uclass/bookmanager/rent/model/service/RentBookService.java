package com.uclass.bookmanager.rent.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.uclass.bookmanager.book.model.vo.Book;
import com.uclass.bookmanager.rent.model.dao.RentBookDao;
import com.uclass.bookmanager.rent.model.vo.Rent;
import com.uclass.common.db.JDBCTemplate;
import com.uclass.common.exception.DataAccessException;
import com.uclass.common.exception.service.ErrorService;

public class RentBookService {
	
	RentBookDao rdao = new RentBookDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public List<Rent> selectRentinfo(String id){
		
		List<Rent> result = new ArrayList<Rent>();
		Connection conn = jdt.getConnection();
		
		try {
			result = rdao.selectRentinfo(conn, id);
		} catch (DataAccessException e) {
			//사용자 또는 개발자에게 적절한 안내메시지를 보내준다.
			new ErrorService().printErrorMessage(e);
		}finally {
			jdt.close(conn);
		}
		
		return result;
	}
	
	public List<Map<String,Object>> selectRentinfo(String id, String searchType, int rentIdx){
		
		Connection conn = jdt.getConnection();
		List<Map<String,Object>> result = null;
		
		try {
			result = rdao.selectRenbooktinfo(conn, searchType, rentIdx);
		} catch (DataAccessException e) {
			//사용자 또는 개발자에게 적절한 안내메시지를 보내준다.
			new ErrorService().printErrorMessage(e);
		}finally {
			jdt.close(conn);
		}
		
		return result;
	}

	public boolean insertRentBookInfo(String userId, List<Book> rentBookList){
		
		boolean isSuccess = false;
		Connection conn = jdt.getConnection();
		String title = rentBookList.get(0).getTitle();
		String rmTitle = "";
		
		if(rentBookList.size() == 1) {
			rmTitle = title;
		}else {
			rmTitle = title + "외" + (rentBookList.size()-1) + "권";
		}
				
		try {
			rdao.insertRentInfo(conn,userId,rmTitle,rentBookList.size());
			for(Book book : rentBookList) {
				rdao.insertRentBookInfo(conn, book.getbIdx());
			}
			
			jdt.commit(conn);
			isSuccess = true;
			
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			//사용자 또는 개발자에게 적절한 안내메시지를 보내준다.
			new ErrorService().printErrorMessage(e);
		}finally {
			jdt.close(conn);
		}
		
		return isSuccess;
	}

	public boolean updateReturnRentBook(int rm_idx, int rb_idx) {
		boolean isSuccess = false;
		Connection conn = jdt.getConnection();
		
		try {
			isSuccess = rdao.updateReturnRentBook(conn, rm_idx, rb_idx);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		
		return isSuccess;
	}
	
	public boolean updateExtendRentBook(int rm_idx, int rb_idx) {
		boolean isSuccess = false;
		Connection conn = jdt.getConnection();
		
		try {
			//쿼리로 넘기기
			//rdao.updateExtendRentBook(conn, rb_idx);
			//rdao.insertUpdateHistory(conn, rb_idx);
			//프로시저 활용해서 update + insert 한번에 해결하기
			rdao.updateExtendRentState(conn, rm_idx ,rb_idx);
			jdt.commit(conn);
			isSuccess = true;
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			
		}finally {
			jdt.close(conn);
		}
		
		return isSuccess;
	}
}
