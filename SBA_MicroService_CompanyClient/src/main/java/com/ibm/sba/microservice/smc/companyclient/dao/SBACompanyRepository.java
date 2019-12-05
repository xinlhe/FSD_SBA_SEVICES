package com.ibm.sba.microservice.smc.companyclient.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.Company;

@Repository(value = "companyRepository")
public interface SBACompanyRepository extends JpaRepository<Company, String> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT u FROM Company u")
	public List<Company> listCompany();

	@Transactional(readOnly = true)
	@Query(value = "SELECT u.companyName FROM Company u WHERE companyName like %:input% or companyCd like %:input%")
	public List<String> findMatchedCompanyName(@Param("input") String input);

	@Transactional(readOnly = true)
	@Query(value = "SELECT u FROM Company u WHERE companyCd=:companyCd")
	public Company findCompanyByCd(@Param("companyCd") String companyCd);

	@Transactional(readOnly = true)
	@Query(value = "select c.companyCd, c.companyName, c.ceoName, c.exchCd, e.exchName, c.director, c.brief, c.sectorCd, s.sectorName , st.stockCd "
			+ "from Company c, Sector s, Exchange e , Stock st where c.companyCd=:companyCd and c.sectorCd=s.sectorCd and c.exchCd=e.exchCd and st.companyCd=c.companyCd")
	public List<Object[]> findCompanyDetailByCd(@Param("companyCd") String companyCd);

	@Transactional(readOnly = true)
	@Query(value = "select c.companyCd, c.companyName, c.ceoName, c.exchCd, e.exchName, c.director, c.brief, c.sectorCd, s.sectorName , st.stockCd "
			+ "from Company c, Sector s, Exchange e, Stock st where c.sectorCd=s.sectorCd and c.exchCd=e.exchCd and st.companyCd=c.companyCd")
	public List<Object[]> listCompanyDetail();

	@Transactional(readOnly = true)
	@Query(value = "select c.companyCd, c.companyName, c.ceoName, c.exchCd, e.exchName, c.director, c.brief, c.sectorCd, s.sectorName , st.stockCd "
			+ "from Company c, Sector s, Exchange e, Stock st where ( c.companyName=:companyName ) and c.sectorCd=s.sectorCd and c.exchCd=e.exchCd and st.companyCd=c.companyCd")
	public List<Object[]> findCompanyDetailByName(String companyName);

}
