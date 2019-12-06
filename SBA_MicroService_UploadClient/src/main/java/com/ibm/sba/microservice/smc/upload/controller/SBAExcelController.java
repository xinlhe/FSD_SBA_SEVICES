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
	public Map<String, SBAResultBean> importResult(@RequestParam("file") MultipartFile file) throws Exception {

	    Map<String, SBAResultBean> map = new HashMap<String, SBAResultBean>();
	    
		String fileName = file.getOriginalFilename();
		
		SBAResultBean rb = excelService.batchImport(fileName, file);
		
		map.put("rb", rb);
		
		return map;
	}
}
