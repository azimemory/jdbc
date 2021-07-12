package com.uclass.bookmanager.rent.model.vo;

import java.sql.Date;

public class Rent {
	
	private int rmIdx;
	private String userId; 
	private Date regDate;
	private String isReturn;
	private String title;  
	private int rentBookCnt; 
	
	public Rent() {
		
	}

	/**
	 * @return the rmIdx
	 */
	public int getRmIdx() {
		return rmIdx;
	}

	/**
	 * @param rmIdx the rmIdx to set
	 */
	public void setRmIdx(int rmIdx) {
		this.rmIdx = rmIdx;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return the isReturn
	 */
	public String getIsReturn() {
		return isReturn;
	}

	/**
	 * @param isReturn the isReturn to set
	 */
	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
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
	 * @return the rentBookCnt
	 */
	public int getRentBookCnt() {
		return rentBookCnt;
	}

	/**
	 * @param rentBookCnt the rentBookCnt to set
	 */
	public void setRentBookCnt(int rentBookCnt) {
		this.rentBookCnt = rentBookCnt;
	}

	@Override
	public String toString() {
		return "Rent [rmIdx=" + rmIdx + ", userId=" + userId + ", regDate=" + regDate + ", isReturn=" + isReturn
				+ ", title=" + title + ", rentBookCnt=" + rentBookCnt + "]";
	}

}
