package com.ibm.sba.microservice.smc.companyclient.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.Company;
import net.bluefsd.entity.IPOPlan;

@Repository(value = "ipoRepository")
public interface SBACompanyIPORepository extends JpaRepository<IPOPlan, Long> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT u FROM IPOPlan u WHERE companyCd=:companyCd")
	public Company findIPOByCompanyCd(@Param("companyCd") String companyCd);

}
