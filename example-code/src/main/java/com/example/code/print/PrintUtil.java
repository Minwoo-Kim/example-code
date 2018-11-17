package com.example.code.print;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class PrintUtil {
	/**
	 * PrintService 가져오기 실행.
	 * 
	 * @param printName
	 * @return
	 */
	public static PrintService getPrintService(String printName) {
		PrintService printService = null;
		PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);

		for (PrintService service : services) {
			if (service.getName().equals(printName)) {
				printService = service;
				break;
			}
		}

		if (printService == null) {
			throw new IllegalArgumentException("Invalid Print Name.");
		}

		return printService;
	}
}
