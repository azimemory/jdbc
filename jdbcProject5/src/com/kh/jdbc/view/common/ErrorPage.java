package com.kh.jdbc.view.common;

public class ErrorPage {
	public void printError(String errMsg) {
		System.out.println("\n******* error *******");
		System.out.println(errMsg);
		System.out.println("******* error *******\n");
	}
}
