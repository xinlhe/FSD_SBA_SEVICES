package com.ibm.sba.microservice.smc.exchangeclient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibm.sba.microservice.smc.exchangeclient.dao.SBAExchangeRepository;
import net.bluefsd.entity.Company;
import net.bluefsd.entity.Exchange;

@Service(value="exchangeService")
public class SBAExchangeService {
	@Autowired
	SBAExchangeRepository exchangeRepository;

	public Exchange addExchange(Exchange exchange) {
		return exchangeRepository.save(exchange);
	}

	public List<Exchange> listExchange() {
		return exchangeRepository.listExchange();
	}

	public List<Company> listCompanyByExchange(String exchangeCd) {
		return exchangeRepository.listCompanyByExchange(exchangeCd);
	}
	
	public Exchange findExchange(String exchangeCd) {
	    
        return exchangeRepository.findExchange(exchangeCd);
    }

}
