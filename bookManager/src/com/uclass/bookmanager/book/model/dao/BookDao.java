package com.uclass.bookmanager.book.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uclass.bookmanager.book.model.vo.Book;
import com.uclass.common.db.JDBCTemplate;
import com.uclass.common.exception.DataAccessException;

public class BookDao{
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();

	public List<Book> selectBook(Connection conn, String searchType, String keyword) throws DataAccessException{
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from tb_book where ";
		List<Book> result = null;
		
		switch (searchType) {
			case "도서명" : sql += "title like '%'||?||'%'";
				break;
			case "작가명" : sql += "author like '%'||?||'%'";
				break;
		}
		
		try {
			result = new ArrayList<Book>();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, keyword);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setbIdx(rs.getInt(1));
				book.setIsbn(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setTitle(rs.getString(4));
				book.setAuthor(rs.getString(5));
				book.setInfo(rs.getString(6));
				book.setBookAmt(rs.getInt(7));
				book.setRegDate(rs.getDate(8));
				book.setRentCnt(rs.getInt(9));
				result.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("도서 검색 중 에러가 발생하였습니다.");
		}finally {
			jdt.close(rs, pstm);
		}
		
		return result;
	}
}
