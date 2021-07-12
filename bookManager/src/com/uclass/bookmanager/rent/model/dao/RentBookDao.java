package com.uclass.bookmanager.rent.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.uclass.bookmanager.member.model.vo.Member;
import com.uclass.bookmanager.rent.model.vo.Rent;
import com.uclass.common.db.JDBCTemplate;
import com.uclass.common.exception.DataAccessException;

public class RentBookDao {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public List<Rent> selectRentinfo(Connection conn, String id) throws DataAccessException{
		List<Rent> result = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * "
				+ "from tb_rent_master "
				+ "where user_id = ? and IS_RETURN = 0";
		try {
			result = new ArrayList<Rent>();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Rent rent = new Rent();
				rent.setRmIdx(rs.getInt(1));
				rent.setUserId(rs.getString(2));
				rent.setRegDate(rs.getDate(3));
				rent.setIsReturn(rs.getString(4));
				rent.setTitle(rs.getString(5));
				rent.setRentBookCnt(rs.getInt(6));
				result.add(rent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("SR01");
		}finally {
			jdt.close(rs, pstm);
		}
		return result;
	}
	
	public List<Map<String,Object>> selectRenbooktinfo(Connection con, String searchType, int rentIdx) throws DataAccessException{
		List<Map<String,Object>> result = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " select "
				+ " rb.rm_Idx, rb.rb_Idx, c.Info, b.title, b.author, "
				+ " rb.reg_date, rb.return_date, rb.EXTENTION_CNT "
				+ " from tb_rent_book rb"
				+ " inner join tb_book b using(b_idx) "
				+ " inner join tb_code c on(c.code = b.CATEGORY)"			
				+ " where rb.state in('RE00', 'RE01') and";
		switch (searchType) {
			case "rmIdx" : sql += " rb.rm_Idx = ? ";
				break;
			case "rbIdx" : sql += " rb.rb_Idx = ? ";
				break;
		}
		try {
			result = new ArrayList<Map<String,Object>>();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, rentIdx);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Map<String,Object> commandMap = new LinkedHashMap<>();
				commandMap.put("rmIdx",rs.getInt(1));
				commandMap.put("rbIdx",rs.getInt(2));
				commandMap.put("category",rs.getString(3));
				commandMap.put("title",rs.getString(4));
				commandMap.put("author",rs.getString(5));
				commandMap.put("regdate",rs.getDate(6));
				commandMap.put("returndate",rs.getDate(7));
				commandMap.put("extentionCnt",rs.getInt(8));
				result.add(commandMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("SR01");
		}finally {
			jdt.close(rs, pstm);
		}
		
		return result;
	}

	public int insertRentInfo(Connection con, String userId, String rm_title, int cnt) throws DataAccessException{
		int result = 0;
		PreparedStatement pstm = null;
		String sql = "insert into tb_rent_master "
				+ "(rm_idx,user_id,title,rent_book_cnt)"
				+ " values(SC_RM_IDX.nextval,?,?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userId);
			pstm.setString(2, rm_title);
			pstm.setInt(3, cnt);
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("IR01");
		}finally {
			jdt.close(pstm);
		}
		
		return result;
	}

	public boolean insertRentBookInfo(Connection con, int bidx) throws DataAccessException  {
		boolean isSuccess = false;
		CallableStatement cstm = null;
		String sql = "{call SP_RENT_INSERT(?,?)}";
		try {
			cstm = con.prepareCall(sql);
			cstm.setInt(1, bidx);
			cstm.registerOutParameter(2, java.sql.Types.NUMERIC);
			cstm.execute();
			int status = cstm.getInt(2);
			isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("IR01");
		} finally {
			jdt.close(cstm);
		}
		
		return isSuccess;
	}

	public boolean updateReturnRentBook(Connection con, int rm_idx, int rb_idx) throws DataAccessException {
		boolean isSuccess = false;
		CallableStatement cstm = null;
		String sql = "{call SP_RENT_RETURN(?,?,?,?)}";
		try {
			cstm = con.prepareCall(sql);
			cstm.setInt(1, rm_idx);
			cstm.setInt(2, rb_idx);	
			cstm.setString(3, Member.loginUser.getUserId());	
			cstm.registerOutParameter(4, java.sql.Types.NUMERIC);
			cstm.executeQuery();
			//int res = cstm.getInt(4);
			isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("UR01");
		}finally {
			jdt.close(cstm);
		}
		return isSuccess;
	}
	
	public boolean updateExtendRentState(Connection con, int rm_idx, int rb_idx) throws DataAccessException {
		boolean isSuccess = false;
		CallableStatement cstm = null;
		String sql = "{call SP_RENT_EXTEND(?,?,?)}";
		try {
			cstm = con.prepareCall(sql);
			cstm.setInt(1, rm_idx);
			cstm.setInt(2, rb_idx);		
			cstm.registerOutParameter(3, java.sql.Types.NUMERIC);
			cstm.executeQuery();
			int res = cstm.getInt(3);
			isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("UR01");
		}finally {
			jdt.close(cstm);
		}
		return isSuccess;
	}

	//프로시저 적용 전	
	public int updateExtendRentBook(Connection con, int rbidx) throws DataAccessException{
		int result = 0;
		PreparedStatement pstm = null;
		String sql = "update tb_rent_book "
				+ "set state = R003"
				+ ", EXTENTION_CNT = EXTENTION_CNT+1 "
				+ ", return_date = return_date + 7"
				+ " where rb_idx = " + rbidx;
		
		try {
			pstm = con.prepareStatement(sql);
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("UR01");
		}finally {
			jdt.close(pstm);
		}
		return result;
	}
	
	public int insertUpdateHistory(Connection con, int rb_idx) throws DataAccessException{
		
		PreparedStatement pstm = null;
		String sql = "insert into tb_rent_history " + 
				"select SC_RH_IDX.NEXTVAL, a.*" + 
				"from (select rm_idx, rb_idx, b_idx, sysdate, state" + 
				"from tb_rent_book where rb_idx = ?) a";
		
		int result = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, rb_idx);
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("IH01");
		}finally {
			jdt.close(pstm);
		}
		return result;
	}
}
