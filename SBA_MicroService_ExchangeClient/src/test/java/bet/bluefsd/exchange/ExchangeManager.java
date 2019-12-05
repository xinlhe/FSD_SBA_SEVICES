package bet.bluefsd.exchange;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class ExchangeManager {
	public static void create1(MockHttpServletRequestBuilder request) {
		request.param("exchCd", "BSE");
		request.param("exchName", "BSE stock exchange");
		request.param("brief", "established in 1987");
		request.param("contact_addr", "Manama, Bahrain");
		request.param("remarks", "I have no more information");
	}

	public static void create2(MockHttpServletRequestBuilder request) {
		request.param("exchCd", "NSE");
		request.param("exchName", "National Stock Exchange");
		request.param("brief", "The largest stock exchange in India");
		request.param("contact_addr", "Manama, Bahrain");
		request.param("remarks", "will get more information");

	}

	public static void update(MockHttpServletRequestBuilder request) {
		request.param("exchCd", "NSE");
		request.param("exchName", "National Stock Exchange");
		request.param("brief", "The largest stock exchange in India");
		request.param("contact_addr", "Manama, Bahrain");
		request.param("remarks", "update with no more information");
	}
}
