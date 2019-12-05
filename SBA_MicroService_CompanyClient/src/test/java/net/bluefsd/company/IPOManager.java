package net.bluefsd.company;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class IPOManager {
	public static void create_1(MockHttpServletRequestBuilder request) {
		request.param("companyCd", "01NEL");
		request.param("exchangeCd", "NSE");
		request.param("pricePerShare", "10.00");
		request.param("totalShares", "1000");
		request.param("openDate", "2020-02-02 09:00:00");
		request.param("remarks", "good luck");
	}

	public static void create_2(MockHttpServletRequestBuilder request) {
		request.param("companyCd", "01YXL");
		request.param("exchangeCd", "NSE");
		request.param("pricePerShare", "10.00");
		request.param("totalShares", "1000");
		request.param("openDate", "2020-02-02 09:00:00");
		request.param("remarks", "good luck");
	}

	private static Timestamp getTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date timeDate = null;
		try {
			timeDate = dateFormat.parse("2020-02-02 09:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
		return dateTime;
	}

}
