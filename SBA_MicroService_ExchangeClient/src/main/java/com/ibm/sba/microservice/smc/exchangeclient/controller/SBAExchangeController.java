package com.ibm.sba.microservice.smc.exchangeclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.sba.microservice.smc.exchangeclient.service.SBAExchangeService;
import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.entity.Company;
import net.bluefsd.entity.Exchange;

@RestController
@CrossOrigin
@RequestMapping("/exchange")
public class SBAExchangeController extends BaseController {
	@Autowired
	SBAExchangeService exchnageService;

	@RequestMapping(value = { "/add", "/create", "update" }, method = RequestMethod.POST)
	public Exchange addStockExch(@RequestBody Exchange exchange) {
	    
		return exchnageService.addExchange(exchange);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<Exchange> listExchange() {
	    
		return exchnageService.listExchange();
	}

	@RequestMapping(value = "/listcompany", method = RequestMethod.POST)
	public List<Company> listCompanyByStockExch(String exchCd) {
	    
		return exchnageService.listCompanyByExchange(exchCd);
	}
	
	@RequestMapping(value = "/findexc", method = RequestMethod.POST)
    public Exchange findExchange(String exchCd) {
        
        return exchnageService.findExchange(exchCd);
    }
}
