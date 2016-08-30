package com.spring.boot.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.boot.util.ValueUtil;

/**
 * Agent 상태 체크 Job
 * 
 * @author Minu.Kim
 */
@Component
public class TestJob {

//	@Autowired
//	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;

	public static String CONTENTS = "None";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Scheduled(fixedRate = 60000)
	public void doExecuteJob() {
		try {
			String url = "http://www.airbusan.com/web/bookingApi/domesticAvail?bookingCategory=Individual&depDate=20160914&depCity=GMP&arrCity=PUS&foc=N&bookingClass=ES";

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("Host", "www.airbusan.com");
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
			con.setRequestProperty("Referer","http://www.airbusan.com/web/individual/booking/domestic?bookingCategory=Individual&foc=N&domesticTripType=OW&depCity=GMP&arrCity=PUS&dateGoing=2016-09-14&passengerCountCorp=0&passengerCountAdult=1&RR4H5HJOIWT43TOLDXHX=1&passengerCountChild=0&passengerCountInfant=0");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
			con.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
			con.setRequestProperty("Cookie","_gat=1; _ga=GA1.2.1507180003.1472201517; wcs_bt=bccd2d8446215c:1472202178|s_11681f2fc3c3:1472202178; NetFunnel_ID=5002%3A200%3Akey%3D9D53A49D6D098E9DAE8ACF2B9A2BB08BD097A552E0DD0FE5E0FAB443795DC685B5A3846B6B19D1CEF64E7B5579F594D73042F67BFC14CCE3DD7CEA288DF28A8C26898FFA2CA72DFE6D0B721F86BF375941A98A8F44ABDA306BE0249DA0254E604FC18FFE5E722ACA3C738AA434385B796963417661696C2C302C322C312C30%26nwait%3D0%26nnext%3D0%26tps%3D0%26ttl%3D0%26ip%3Dpromo.airbusan.com%26port%3D80");

			String inputLine;
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			Map map = ValueUtil.jsonToObject(response.toString(), Map.class);
			List<Map<String, String>> listMap = (List<Map<String, String>>) map.get("list");

			StringJoiner printContents = new StringJoiner("\n");
			StringJoiner emailContents = new StringJoiner("<br>");
			for (Map<String, String> data : listMap) {
				int count = Integer.parseInt(data.get("availSeat"));
				String seat = new StringBuilder().append(data.get("depTime")).append(" : ").append(count).toString();
				if (count > 0) {
					printContents.add(seat);
				}
				if (count > 1) {
					emailContents.add(seat);
				}
			}
			String content = ValueUtil.checkValue(emailContents.toString(), "None");
			if (ValueUtil.isNotEqual(content, CONTENTS)) {
				this.sendMail(content);
				CONTENTS = content;
			}

			System.out.println(ValueUtil.checkValue(printContents.toString(), "None") + " - " + new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMail(String contents) {
		String receiveUser = "minuhappy@gmail.com";
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(receiveUser);
		mailMessage.setReplyTo(receiveUser);
		mailMessage.setFrom("bluesky@hatiolab.com");
		mailMessage.setSubject("Information");
		mailMessage.setText(contents);
		
		// TODO attachments
		this.javaMailSender.send(mailMessage);
	}
}