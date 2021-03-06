package com.example.code.serial.rxtx;

import java.io.IOException;
import java.io.OutputStream;

public class RxTxSerialWriter {
	OutputStream out;

	public RxTxSerialWriter(OutputStream out) {
		this.out = out;
	}

	public void run() {
		try {
			int c = 0;
			while ((c = System.in.read()) > -1) {
				this.out.write(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
