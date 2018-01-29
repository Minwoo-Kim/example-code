/**
 * 
 */
package com.java.sample.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Minu.Kim
 *
 */
public class InetAddressSamp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InetAddress inet= InetAddress.getLocalHost();
			System.out.println(inet.getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
