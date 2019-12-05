package com.ibm.sba.microservice.smc.sectorclient.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.Company;
import net.bluefsd.entity.Sector;
import net.bluefsd.entity.StockPrice;

@Repository(value = "sectorRepository")
public interface SBASectorRepository extends JpaRepository<Sector, Long> {

	// @Transactional(readOnly = true)
	// @Query(value = "SELECT cs FROM CompanySector cs where sectorCd=:sectorCd")
	// public List<Company> listCompanyBySector(@Param("sectorCd") String sectorCd);

	@Transactional(readOnly = true)
	@Query(value = "SELECT p FROM StockPrice p, Stock s where TIMESTAMPDIFF(SECOND, :from, cur_time)>=0 and TIMESTAMPDIFF(SECOND, cur_time, :to)<0 "
			+ "and p.stockCd=s.stockCd and s.companyCd in "
			+ "(select companyCd from Company cs where cs.sectorCd=:sectorCd)")
	public List<StockPrice> listSectorPrice(@Param("from") Timestamp from, @Param("to") Timestamp to,
			@Param("sectorCd") String sectorCd);
}
