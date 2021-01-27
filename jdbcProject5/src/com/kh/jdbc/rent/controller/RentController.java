package com.kh.jdbc.rent.controller;

import java.util.List;

import com.kh.jdbc.book.model.vo.Book;
import com.kh.jdbc.common.code.ErrorCode;
import com.kh.jdbc.rent.model.service.RentService;
import com.kh.jdbc.rent.model.vo.Rent;
import com.kh.jdbc.rent.model.vo.RentBook;

public class RentController {
	
	RentService rentService = new RentService();
	
	public List<Rent> searchRentList(String userId){
		return rentService.selectRentList(userId);		
	}
	
	public List<RentBook> searchRentBookList(String rmIdx){
		return rentService.selectRentBookList(rmIdx);		
	}
	
	public String registRent(List<Book> bookList, String userId) {
		
		Rent rent = new Rent();
		//대출 건 제목 작성 / 000외 N권
		String title = bookList.get(0).getTitle() + " 외" 
				+ (bookList.size()-1) + "권";
		
		rent.setUserId(userId);
		rent.setTitle(title);
		rent.setRentBookCnt(bookList.size());
		
		if(rentService.insertRentInfo(rent, bookList)) {
			return title;
		}else {
			return ErrorCode.IR01.errMsg();
		}
	}
	
	public boolean returnBook(int rbIdx) {
		return false;
	}
	
	public boolean extendBook(int rbIdx) {
		return false;
	}
}
