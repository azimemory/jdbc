package com.uclass.bookmanager.member.model.service;

import java.sql.Connection;

import com.uclass.bookmanager.member.model.dao.MemberDao;
import com.uclass.bookmanager.member.model.vo.Member;
import com.uclass.common.db.JDBCTemplate;
import com.uclass.common.exception.DataAccessException;
import com.uclass.common.exception.service.ErrorService;

public class MemberService{
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	private MemberDao memberDao = new MemberDao();
	
	public Member selectMember(String userId, String password) {
		Connection conn = jdt.getConnection();
		Member member = null;

		try {
			member = memberDao.selectMember(conn, userId, password);
		} catch (DataAccessException e) {
			//사용자 또는 개발자에게 적절한 안내메시지를 보내준다.
			new ErrorService().printErrorMessage(e);
		}finally {
			jdt.close(conn);
		}
		
		return member;
	}
	
	public boolean insertMember(Member member){
		Connection conn = jdt.getConnection();
		boolean isSuccess = false;
		try {
			memberDao.checkId(conn, member);
			isSuccess = memberDao.insertMember(conn, member);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			new ErrorService().printErrorMessage(e);
		}finally {
			jdt.close(conn);
		}
		
		return isSuccess;
	}

	public boolean updateMemberInfo(Member member) {
		Connection conn = jdt.getConnection();
		boolean isSuccess = false;

		try {
			isSuccess = memberDao.updateMemberInfo(conn, member);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			new ErrorService().printErrorMessage(e);
			return false;
		}finally {
			jdt.close(conn);
		}
		return isSuccess;
	}
}
