package com.kh.jdbc.rent.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.common.exception.DataAccessException;
import com.kh.jdbc.common.template.JDBCTemplate;
import com.kh.jdbc.rent.model.vo.Rent;
import com.kh.jdbc.rent.model.vo.RentBook;

public class RentDao {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public List<Rent> selectRentList(Connection conn, String userId) throws DataAccessException{
		List<Rent> rentList = new ArrayList<Rent>();
		return rentList;
	}
	
	public List<RentBook> selectRentBookList(Connection conn, String rmIdx) throws DataAccessException{
		List<RentBook> rentBookList = new ArrayList<RentBook>();
		return rentBookList;
	}
	
	//tb_rent_master테이블에 주문건 정보 입력
	public int insertRentInfo(Connection conn, Rent rent) throws DataAccessException{
		int result = 0;
		PreparedStatement pstm = null;
		String query = "insert into tb_rent_master(rm_idx,user_id,title,rent_book_cnt)"
				+ " values(sc_rm_idx.nextval,?,?,?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, rent.getUserId());
			pstm.setString(2, rent.getTitle());
			pstm.setInt(3, rent.getRentBookCnt());
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		}finally {
			jdt.close(pstm);
		}
		
		return result;
	}

	//대출 도서 정보 입력	
	//SP_RENT_INSERT 프로시저 호출
	//PROCEDURE의 TABLE타입 : 일차원 배열 과 같다.
	//JDBC에서 프로시저를 호출 할 때 TABLE 타입의 매개변수에
	//배열 또는 LIST를 넘겨서 PROCEDURE 내부에서 반복적으로 작업 가능	
	public void insertRentBookInfo(Connection conn, int bIdx) throws DataAccessException  {
		
		//프로시저 실행용 객체
		CallableStatement cstm = null;
		String query = "{call sp_rent_insert(?)}";
		
		try {
			cstm = conn.prepareCall(query);
			cstm.setInt(1, bIdx);
			cstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		}finally {
			jdt.close(cstm);
		}
	} 
	
	public void updateReturnRentBook(Connection conn, int rbIdx) throws DataAccessException {

	}
	
	public void updateExtendRentState(Connection conn, int rbIdx) throws DataAccessException {

	}

}
