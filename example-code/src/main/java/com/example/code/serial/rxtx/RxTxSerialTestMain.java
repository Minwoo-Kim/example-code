package com.example.code.serial.rxtx;

public class RxTxSerialTestMain {
	public static void main(String[] args) {
		RxTxSerialConnection serial = new RxTxSerialConnection();
		serial.connect("Test Serial", "COM3", 9300);
	}
}
