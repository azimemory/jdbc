package com.uclass.bookmanager.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.uclass.bookmanager.member.model.vo.Member;
import com.uclass.common.db.JDBCTemplate;
import com.uclass.common.exception.DataAccessException;

public class MemberDao{
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();

	public Member selectMember(Connection conn, String userId, String password) throws DataAccessException {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Member member = null;
		String sql = "select * from tb_member where user_id = ? and password = ? ";
	
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			pstm.setString(2, password);
			rs = pstm.executeQuery();

			if(rs.next()) {
				member = new Member();
				member.setUserId(rs.getString(1));
				member.setPassword(rs.getString(2));
				member.setEmail(rs.getString(3));
				member.setGrade(rs.getString(4));
				member.setTell(rs.getString(5));
				member.setRegDate(rs.getDate(6));
				member.setRentableDate(rs.getDate(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("SM01");
		}finally {
			jdt.close(rs,pstm);
		}
		
		return member;
	}
	
	public void checkId(Connection conn, Member member) throws DataAccessException{
		String sql = "select count(*) from tb_member where user_id = '" + member.getUserId() + "'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				if(rs.getInt(1) > 0) 
					throw new DataAccessException("SM03");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("SM02");
		}finally {
			jdt.close(rs,stmt);
		}
	}
	
	public boolean insertMember(Connection con, Member member) throws DataAccessException{
		boolean isSuccess = true;
		String sql = "insert into tb_member"
				+ "(user_Id, password ,tell, email) "
				+ "values(?,?,?,?)"; 
		
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, member.getUserId());
			pstm.setString(2, member.getPassword());
			pstm.setString(3, member.getTell());
			pstm.setString(4, member.getEmail());
			if(pstm.executeUpdate() == 0) 
				isSuccess = false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("IM01");
		}finally {
			jdt.close(pstm);
		}
		
		return isSuccess;
	}

	public boolean updateMemberInfo(Connection con, Member member) throws DataAccessException{
		boolean isSuccess = true;
		String sql = "update tb_member set " 
		+ " password = ? "
		+ ", tell = ? "
		+ ", email = ? "
		+ " where user_id = ? ";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getTell());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getUserId());
			if(pstmt.executeUpdate() == 0) 
				isSuccess = false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("UM01");
		}finally {
			jdt.close(pstmt);
		}
		
		return isSuccess;
	}
}
