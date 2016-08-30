/**
 * 
 */
package com.spring.boot.mail;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Minu.Kim
 *
 */
@Service
public class SimpleMailSender {

	@Autowired
	private Environment env;

	@Autowired
	private MailSender mailSender;

	// @Autowired
	// private JavaMailSender javaMailSender;

	public void sendMail() {
		// SimpleMailMessage smm = new SimpleMailMessage();
		// mailSender.send(simpleMessage);
	}

	public void sendTextMail(String title, String content, String from, String to, File[] files) {
		if (from == null || from.isEmpty()) {
			from = env.getProperty("spring.mail.username");
		}

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(StringUtils.tokenizeToStringArray(to, ","));
		mailMessage.setReplyTo(to);
		mailMessage.setFrom(from);
		mailMessage.setSubject(title);
		mailMessage.setText(content);
		// TODO attachments
		this.mailSender.send(mailMessage);
	}
}
