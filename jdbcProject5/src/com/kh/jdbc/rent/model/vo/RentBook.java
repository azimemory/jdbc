package com.kh.jdbc.rent.model.vo;

import java.sql.Date;

public class RentBook {
	
	private int rbIdx;
	private int rmIdx;
	private int bIdx;
	private Date regDate;
	private String state;
	private Date returnDate;
	private int extentionCnt;
	
	public int getRbIdx() {
		return rbIdx;
	}
	
	public void setRbIdx(int rbIdx) {
		this.rbIdx = rbIdx;
	}
	
	public int getRmIdx() {
		return rmIdx;
	}
	
	public void setRmIdx(int rmIdx) {
		this.rmIdx = rmIdx;
	}
	
	public int getbIdx() {
		return bIdx;
	}
	
	public void setbIdx(int bIdx) {
		this.bIdx = bIdx;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Date getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public int getExtentionCnt() {
		return extentionCnt;
	}
	
	public void setExtentionCnt(int extentionCnt) {
		this.extentionCnt = extentionCnt;
	}
	
	@Override
	public String toString() {
		return "RentBook [rbIdx=" + rbIdx + ", rmIdx=" + rmIdx + ", bIdx=" + bIdx + ", regDate=" + regDate + ", state="
				+ state + ", returnDate=" + returnDate + ", extentionCnt=" + extentionCnt + "]";
	}
	
	

}
