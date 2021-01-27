package com.kh.jdbc.rent.model.vo;

import java.sql.Date;

public class Rent {
	
	private int rmIdx;
	private String userId;
	private Date regDate;
	private int isReturn;
	private String title;
	private int rentBookCnt;
	
	public int getRmIdx() {
		return rmIdx;
	}
	
	public void setRmIdx(int rmIdx) {
		this.rmIdx = rmIdx;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public int getIsReturn() {
		return isReturn;
	}
	
	public void setIsReturn(int isReturn) {
		this.isReturn = isReturn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getRentBookCnt() {
		return rentBookCnt;
	}
	
	public void setRentBookCnt(int rentBookCnt) {
		this.rentBookCnt = rentBookCnt;
	}
	
	@Override
	public String toString() {
		return "Rent [rmIdx=" + rmIdx + ", userId=" + userId + ", regDate=" + regDate + ", isReturn=" + isReturn
				+ ", title=" + title + ", rentBookCnt=" + rentBookCnt + "]";
	}
	
	

}
