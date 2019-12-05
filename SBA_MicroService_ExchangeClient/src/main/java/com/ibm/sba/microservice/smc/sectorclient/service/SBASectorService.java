package com.ibm.sba.microservice.smc.sectorclient.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;
import com.ibm.sba.microservice.smc.sectorclient.dao.SBASectorRepository;
import net.bluefsd.entity.Company;
import net.bluefsd.entity.Sector;
import net.bluefsd.entity.StockPrice;

@Service(value = "sectorService")
public class SBASectorService {
	SBASectorRepository sectorRepository;

	// getList of Companies in a Sector
    /*
     * public List<Company> listCompanyBySectior(String sectorCd) { return
     * sectorRepository.listCompanyBySector(sectorCd); }
     */
	// getSectorPrice Sector ID, From Period, To period, periodicity
	public List<StockPrice> findSectorPrice(Timestamp from, Timestamp to, int periodicity, String sectorCd) {
		return sectorRepository.listSectorPrice(from, to, sectorCd);
	}

	public List<Sector> listSector() {
		return sectorRepository.findAll();
	}
}
