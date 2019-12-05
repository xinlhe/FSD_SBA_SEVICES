package com.ibm.sba.microservice.smc.upload.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ibm.sba.microservice.smc.upload.service.SBAExcelService;

@RestController
@CrossOrigin
@RequestMapping("/upload")
public class SBAExcelController {

	@Autowired
	SBAExcelService excelService;

	@RequestMapping(value = "/excel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> importResult(@RequestParam("file") MultipartFile file) throws Exception {

	    Map<String, String> map = new HashMap<String, String>();
	    
		String fileName = file.getOriginalFilename();
		
		excelService.batchImport(fileName, file);
		
		map.put("retMsg", "Upload file Successful !!!");
		
		return map;
	}
}
