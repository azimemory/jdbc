package com.uclass.bookmanager.book.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Book implements Serializable{
	
	private static final long serialVersionUID = -2101178531294902378L;
	
	private int bIdx;
	private String isbn;
	private String category;
	private String title;
	private String author;
	private String info;
	private int bookAmt;
	private Date regDate;
	private int rentCnt;
	
	public Book() {
		
	}

	/**
	 * @return the bIdx
	 */
	public int getbIdx() {
		return bIdx;
	}

	/**
	 * @param bIdx the bIdx to set
	 */
	public void setbIdx(int bIdx) {
		this.bIdx = bIdx;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the bookAmt
	 */
	public int getBookAmt() {
		return bookAmt;
	}

	/**
	 * @param bookAmt the bookAmt to set
	 */
	public void setBookAmt(int bookAmt) {
		this.bookAmt = bookAmt;
	}

	/**
	 * @return the rentCnt
	 */
	public int getRentCnt() {
		return rentCnt;
	}

	/**
	 * @param rentCnt the rentCnt to set
	 */
	public void setRentCnt(int rentCnt) {
		this.rentCnt = rentCnt;
	}

	@Override
	public String toString() {
		return "Book [bIdx=" + bIdx + ", isbn=" + isbn + ", category=" + category + ", title=" + title + ", author="
				+ author + ", info=" + info + ", regDate=" + regDate + ", bookAmt=" + bookAmt + ", rentCnt=" + rentCnt
				+ "]";
	}
	
	
	
}
