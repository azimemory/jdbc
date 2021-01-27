package com.kh.jdbc.member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.member.model.vo.Member;

//DAO : DBMS에 접근해 데이터의 조회, 수정, 삽입, 삭제 요청을 보내는 클래스
//DAO의 메서드는 가능하다면 하나의 메서드에 하나의 쿼리만 처리하도록 작성
public class MemberDao {

	public MemberDao() {}
	
	public Member memberAuthenticate(String userId, String password){
		
		Member member = null;
		
		//DBMS와의 연결을 관리
		//transaction(commit, rollback) 관리
		Connection conn = null;
		
		//쿼리 실행용 객체
		PreparedStatement pstm = null;
		
		//Select쿼리의 결과로 반환된 데이터를 저장하는 객체
		ResultSet rset = null;
		
		try {
			//jdbc 코딩 순서
			//1. 오라클 jdbc driver를 jvm에 등록
			// - java reflection 찾아보기
			// - forName메서드의 전달인자로 넣어준 문자열은 OracleDriver 클래스의 fullName
			// Class.forName메서드는 매개변수로 받은 클래스명의 클래스객체를 반환
			// Class.forName메서드로 반환받은 Class객체를 통해 해당 Class의 메서드를 사용하거나
			// 새로운 인스턴스를 반환받는 등으로 활용할 수 있다.
			//
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 데이터베이스와 연결처리
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
					,"BOOKMANAGER","USER11");
			
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
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return member;
	}
	
	public Member selectMemberById(String userId){
		
		Member member = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
					, "BOOKMANAGER", "USER11");
			
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
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return member;
	}
 		
	public ArrayList<Member> selectMemberList(){
		
		ArrayList<Member> memberList = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
								,"BOOKMANAGER","USER11");
			
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
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return memberList;
	}
	
	public int insertMember(Member member){
		
		int res = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
					,"BOOKMANAGER","USER11");
			
			String query = "insert into tb_member(user_id,password,email,tell) "
					+"values(?,?,?,?)";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getUserId());
			pstm.setString(2,member.getPassword());
			pstm.setString(3, member.getEmail());
			pstm.setString(4, member.getTell());
			
			res = pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	//' or 1=1 or user_id = ' 
	// SQL Injection 공격
	// SQL 구문을 주입해 상대방의 DataBase 를 공격하는 기법
	public int updateMember(Member member){
		int res = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
					,"BOOKMANAGER","USER11");
			
			String query = "update tb_member set password = ? where user_id = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getPassword());
			pstm.setString(2, member.getUserId());
			res = pstm.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	public int deleteMember(String userId){
		
		int res = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
					,"BOOKMANAGER","USER11");
			
			String query = "delete from tb_member where user_id = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			
			res = pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	public List<Member> selectMemberByRegdate(Date begin, Date end){
		
		List<Member> memberList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BOOKMANAGER","USER11");
			
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
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return memberList;
	}
}
