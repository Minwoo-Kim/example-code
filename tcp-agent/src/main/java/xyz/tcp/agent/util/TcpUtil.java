package xyz.tcp.agent.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import xyz.elidom.util.ValueUtil;

public class TcpUtil {

	/**
	 * 연결된 Session으로 메시지를 String형태로 전송.
	 * 
	 * @param session
	 * @param message
	 * @return
	 */
	public static boolean send(IoSession session, String message) {
		if (message == null || message.isEmpty())
			return true;

		return send(session, message.getBytes());
	}

	/**
	 * 연결된 Session으로 메시지 Byte 형태로 전송.
	 * 
	 * @param session
	 * @param rawMsg
	 * @return
	 */
	public static boolean send(IoSession session, byte[] rawMsg) {
		if (rawMsg == null)
			return true;

		IoBuffer buffer = IoBuffer.allocate(rawMsg.length, false);
		buffer.put(rawMsg);
		buffer.flip();

		session.write(buffer);

		return true;
	}

	/**
	 * 수신된 메시지를 String 형태로 변환.
	 * 
	 * @param message
	 * @return
	 */
	public static String parseReceivedMessage(Object message) {
		List<String> list = parseReceivedMessage(message, null);
		if (list == null || list.isEmpty())
			return null;

		return list.get(0);
	}

	public static List<String> parseReceivedMessage(Object message, Integer prefixSize) {
		if (!(message instanceof IoBuffer))
			return null;

		IoBuffer buffer = (IoBuffer) message;
		return parsedMessage(buffer.array(), prefixSize);
	}

	public static List<String> parsedMessage(byte[] rawMsg, Integer prefixSize) {
		List<String> list = new ArrayList<String>();

		/*
		 * Prefix 설정이 존재하지 않을 경우
		 */
		if (prefixSize == null || prefixSize == 0) {
			list.add(new String(rawMsg));
			return list;
		}

		/*
		 * Prefix 설정이 존재 할 경우
		 */
		int readSize = 0;
		String emptyByte = new String(new byte[prefixSize]);

		do {
			byte[] msgLengthArr = new byte[prefixSize];
			System.arraycopy(rawMsg, readSize, msgLengthArr, 0, prefixSize);
			String messageSizeStr = new String(msgLengthArr);

			// 내용이 존재하지 않으면 loop를 종료.
			if (ValueUtil.isEqual(emptyByte, messageSizeStr))
				break;

			Integer msgLength = ValueUtil.toInteger(messageSizeStr);

			// Prefix에 대한 정보가 올바르지 않을 경우 loop 종료.
			if (msgLength == null) {
				// list.add(value);
				// break;
				throw new RuntimeException("Invalid Prefix Value.[" + messageSizeStr + "]");
			}

			readSize += prefixSize;

			if (rawMsg.length < readSize + msgLength) {
				throw new RuntimeException("Invalid Message Value.[" + new String(rawMsg) + "]");
			}

			byte[] msgContentsArr = new byte[msgLength];
			System.arraycopy(rawMsg, readSize, msgContentsArr, 0, msgLength);
			readSize += msgLength;

			list.add(new String(msgContentsArr));
		} while (rawMsg.length > readSize);

		return list;
	}

	/**
	 * Close Session
	 * 
	 * @param session
	 * @return
	 */
	public static boolean closeSession(IoSession session) {
		if (ValueUtil.isNotEmpty(session))
			session.closeNow();
		return true;
	}

	/**
	 * int형을 byte배열로 바꿈
	 * 
	 * @param integer
	 * @param order
	 * @return
	 */
	public static byte[] intTobyte(int integer, ByteOrder order) {
		ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE / 8);
		buff.order(order);
		buff.putInt(integer);
		return buff.array();
	}

	/**
	 * byte배열을 int형로 바꿈
	 * 
	 * @param bytes
	 * @param order
	 * @return
	 */
	public static int byteToInt(byte[] bytes, ByteOrder order) {
		ByteBuffer buff = ByteBuffer.allocate(bytes.length);
		buff.order(order);
		buff.put(bytes);
		buff.flip();
		return buff.getInt();
	}
}
