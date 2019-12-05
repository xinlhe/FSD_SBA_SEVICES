package net.bluefsd.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.Company;
import net.bluefsd.entity.Sector;

@Repository(value = "sectorRepository")
public interface SectorRepository extends JpaRepository<Sector, Long> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT c FROM SectorCompany where sectorCd=:sectorCd")
	public List<Sector> listCompanyBySector(@Param("sectorCd") String sectorCd);

	@Transactional(readOnly = true)
	@Query(value = "SELECT c FROM SectorPrice where sectorCd=:sectorCd")
	public List<Company> listSectorPrice(Date from, Date to, int periodicity, @Param("sectorCd") String sectorCd);
}
