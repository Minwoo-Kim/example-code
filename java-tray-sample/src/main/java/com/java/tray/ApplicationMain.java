/**
 * 
 */
package com.java.tray;

import com.java.tray.system.TrayActionListener;

/**
 * @author Minu.Kim
 *
 */
public class ApplicationMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TrayActionListener().initTray();
	}
}
