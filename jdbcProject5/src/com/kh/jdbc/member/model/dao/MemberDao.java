package com.kh.jdbc.member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.common.code.ErrorCode;
import com.kh.jdbc.common.code.MemberGrade;
import com.kh.jdbc.common.exception.DataAccessException;
import com.kh.jdbc.common.template.JDBCTemplate;
import com.kh.jdbc.member.model.vo.Member;

//DAO : DBMS에 접근해 데이터의 조회, 수정, 삽입, 삭제 요청을 보내는 클래스
//DAO의 메서드는 가능하다면 하나의 메서드에 하나의 쿼리만 처리하도록 작성
public class MemberDao {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();

	public MemberDao() {}
	
	public Member memberAuthenticate(Connection conn, String userId, String password){
		
		Member member = null;
		
		//쿼리 실행용 객체
		PreparedStatement pstm = null;
		
		//Select쿼리의 결과로 반환된 데이터를 저장하는 객체
		ResultSet rset = null;
		
		try {
			
			String query = "select * from tb_member where user_id = ? and password = ?";
			
			//3. 쿼리 실행용 객체 생성
			pstm = conn.prepareStatement(query);
			
			//4. PreparedStatement의 쿼리를 완성
			// setString(int idx, String val) : idx번째 물음표에 val 를 넣겠다.
			pstm.setString(1, userId);
			pstm.setString(2, password);
			
			//5. 쿼리를 실행하고 질의결과(ResultSet) 을 받음
			rset = pstm.executeQuery();
			
			//6. resultSet에 저장된 데이터를 VO객체로 옮겨담기
			//next() : 현재 위치에서 다음 row에 데이터가 있는 지 확인하고 커서를 이동
			// 다음 row에 데이터가 있다면 true, 없으면  false를 반환
			if(rset.next()) {
				member = new Member();
				//현재 row의 user_id 컬럼값을 자바의 String타입으로 가져온다.
				member.setUserId(rset.getString("user_id"));
				member.setPassword(rset.getString("password"));
				member.setGrade(rset.getString("grade"));
				member.setTell(rset.getString("tell"));
				member.setRegDate(rset.getDate("reg_date"));
				member.setEmail(rset.getString("email"));
				member.setRentableDate(rset.getDate("rentable_date"));
			}
		System.out.println("데이터베이스로 부터 받아온 데이터 확인 : " + member);
		//SQLException : db와 통신 중에 발생하는 모든 예외를 담당하는 Exception	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataAccessException(e.getMessage(),ErrorCode.SM01.errMsg());
		} finally {
			jdt.close(rset, pstm);
		}
		
		return member;
	}
	
	public Member selectMemberById(Connection conn, String userId){
		
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String query = "select * from tb_member where user_id = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				member = new Member();
				member.setUserId(rset.getString("user_id"));
				member.setPassword(rset.getString("password"));
				member.setEmail(rset.getString("email"));
				member.setTell(rset.getString("tell"));
				member.setRegDate(rset.getDate("reg_date"));
				member.setRentableDate(rset.getDate("rentable_date"));
				member.setIsLeave(rset.getInt("is_leave"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataAccessException(e.getMessage(),ErrorCode.SM01.errMsg());
		} finally {
			jdt.close(rset,pstm);
		}
		
		return member;
	}
 		
	public ArrayList<Member> selectMemberList(Connection conn){
		
		ArrayList<Member> memberList = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String query = "select * from tb_member";
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Member member = new Member();
				member.setUserId(rset.getString("user_id"));
				member.setPassword(rset.getString("password"));
				member.setEmail(rset.getString("email"));
				member.setTell(rset.getString("tell"));
				member.setRegDate(rset.getDate("reg_date"));
				member.setRentableDate(rset.getDate("rentable_date"));
				member.setIsLeave(rset.getInt("is_leave"));
				memberList.add(member);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataAccessException(e.getMessage(),ErrorCode.SM01.errMsg());
		}finally {
			jdt.close(rset,pstm);
		}
		
		return memberList;
	}
	
	public int insertMember(Connection conn, Member member){
		
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			String query = "insert into tb_member(user_id, password, email, tell) "
					+"values(?,?,?,?)";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getUserId());
			pstm.setString(2,member.getPassword());
			pstm.setString(3, member.getEmail());
			pstm.setString(4, member.getTell());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage(),ErrorCode.IM01.errMsg());
		}finally {
			jdt.close(pstm);
		}
		
		return res;
	}
	
	//' or 1=1 or user_id = ' 
	// SQL Injection 공격
	// SQL 구문을 주입해 상대방의 DataBase 를 공격하는 기법
	public int updateMember(Connection conn, Member member){
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			String query = "update tb_member set password = ? where user_id = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getPassword());
			pstm.setString(2, member.getUserId());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage(), ErrorCode.UM01.errMsg());
		}finally {
			jdt.close(pstm);
		}
		
		return res;
	}
	
	public int deleteMember(Connection conn, String userId){
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			String query = "delete from tb_member where user_id = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage(),ErrorCode.DM01.errMsg());
		}finally {
			jdt.close(pstm);
		}
		
		return res;
	}
	
	public List<Member> selectMemberByRegdate(Connection conn, Date begin, Date end){
		
		List<Member> memberList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String query = "select * from tb_member where reg_date between ? and ?";
			pstm = conn.prepareStatement(query);
			pstm.setDate(1, begin);
			pstm.setDate(2, end);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Member member = new Member();
				member.setUserId(rset.getString("user_id"));
				member.setPassword(rset.getString("password"));
				member.setEmail(rset.getString("email"));
				member.setTell(rset.getString("tell"));
				member.setIsLeave(rset.getInt("is_leave"));
				memberList.add(member);
			}
		} catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataAccessException(e.getMessage(),ErrorCode.SM01.errMsg());
		} finally {
			jdt.close(rset, pstm);
		}
		
		return memberList;
	}
}
