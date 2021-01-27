package com.kh.jdbc.common.code;

//enum(enumerated type) : 열거형
//						  서로 연관된 상수들의 집합
//	서로 연관된 상수들을 편하게 다루기 위한 enum만의 문법적 형식과
//  메서드를 제공 해준다.
public enum MemberGrade {
	
	//회원등급코드, 등급명, 등급별 연장 가능 횟수
	//ex) 회원등급 코드가 ME00이면 등급명은 '일반'이고 연장 가능횟수는 1회
	
	//내부적으로 enum은 class와 다를 바가 없다.
	//ME00("일반",1) ->  public static final MemberGrade ME00 = new MemberGrade("일반",1); 
	//static 변수에 MemberGrade의 인스턴스가 담겨있기 때문에
	//어디서든 ME00.메서드명 으로 인스턴스를 생성할 때 매개변수로 넘겼던 값들을 확인할 수 있다.
	ME00("일반",1),
	ME01("성실",2),
	ME02("우수",3),
	ME04("VIP",4);
	
	//code 등급명
	private String desc;
	//회원등급별 연장 횟수
	private int extendableCnt;
	
	//접근제한자를 생략한 매개변수가 있는 생성자
	//ENUM에서 생성자는 DEFAULT로 private이다.
	MemberGrade(String desc, int extendableCnt){
		this.desc = desc;
		this.extendableCnt = extendableCnt;
	}
	
	public String desc() {
		return desc;
	}
	
	public int extandableCnt() {
		return extendableCnt;
	}
}
