package com.ibm.sba.microservice.smc.sectorclient.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.sba.microservice.smc.sectorclient.service.SBASectorService;
import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.entity.Company;
import net.bluefsd.entity.Sector;
import net.bluefsd.entity.StockPrice;

@RestController
@CrossOrigin
@RequestMapping("/sector")
public class SBASectorController extends BaseController {
	@Autowired
	SBASectorService sectorService;

	public List<Sector> listSector() {
		return sectorService.listSector();
	}

    /*
     * public List<Company> listCompanyBySector(String sectorCd) { return
     * sectorService.listCompanyBySectior(sectorCd); }
     */

	public List<StockPrice> findSectorPrice(Timestamp from, Timestamp to, int periodicity, String sectorCd) {
		return sectorService.findSectorPrice(from, to, periodicity, sectorCd);
	}
}
