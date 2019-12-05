package com.ibm.sba.microservice.smc.companyclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibm.sba.microservice.smc.companyclient.dao.SBACompanyIPORepository;
import net.bluefsd.entity.IPOPlan;

@Service(value = "ipoService")
public class SBAComplayIPOService {

	@Autowired
	SBACompanyIPORepository ipoRepository;

	public IPOPlan add(IPOPlan ipoPlan) {
	    
		return ipoRepository.save(ipoPlan);
	}
}
