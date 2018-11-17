package com.example.code.print.printable;

import java.awt.print.Book;
import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobSheets;

import com.example.code.print.PrintUtil;

public class PrintablePrint {
	public void doPrint(String printName, String command) throws Exception {
		PrinterJob pjob = PrinterJob.getPrinterJob();
		pjob.setPrintService(PrintUtil.getPrintService(printName));
		
		Book book = new Book();
		book.append(new PrintableImpl(), pjob.defaultPage());
		
		pjob.setPageable(book);
		
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		pras.add(JobSheets.NONE);
		
		// Printing
		pjob.print(pras);
	}
}
