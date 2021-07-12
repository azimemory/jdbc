package com.uclass.bookmanager.rent.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.uclass.bookmanager.book.model.vo.Book;
import com.uclass.bookmanager.member.model.vo.Member;
import com.uclass.bookmanager.rent.model.service.RentBookService;
import com.uclass.bookmanager.rent.model.vo.Rent;
import com.uclass.common.code.MemberGrade;

public class RentController {
	
	private RentBookService rentBookService = new RentBookService();
	
	public List<Rent> searchRentinfo(){
		List<Rent> result = rentBookService.selectRentinfo(Member.loginUser.getUserId());
		return result;
	}
	
	public List<Map<String,Object>> searchRentbook(Member user, String searchType, String menuNumber){
		int rmIdx = Integer.parseInt(menuNumber); 
		List<Map<String,Object>> result = rentBookService.selectRentinfo(user.getUserId(), searchType, rmIdx);
		return result;
	}
	
	public String rentBook(Member user, String rbNo, List<Book> bookList) {
		String result = "도서 대출이 성공적으로 마무리 되었습니다.";
		String[] sArr = rbNo.split(",");
		List<Book> rentBookList = new ArrayList<Book>();
		
		for(int i = 0; i < sArr.length; i++) {
			for(Book book : bookList) {
				if(book.getbIdx() == Integer.parseInt(sArr[i])){
					rentBookList.add(book);
				}
			}
		}
		
		boolean isSuccess = rentBookService.insertRentBookInfo(user.getUserId(),rentBookList);
		if(!isSuccess) 
			result = "도서 대출에 실패했습니다.";
		return result;
	}
	
	public String returnBook(String inputRmIdx, String inputRbIdx) {
		String result = "도서 반납에 성공했습니다.";
		int rmIdx = Integer.parseInt(inputRmIdx); 
		int rbIdx = Integer.parseInt(inputRbIdx); 
		boolean isSuccess = rentBookService.updateReturnRentBook(rmIdx, rbIdx);
		
		if(!isSuccess) {
			result = "도서반납에 실패했습니다.";
		}
		
		return result;
	}

	public String extendBook(Map<String, Object> extendBook) {
		String result = "";
		int rmIdx = (int)extendBook.get("rmIdx"); 
		int rbIdx = (int)extendBook.get("rbIdx"); 
		String grade = Member.loginUser.getGrade();
		
		if(MemberGrade.valueOf(grade).extendableCnt() > (int)extendBook.get("extentionCnt")){
			boolean isSuccess = rentBookService.updateExtendRentBook(rmIdx,rbIdx);
			if(isSuccess) {
				result = "대출연장에 성공했습니다.";
			}else {
				result = "대출연장에 실패했습니다.";
			}
		}else {
			result = "연장 가능 횟수를 초과했습니다.";
		}
		return result;
	}

}
