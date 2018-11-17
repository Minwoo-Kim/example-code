package com.example.code.print.printable;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class PrintableImpl implements Printable {

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		graphics.drawString("ddddddddddddddddddddddddd", 72, 100);
		return Printable.PAGE_EXISTS;
	}
}
