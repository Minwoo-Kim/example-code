/**
 * 
 */
package com.java.tray;

import java.awt.Toolkit;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Minu.Kim
 *
 */
public class ApplicationMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TrayIconHandler.registerTrayIcon(Toolkit.getDefaultToolkit().getImage("src/main/resources/hatio.png"), "Example",
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Open your application here.
					}
				});

		TrayIconHandler.addItem("Exit", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		TrayIconHandler.displayMessage("Silentsoft", "Benefit the world !", MessageType.INFO);
	}
}
