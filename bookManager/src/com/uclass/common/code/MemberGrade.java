package com.uclass.common.code;

/**
 * @PackageName: com.uclass.common.code
 * @FileName : TableStatus.java
 * @Date : 2020. 6. 16.
 * @프로그램 설명 : 
 * @author :하명도
 */
public enum MemberGrade {
	
	ME00("일반",1),
	ME01("성실",2),
	ME02("우수",3),
	ME03("vip",4);

	private String desc;
	private int extendableCnt;
	
	MemberGrade(String desc, int extendableCnt) {
		this.desc = desc;
		this.extendableCnt = extendableCnt;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public int extendableCnt() {
		return extendableCnt;
	}
	
}
