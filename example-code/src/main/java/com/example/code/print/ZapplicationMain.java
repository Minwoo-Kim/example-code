package com.example.code.print;

public class ZapplicationMain {
	public static void main(String[] args) throws Exception {
		String printName = "Samsung SCX-3400 Series (SEC001599E13F05)";
		String command = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		new JavaPrint().doStreamPrint(printName, command);
		// new PrintablePrint().doPrint(printName, command);
	}
}
