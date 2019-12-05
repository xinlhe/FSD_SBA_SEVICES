package net.bluefsd.service;

import java.util.Date;
import java.util.List;

import net.bluefsd.dao.SectorRepository;

public class SectorService {
	SectorRepository sectorRepository;

	// getList of Companies in a Sector
	public List listCompanyBySectior(String sectorCd) {
		return sectorRepository.listCompanyBySector(sectorCd);
	}

	// getSectorPrice Sector ID, From Period, To period, periodicity
	public List findSectorPrice(Date from, Date to, int periodicity, String sectorCd) {
		return sectorRepository.listSectorPrice(from, to, periodicity, sectorCd);
	}
}
