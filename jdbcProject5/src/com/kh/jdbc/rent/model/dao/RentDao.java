package com.kh.jdbc.rent.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.common.exception.DataAccessException;
import com.kh.jdbc.common.template.JDBCTemplate;
import com.kh.jdbc.rent.model.vo.Rent;
import com.kh.jdbc.rent.model.vo.RentBook;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

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
	
	//rent_master테이블에 주문건 정보 입력
	public void insertRentInfo(Connection conn, Rent rent, String[] bkIdxs) throws DataAccessException{
		//프로시저 실행용 객체
		CallableStatement cstm = null;
		String query = "{call sp_rent_insert(?,?,?,?)}";
		
		try {
			ArrayDescriptor descriptor = ArrayDescriptor.createDescriptor(
			        "BK_IDXS", conn);		
			ARRAY bkIdxArr = new ARRAY(descriptor, conn, bkIdxs);
			
			cstm = conn.prepareCall(query);
			cstm.setString(1, rent.getUserId());
			cstm.setString(2, rent.getTitle());
			cstm.setInt(3, rent.getRentBookCnt());
			cstm.setArray(4, bkIdxArr);
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
