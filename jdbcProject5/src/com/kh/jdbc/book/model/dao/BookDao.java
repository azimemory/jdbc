package com.kh.jdbc.book.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.book.model.vo.Book;
import com.kh.jdbc.common.template.JDBCTemplate;

public class BookDao {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public List<Book> selectAllBooks(Connection conn) throws SQLException{
		
		Book book = null;
		List<Book> bookList = new ArrayList<Book>();
		
		//PreparedStatement : 미리 준비된 쿼리
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from book";
		
		try {
			//3. 쿼리문 실행용 객체 생성함
			pstm = conn.prepareStatement(query);
						
			//4. 쿼리문 실행하고 결과 받음
			rset = pstm.executeQuery(query);
			while(rset.next()) {
				book = new Book();
				book.setBkIdx(rset.getString("bk_idx"));
				book.setTitle(rset.getString("title"));
				book.setAuthor(rset.getString("author"));
				book.setInfo(rset.getString("info"));
				book.setIsbn(rset.getString("isbn"));
				book.setCategory(rset.getString("category"));
				book.setBookAmt(rset.getInt("book_amt"));
				bookList.add(book);
			}
		} finally {
			jdt.close(rset, pstm);
		}
		
		return bookList;
	}
	
	public List<Book> selectBookOrderByRank(Connection conn) throws SQLException {
		
		Book book = null;
		List<Book> bookList = new ArrayList<Book>();
		
		//PreparedStatement : 미리 준비된 쿼리
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select" + 
				" bk_idx, title, author, info, isbn, category, book_amt " +  
				" from(" + 
				" select rownum rnum, b.*" + 
				" from(" + 
				" select * " + 
				" from book" + 
				" order by rent_cnt desc" + 
				" ) b" + 
				" )where rnum between 1 and 5";
		
		try {
			//3. 쿼리문 실행용 객체 생성함
			pstm = conn.prepareStatement(query);
						
			//4. 쿼리문 실행하고 결과 받음
			rset = pstm.executeQuery(query);
			while(rset.next()) {
				book = new Book();
				book.setBkIdx(rset.getString("bk_idx"));
				book.setTitle(rset.getString("title"));
				book.setAuthor(rset.getString("author"));
				book.setInfo(rset.getString("info"));
				book.setIsbn(rset.getString("isbn"));
				book.setCategory(rset.getString("category"));
				book.setBookAmt(rset.getInt("book_amt"));
				bookList.add(book);
			}
		} finally {
			jdt.close(rset, pstm);
		}
		
		return bookList;
	}
	
	public Book selectBookByTitle(Connection conn, String title) throws SQLException {
		
		Book book = null;

		//PreparedStatement : 미리 준비된 쿼리
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from book where title like '%'||?||'%'";
		
		try {
			//3. 쿼리문 실행용 객체 생성함
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, title);
						
			//4. 쿼리문 실행하고 결과 받음
			rset = pstm.executeQuery();
			if(rset.next()) {
				book = new Book();
				book.setBkIdx(rset.getString("bk_idx"));
				book.setTitle(rset.getString("title"));
				book.setAuthor(rset.getString("author"));
				book.setInfo(rset.getString("info"));
				book.setIsbn(rset.getString("isbn"));
				book.setCategory(rset.getString("category"));
				book.setBookAmt(rset.getInt("book_amt"));
			}
		} finally {
			jdt.close(rset, pstm);
		}
		
		return book;
	}
	
	public int insertBook(Connection conn, Book book) throws SQLException {
		
		PreparedStatement pstm = null;
		int res = 0;
		
		String query = "insert into book(bk_idx, title, author, isbn, category) values(sc_bk_idx.nextval,?,?,?,?)";
		
		try {
			//3. 쿼리문 실행용 객체 생성함
			pstm = conn.prepareStatement(query);
			//PreparedStatement의 쿼리문을 완성한다.
			pstm.setString(1,book.getTitle());
			pstm.setString(2,book.getAuthor());
			pstm.setString(3,book.getIsbn());
			pstm.setString(4,book.getCategory());
						
			//4. 쿼리문 실행하고 결과 받음
			// executeUpdate : 실행된 레코드 수를 반환해 준다.
			res = pstm.executeUpdate();
			
		} finally {
			jdt.close(pstm);
		}
		
		return res;
	}

	public int updateBook(Connection conn, Book book) throws SQLException {
		PreparedStatement pstm = null;
		int res = 0;
		
		String query = "update book(title, author, isbn, category) values(?,?,?,?) where bk_idx = ?";
		
		try {
			//3. 쿼리문 실행용 객체 생성함
			pstm = conn.prepareStatement(query);
			//PreparedStatement의 쿼리문을 완성한다.
			pstm.setString(1,book.getTitle());
			pstm.setString(2,book.getAuthor());
			pstm.setString(3,book.getIsbn());
			pstm.setString(4,book.getCategory());
			pstm.setString(5,book.getBkIdx());
			//4. 쿼리문 실행하고 결과 받음
			// executeUpdate : 실행된 레코드 수를 반환해 준다.
			res = pstm.executeUpdate();
			
		} finally {
			jdt.close(pstm);
		}
		
		return res;
	}
	
	public int deleteBookByBIdx(Connection conn, int bIdx) throws SQLException {
		
		PreparedStatement pstm = null;
		int res = 0;
		
		String query = "delete from book where bk_idx = ?";
		
		try {
			//3. 쿼리문 실행용 객체 생성함
			pstm = conn.prepareStatement(query);
			//PreparedStatement의 쿼리문을 완성한다.
			pstm.setInt(1,bIdx);
						
			//4. 쿼리문 실행하고 결과 받음
			// executeUpdate : 실행된 레코드 수를 반환해 준다.
			res = pstm.executeUpdate();
			
		} finally {
			jdt.close(pstm);
		}
		
		return res;
	}
}
