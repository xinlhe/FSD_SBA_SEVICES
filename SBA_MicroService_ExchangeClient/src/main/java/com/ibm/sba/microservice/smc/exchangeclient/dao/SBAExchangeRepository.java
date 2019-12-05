package com.ibm.sba.microservice.smc.exchangeclient.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.Company;
import net.bluefsd.entity.Exchange;

@Repository(value = "exchangeRepository")
public interface SBAExchangeRepository extends JpaRepository<Exchange, Long> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT e FROM Exchange e")
	public List<Exchange> listExchange();

	@Transactional(readOnly = true)
	@Query(value = "SELECT c FROM Company c, Stock s where s.exchCd= :exchCd and c.companyCd=s.companyCd")
	public List<Company> listCompanyByExchange(@Param("exchCd") String exchCd);
	
	@Transactional(readOnly = true)
    @Query(value = "SELECT e FROM Exchange e where e.exchCd= :exchCd")
    public Exchange findExchange(@Param("exchCd") String exchCd);
}
