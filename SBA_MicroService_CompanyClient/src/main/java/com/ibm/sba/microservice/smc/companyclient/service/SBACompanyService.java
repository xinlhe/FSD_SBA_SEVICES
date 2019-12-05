package com.ibm.sba.microservice.smc.companyclient.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibm.sba.microservice.smc.companyclient.dao.SBACompanyIPORepository;
import com.ibm.sba.microservice.smc.companyclient.dao.SBACompanyRepository;
import net.bluefsd.entity.Company;
import net.bluefsd.model.CompanyDetail;

@Service
public class SBACompanyService {

	@Autowired
	SBACompanyRepository companyRepository;

	@Autowired
	SBACompanyIPORepository ipoRepository;

	public Company addOrUpdateCompany(Company com) {
	    
		return companyRepository.save(com);
	}

	public Company findCompanyByCd(String companyCd) {
	    
		return companyRepository.findCompanyByCd(companyCd);
	}

	public List<?> findCompanyIPODetails(String companyCd) {
	    
		return null;
	}

	public void updateCompanyDetail(CompanyDetail cd) {
	    
		Company cp = new Company();
		
		cp.setBrief(cd.getBrief());
		cp.setCeoName(cd.getCeoName());
		cp.setCompanyCd(cd.getCompanyCd());
		cp.setCompanyName(cd.getCompanyName());
		cp.setDirector(cd.getDirector());
		cp.setExchCd(cd.getExchCd());
		cp.setSectorCd(cd.getSectorCd());
		
		companyRepository.save(cp);
	}

	public CompanyDetail findCompanyDetail(String companyCd) throws Exception {
	    
		List<Object[]> list = companyRepository.findCompanyDetailByCd(companyCd);

		if (list == null || list.size() <= 0) {
		    
			throw new Exception("Can't find any company!");
			
		} else {
		    
			Object[] obj = list.get(0);
			
			CompanyDetail cd = composeCompanyDetail(obj);
			
			return cd;
		}
	}

	public List<CompanyDetail> listCompanyDetail() throws Exception {
	    
		List<CompanyDetail> cdList = new ArrayList<>();
		
		List<Object[]> list = companyRepository.listCompanyDetail();
		
		if (list == null || list.size() <= 0) {
		    
			throw new Exception("Can't find any company!");
			
		} else {
		    
			for (int i = 0; i < list.size(); i++) {
			    
				Object[] obj = list.get(i);
				
				CompanyDetail cd = composeCompanyDetail(obj);
				
				cdList.add(cd);
			}
		}
		
		return cdList;
	}

	public List<CompanyDetail> findCompanyDetailByName(String companyName) throws Exception {
	    
		List<CompanyDetail> cdList = new ArrayList<>();
		
		List<Object[]> list = companyRepository.findCompanyDetailByName(companyName);
		
		if (list == null || list.size() <= 0) {
		    
			throw new Exception("Can't find any company!");
			
		} 
		else {
		    
			for (int i = 0; i < list.size(); i++) {
			    
				Object[] obj = list.get(i);
				
				CompanyDetail cd = composeCompanyDetail(obj);
				
				cdList.add(cd);
			}
		}
		
		return cdList;
	}

	private CompanyDetail composeCompanyDetail(Object[] obj) {
		
	    CompanyDetail cd = new CompanyDetail();
		
		int index = 0;
		cd.setCompanyCd((String) obj[index++]);
		cd.setCompanyName((String) obj[index++]);
		cd.setCeoName((String) obj[index++]);
		cd.setExchCd((String) obj[index++]);
		cd.setExchName((String) obj[index++]);
		cd.setDirector((String) obj[index++]);
		cd.setBrief((String) obj[index++]);
		cd.setSectorCd((String) obj[index++]);
		cd.setSectorName((String) obj[index++]);
		cd.setStockCd((String) obj[index++]);

		return cd;
	}

	public List<String> listCompanyName(String searchStr) {
	    
		List<String> list = companyRepository.findMatchedCompanyName(searchStr);
		return list;

	}

}
