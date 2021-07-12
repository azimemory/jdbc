package com.kh.jdbc.book.model.vo;

import java.sql.Date;

public class Book {
	
	private String bkIdx;
	private String isbn;
	private String category;
	private String title;
	private String author;
	private String info;
	private int bookAmt;
	private Date regDate;
	private int rentCnt;	
	
	public String getBkIdx() {
		return bkIdx;
	}

	public void setBkIdx(String bkIdx) {
		this.bkIdx = bkIdx;
	}

	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public int getBookAmt() {
		return bookAmt;
	}
	
	public void setBookAmt(int bookAmt) {
		this.bookAmt = bookAmt;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public int getRentCnt() {
		return rentCnt;
	}
	
	public void setRentCnt(int rentCnt) {
		this.rentCnt = rentCnt;
	}

	@Override
	public String toString() {
		return "Book [bkIdx=" + bkIdx + ", isbn=" + isbn + ", category=" + category + ", title=" + title + ", author="
				+ author + ", info=" + info + ", bookAmt=" + bookAmt + ", regDate=" + regDate + ", rentCnt=" + rentCnt
				+ "]";
	}
}
