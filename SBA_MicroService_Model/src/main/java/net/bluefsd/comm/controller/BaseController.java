package net.bluefsd.comm.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * This is Base Controller
 * 
 * @author XinLongHe
 *
 * @since 2019.11.29
 */
public class BaseController {

	@Autowired
	protected MessageSource messageSource;

	public Map successMessage() {
	    
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", "0");
		
		map.put("retMsg", messageSource.getMessage("success", null, "success", Locale.CHINA));
		
		return map;
	}

	public Map successMessage(String msg) {
	    
		Map<String, Object> map = new HashMap<>();

		map.put("status", "0");
		
		map.put("retMsg", msg);
		
		return map;
	}

	public Map successMessage(String key, Object object) {
		
	    Map<String, Object> map = new HashMap<>();
		
	    map.put("status", "0");
		
	    map.put(key, object);
		
	    map.put("retMsg", "success");
		
	    return map;
	}

	public Map errorMessage(String message) {
	    
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", "-1");
		
		map.put("retMsg", message);
		
		return map;
	}
}
